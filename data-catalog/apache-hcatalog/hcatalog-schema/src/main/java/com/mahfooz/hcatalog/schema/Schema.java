/*
Defining a Schema
A schema can be defined as HCatalog table:

    create table mytable(
    id    int,
    firstname    string,
    lastname    string
    )
    comment 'An example of an HCatalog table'
    partitioned by (dob string)
    stored as sequencefile;

 */
package com.mahfooz.hcatalog.schema;

public class Schema {
}
