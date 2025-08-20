/*
Q1) What is HCatalog?
Basically, a table as well as a storage management layer for Hadoop is what we call HCatalog. Its main function is that it enables users with different data processing tools, for example, Pig, MapReduce to make the read and write data easily on the grid.

In addition, its abstraction presents users with a relational view of data in the Hadoop distributed file system (HDFS). Also, it makes sure that where or in what format their data is stored like the RCFile format, text files, SequenceFiles, or ORC files, users need not worry about.
Hence we can say in any format for which a SerDe (serializer-deserializer) can be written, HCatalog supports reading and writing files.

Moreover, it supports RCFile, CSV, JSON, and SequenceFile, and ORC file formats, by default. Although, make sure to provide the InputFormat, OutputFormat, and SerDe, to use a custom format.


Q2)
Intended Audience for HCatalog Tutorial
For the professionals who want to make a career in Big Data Analytics using Hadoop Framework, HCatalog tutorial is specially designed for them.
Also, ETL developers, as well as analytics professionals, may go through this tutorial for good effect.


 */
package com.mahfooz.hcat.faq;

public class FAQ {
}
