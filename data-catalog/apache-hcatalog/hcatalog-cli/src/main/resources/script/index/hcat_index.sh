#CREATE INDEX index_name
#ON TABLE base_table_name (col_name, ...)
#AS 'index.handler.class.name'
#[WITH DEFERRED REBUILD]
#[IDXPROPERTIES (property_name = property_value, ...)]
#[IN TABLE index_table_name]
#[PARTITIONED BY (col_name, ...)][
#   [ ROW FORMAT ...] STORED AS ...
#   | STORED BY ...
#[LOCATION hdfs_path]
#[TBLPROPERTIES (...)]

hcat -e "CREATE INDEX index_salary ON TABLE hcat.employee(salary)
  AS 'org.apache.hadoop.hive.ql.index.compact.CompactIndexHandler' WITH DEFERRED REBUILD ;"


#DROP INDEX <index_name> ON <table_name>
hcat -e "DROP INDEX index_salary ON employee;"