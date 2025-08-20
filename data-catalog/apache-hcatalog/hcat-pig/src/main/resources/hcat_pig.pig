-- HCatLoader
A = LOAD 'tablename' USING org.apache.HCatalog.pig.HCatLoader();

student = LOAD 'hdfs://localhost:9000/pig_data/student_details.txt' USING
   PigStorage(',') as (id:int, firstname:chararray, lastname:chararray,
   phone:chararray, city:chararray);

student_order = ORDER student BY age DESC;
STORE student_order INTO 'student_order_table' USING org.apache.HCatalog.pig.HCatStorer();
student_limit = LIMIT student_order 4;
Dump student_limit;
