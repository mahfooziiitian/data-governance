package com.mahfooz.hcatalog.api.reader;

import org.apache.hadoop.conf.Configuration;
import org.apache.hive.hcatalog.common.HCatException;
import org.apache.hive.hcatalog.data.transfer.DataTransferFactory;
import org.apache.hive.hcatalog.data.transfer.HCatReader;
import org.apache.hive.hcatalog.data.transfer.ReadEntity;
import org.apache.hive.hcatalog.data.transfer.ReaderContext;

import java.util.Map;

public class HCatReaders {

    public static void main(String[] args) throws HCatException {

        Configuration config=null;

        ReadEntity.Builder builder = new ReadEntity.Builder();
        ReadEntity entity = builder.withDatabase("mydatabase").withTable("mytable").build();

        //Get the reader
        HCatReader reader = DataTransferFactory.getHCatReader(entity, (Map<String, String>) config);

        //Get a ReaderContext from a reader:-
        ReaderContext cntxt = reader.prepareRead();
    }
}
