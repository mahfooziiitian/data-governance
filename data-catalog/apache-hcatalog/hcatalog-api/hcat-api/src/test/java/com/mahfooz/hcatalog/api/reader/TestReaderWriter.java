package com.mahfooz.hcatalog.api.reader;

import org.apache.hadoop.hive.ql.CommandNeedRetryException;
import org.apache.hive.hcatalog.common.HCatException;
import org.apache.hive.hcatalog.data.DefaultHCatRecord;
import org.apache.hive.hcatalog.data.HCatRecord;
import org.apache.hive.hcatalog.data.transfer.*;
import org.apache.hive.hcatalog.mapreduce.HCatBaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class TestReaderWriter extends HCatBaseTest {
    @Test
    public void test() throws CommandNeedRetryException,
            IOException, ClassNotFoundException {

        driver.run("drop table mytbl");
        driver.run("create table mytbl (a string, b int)");

        Iterator<Entry<String, String>> itr = hiveConf.iterator();
        Map<String, String> map = new HashMap<String, String>();

        while (itr.hasNext()) {
            Entry<String, String> kv = itr.next();
            map.put(kv.getKey(), kv.getValue());
        }

        WriterContext cntxt = runsInMaster(map);
        File writeCntxtFile = File.createTempFile("hcat-write", "temp");
        writeCntxtFile.deleteOnExit();

        // Serialize context.
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(writeCntxtFile));
        oos.writeObject(cntxt);
        oos.flush();
        oos.close();

        // Now, deserialize it.
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(writeCntxtFile));
        cntxt = (WriterContext) ois.readObject();
        ois.close();
        runsInSlave(cntxt);
        commit(map, true, cntxt);

        ReaderContext readCntxt = runsInMaster(map, false);
        File readCntxtFile = File.createTempFile("hcat-read", "temp");
        readCntxtFile.deleteOnExit();
        oos = new ObjectOutputStream(new FileOutputStream(readCntxtFile));
        oos.writeObject(readCntxt);
        oos.flush();
        oos.close();

        ois = new ObjectInputStream(new FileInputStream(readCntxtFile));
        readCntxt = (ReaderContext) ois.readObject();
        ois.close();

        for (int i = 0; i < readCntxt.numSplits(); i++) {
            runsInSlave(readCntxt, i);
        }
    }

    private WriterContext runsInMaster(Map<String, String> config) throws HCatException {
        WriteEntity.Builder builder = new WriteEntity.Builder();
        WriteEntity entity = builder.withTable("mytbl").build();

        HCatWriter writer = DataTransferFactory.getHCatWriter(entity, config);
        WriterContext info = writer.prepareWrite();
        return info;
    }

    private ReaderContext runsInMaster(Map<String, String> config,
                                       boolean bogus) throws HCatException {
        ReadEntity entity = new ReadEntity.Builder().withTable("mytbl").build();
        HCatReader reader = DataTransferFactory.getHCatReader(entity, config);
        ReaderContext cntxt = reader.prepareRead();
        return cntxt;
    }

    private void runsInSlave(ReaderContext cntxt, int slaveNum) throws HCatException {
        HCatReader reader = DataTransferFactory.getHCatReader(cntxt, slaveNum);
        Iterator<HCatRecord> itr = reader.read();
        int i = 1;

        while (itr.hasNext()) {
            HCatRecord read = itr.next();
            HCatRecord written = getRecord(i++);

            // Argh, HCatRecord doesnt implement equals()
            Assert.assertTrue("Read: " + read.get(0) + "Written: " + written.get(0),
                    written.get(0).equals(read.get(0)));

            Assert.assertTrue("Read: " + read.get(1) + "Written: " + written.get(1),
                    written.get(1).equals(read.get(1)));

            Assert.assertEquals(2, read.size());
        }

        //Assert.assertFalse(itr.hasNext());
    }

    private void runsInSlave(WriterContext context) throws HCatException {
        HCatWriter writer = DataTransferFactory.getHCatWriter(context);
        writer.write(new HCatRecordItr());
    }

    private void commit(Map<String, String> config, boolean status,
                        WriterContext context) throws IOException {
        WriteEntity.Builder builder = new WriteEntity.Builder();
        WriteEntity entity = builder.withTable("mytbl").build();
        HCatWriter writer = DataTransferFactory.getHCatWriter(entity, config);

        if (status) {
            writer.commit(context);
        } else {
            writer.abort(context);
        }
    }

    private static HCatRecord getRecord(int i) {
        List<Object> list = new ArrayList<Object>(2);
        list.add("Row #: " + i);
        list.add(i);
        return new DefaultHCatRecord(list);
    }

    private static class HCatRecordItr implements Iterator<HCatRecord> {
        int i = 0;

        @Override
        public boolean hasNext() {
            return i++ < 100 ? true : false;
        }

        @Override
        public HCatRecord next() {
            return getRecord(i);
        }

        @Override
        public void remove() {
            throw new RuntimeException();
        }
    }
}
