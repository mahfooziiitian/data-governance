/*

Create Table Statement
    CREATE [TEMPORARY] [EXTERNAL] TABLE [IF NOT EXISTS] [db_name.] table_name
    [(col_name data_type [COMMENT col_comment], ...)]
    [COMMENT table_comment]
    [ROW FORMAT row_format]
    [STORED AS file_format]


    hcat –e "CREATE TABLE IF NOT EXISTS hcat.employee ( eid int, name String, salary String, destination String)
        COMMENT 'Employee details'
        ROW FORMAT DELIMITED
        FIELDS TERMINATED BY '\t'
        LINES TERMINATED BY '\n'
        STORED AS TEXTFILE;"

 */
package com.mahfooz.hcatalog.cli.table;

public class HcatTable {
}
