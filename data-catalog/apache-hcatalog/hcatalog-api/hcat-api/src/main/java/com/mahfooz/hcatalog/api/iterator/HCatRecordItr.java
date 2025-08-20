package com.mahfooz.hcatalog.api.iterator;

import org.apache.hive.hcatalog.data.DefaultHCatRecord;
import org.apache.hive.hcatalog.data.HCatRecord;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HCatRecordItr implements Iterator<HCatRecord> {

    int i = 0;

    private HCatRecord getRecord(int i) {
        List<Object> list = new ArrayList<Object>(2);
        list.add("Row #: " + i);
        list.add(i);
        return new DefaultHCatRecord(list);
    }

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