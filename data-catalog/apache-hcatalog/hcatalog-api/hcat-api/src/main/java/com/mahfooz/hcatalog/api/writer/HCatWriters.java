package com.mahfooz.hcatalog.api.writer;

import org.apache.hive.hcatalog.common.HCatException;
import org.apache.hive.hcatalog.data.transfer.DataTransferFactory;
import org.apache.hive.hcatalog.data.transfer.HCatWriter;
import org.apache.hive.hcatalog.data.transfer.WriteEntity;
import org.apache.hive.hcatalog.data.transfer.WriterContext;
import java.util.Map;

public class HCatWriters {

    public static void main(String[] args) throws HCatException {

        WriteEntity.Builder builder = new WriteEntity.Builder();
        WriteEntity entity = builder.withDatabase("mydatabase").withTable("mytable").build();
        //After creating a WriteEntity, the next step is to get a WriterContext:

        Map<String, String> config=null;
        HCatWriter writer = DataTransferFactory.getHCatWriter(entity, config);
        WriterContext info = writer.prepareWrite();
    }
}
