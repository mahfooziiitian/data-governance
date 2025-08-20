package com.mahfooz.hcatalog.api.reader.hive;

import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hive.hcatalog.common.HCatException;
import org.apache.hive.hcatalog.data.HCatRecord;
import org.apache.hive.hcatalog.data.transfer.DataTransferFactory;
import org.apache.hive.hcatalog.data.transfer.HCatReader;
import org.apache.hive.hcatalog.data.transfer.ReadEntity;
import org.apache.hive.hcatalog.data.transfer.ReaderContext;

import java.io.IOException;
import java.util.*;

public class HiveTableReader {

    private String dbName;
    private String tableName;
    private int currentSplit = -1;
    private ReaderContext readCntxt = null;
    private Iterator<HCatRecord> currentHCatRecordItr = null;
    private HCatRecord currentHCatRecord;
    private int numberOfSplits = 0;
    private Map<String, String> partitionKV = null;

    /**
     * Constructor for reading whole hive table
     * @param dbName
     * @param tableName
     * @throws IOException
     */
    public HiveTableReader(String dbName, String tableName) throws IOException {
        this(dbName, tableName, null);
    }

    /**
     * Constructor for reading a partition of the hive table
     * @param dbName
     * @param tableName
     * @param partitionKV key-value pairs condition on the partition
     * @throws IOException
     */
    public HiveTableReader(String dbName, String tableName, Map<String, String> partitionKV) throws IOException {
        this.dbName = dbName;
        this.tableName = tableName;
        this.partitionKV = partitionKV;
        initialize();
    }

    private void initialize() throws IOException {
        try {
            this.readCntxt = getHiveReaderContext(dbName, tableName, partitionKV);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException(e);
        }

        this.numberOfSplits = readCntxt.numSplits();
    }

    public boolean next() throws IOException {

        while (currentHCatRecordItr == null || !currentHCatRecordItr.hasNext()) {
            currentSplit++;
            if (currentSplit == numberOfSplits) {
                return false;
            }

            currentHCatRecordItr = loadHCatRecordItr(readCntxt, currentSplit);
        }

        currentHCatRecord = currentHCatRecordItr.next();

        return true;
    }

    public String[] getRow() {
        return getRowAsStringArray(currentHCatRecord);
    }

    public List<String> getRowAsList() {
        return getRowAsList(currentHCatRecord);
    }

    public static List<String> getRowAsList(HCatRecord record, List<String> rowValues) {
        List<Object> allFields = record.getAll();
        for (Object o : allFields) {
            rowValues.add((o == null) ? null : o.toString());
        }
        return rowValues;
    }

    public static List<String> getRowAsList(HCatRecord record) {
        return Arrays.asList(getRowAsStringArray(record));
    }

    public static String[] getRowAsStringArray(HCatRecord record) {
        String[] arr = new String[record.size()];
        for (int i = 0; i < arr.length; i++) {
            Object o = record.get(i);
            arr[i] = (o == null) ? null : o.toString();
        }
        return arr;
    }


    public void close() throws IOException {
        this.readCntxt = null;
        this.currentHCatRecordItr = null;
        this.currentHCatRecord = null;
        this.currentSplit = -1;
    }

    public String toString() {
        return "hive table reader for: " + dbName + "." + tableName;
    }

    private static ReaderContext getHiveReaderContext(String database, String table, Map<String, String> partitionKV) throws Exception {
        HiveConf hiveConf = new HiveConf(HiveTableReader.class);
        Iterator<Map.Entry<String, String>> itr = hiveConf.iterator();
        Map<String, String> map = new HashMap<String, String>();
        while (itr.hasNext()) {
            Map.Entry<String, String> kv = itr.next();
            map.put(kv.getKey(), kv.getValue());
        }

        ReadEntity entity;
        if (partitionKV == null || partitionKV.size() == 0) {
            entity = new ReadEntity.Builder().withDatabase(database).withTable(table).build();
        } else {
            entity = new ReadEntity.Builder().withDatabase(database).withTable(table).withPartition(partitionKV).build();
        }

        HCatReader reader = DataTransferFactory.getHCatReader(entity, map);
        ReaderContext cntxt = reader.prepareRead();

        return cntxt;
    }

    private static Iterator<HCatRecord> loadHCatRecordItr(ReaderContext readCntxt, int dataSplit) throws HCatException {
        HCatReader currentHCatReader = DataTransferFactory.getHCatReader(readCntxt, dataSplit);

        return currentHCatReader.read();
    }
}

