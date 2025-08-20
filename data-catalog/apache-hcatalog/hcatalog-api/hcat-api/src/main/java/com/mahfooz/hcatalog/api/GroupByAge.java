package com.mahfooz.hcatalog.api;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.hive.hcatalog.common.HCatConstants;
import org.apache.hive.hcatalog.data.DefaultHCatRecord;
import org.apache.hive.hcatalog.data.HCatRecord;
import org.apache.hive.hcatalog.data.schema.HCatSchema;
import org.apache.hive.hcatalog.mapreduce.HCatInputFormat;
import org.apache.hive.hcatalog.mapreduce.HCatOutputFormat;
import org.apache.hive.hcatalog.mapreduce.OutputJobInfo;

import java.io.IOException;
import java.util.Iterator;

public class GroupByAge extends Configured implements Tool {

    public static class Map extends Mapper<WritableComparable,
            HCatRecord, IntWritable, IntWritable> {
        int age;

        @Override
        protected void map(
                WritableComparable key, HCatRecord value,
                org.apache.hadoop.mapreduce.Mapper<WritableComparable,
                        HCatRecord, IntWritable, IntWritable>.Context context
        )throws IOException, InterruptedException {
            age = (Integer) value.get(1);
            context.write(new IntWritable(age), new IntWritable(1));
        }
    }

    public static class Reduce extends Reducer<IntWritable, IntWritable,
            WritableComparable, HCatRecord> {
        @Override
        protected void reduce(
                IntWritable key, java.lang.Iterable<IntWritable> values,
                org.apache.hadoop.mapreduce.Reducer<IntWritable, IntWritable,
                        WritableComparable, HCatRecord>.Context context
        )throws IOException ,InterruptedException {
            int sum = 0;
            Iterator<IntWritable> iter = values.iterator();

            while (iter.hasNext()) {
                sum++;
                iter.next();
            }

            HCatRecord record = new DefaultHCatRecord(2);
            record.set(0, key.get());
            record.set(1, sum);
            context.write(null, record);
        }
    }

    public int run(String[] args) throws Exception {
        Configuration conf = getConf();
        args = new GenericOptionsParser(conf, args).getRemainingArgs();

        String serverUri = args[0];
        String inputTableName = args[1];
        String outputTableName = args[2];
        String dbName = null;
        String principalID = System

                .getProperty(HCatConstants.HCAT_METASTORE_PRINCIPAL);
        if (principalID != null)
            conf.set(HCatConstants.HCAT_METASTORE_PRINCIPAL, principalID);
        Job job = Job.getInstance(conf, "GroupByAge");
        HCatInputFormat.setInput(job.getConfiguration(),dbName,inputTableName,null);

        // initialize HCatOutputFormat
        job.setInputFormatClass(HCatInputFormat.class);
        job.setJarByClass(GroupByAge.class);
        job.setMapperClass(Map.class);
        job.setReducerClass(Reduce.class);

        job.setMapOutputKeyClass(IntWritable.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setOutputKeyClass(WritableComparable.class);
        job.setOutputValueClass(DefaultHCatRecord.class);

        HCatOutputFormat.setOutput(job, OutputJobInfo.create(dbName, outputTableName, null));
        HCatSchema s = HCatOutputFormat.getTableSchema(job.getConfiguration());
        System.err.println("INFO: output schema explicitly set for writing:" + s);
        HCatOutputFormat.setSchema(job, s);
        job.setOutputFormatClass(HCatOutputFormat.class);
        return (job.waitForCompletion(true) ? 0 : 1);
    }

    public static void main(String[] args) throws Exception {
        int exitCode = ToolRunner.run(new GroupByAge(), args);
        System.exit(exitCode);
    }
}

