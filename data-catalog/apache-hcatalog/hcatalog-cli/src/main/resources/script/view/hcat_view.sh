#CREATE VIEW [IF NOT EXISTS] [db_name.]view_name [(column_name [COMMENT column_comment], ...) ]
#[COMMENT view_comment]
#[TBLPROPERTIES (property_name = property_value, ...)]
#AS SELECT ...;

hcat -e "CREATE VIEW hcat.emp_deg_view
   AS SELECT eid, name, salary, designation FROM hcat.employee WHERE salary >= 35000;"

