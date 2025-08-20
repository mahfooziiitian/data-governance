/*
For Example using pig:

    A=LOAD 'hdfs://localhost:9000/usr/hadoop/data.csv' USING PigStorage() AS (f1:int, f2:int, f3:int);

With HCatalog it will be easy:

    A= LOAD 'data.csv' using HCatLoader();

 */
package com.mahfooz.hcatalog.cli.loader;

/**
 *
 * @author 370037
 */
public class HCatLoaders {
    
}
