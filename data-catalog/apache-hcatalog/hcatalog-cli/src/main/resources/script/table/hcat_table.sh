#Create Table Statement
#    CREATE [TEMPORARY] [EXTERNAL] TABLE [IF NOT EXISTS] [db_name.] table_name
#    [(col_name data_type [COMMENT col_comment], ...)]
#    [COMMENT table_comment]
#    [ROW FORMAT row_format]
#    [STORED AS file_format]


hcat -e \
  "CREATE TABLE IF NOT EXISTS hcat.employee
    ( eid int, name String, salary String, destination String)
  COMMENT 'Employee details'
  ROW FORMAT DELIMITED
  FIELDS TERMINATED BY '\t'
  LINES TERMINATED BY '\n'
  STORED AS TEXTFILE;"


#ALTER TABLE
#ALTER TABLE table_name ADD [IF NOT EXISTS] PARTITION partition_spec
#[LOCATION 'location1'] partition_spec [LOCATION 'location2'] ...;
#partition_spec:: (p_column = p_col_value, p_column = p_col_value, ...)

hcat -e \
  "ALTER TABLE employee ADD PARTITION (year = '2013')
  location '/2012/part2012';"

#Renaming table
#ALTER TABLE name RENAME TO new_name
./hcat -e "ALTER TABLE employee RENAME TO emp;"

#ALTER TABLE name ADD COLUMNS (col_spec[, col_spec ...])
./hcat -e "ALTER TABLE employee ADD COLUMNS (dept STRING COMMENT 'Department name');"

#ALTER TABLE name DROP [COLUMN] column_name
./hcat -e "ALTER TABLE employee REPLACE COLUMNS ( eid INT empid Int, ename STRING name String);"

#ALTER TABLE name CHANGE column_name new_name new_type
./hcat -e "ALTER TABLE employee CHANGE name ename String;"
./hcat -e "ALTER TABLE employee CHANGE salary salary Double;"

#ALTER TABLE name REPLACE COLUMNS (col_spec[, col_spec ...])
./hcat -e "ALTER TABLE employee REPLACE COLUMNS ( eid INT empid Int, ename STRING name String);"

#STATIC PARTITION
hcat -e "ALTER TABLE hcat.employee ADD PARTITION (year = '2013')"
hcat -e "ALTER TABLE employee PARTITION (year='1203') RENAME TO PARTITION (Yoj='1203');"
hcat -e "ALTER TABLE employee DROP [IF EXISTS] PARTITION (year='1203');"

./hcat -e "Show partitions employee;"


#Droping table
./hcat -e "DROP TABLE IF EXISTS employee;"

./hcat -e "Show tables;"