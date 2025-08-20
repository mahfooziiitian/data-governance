/*
Download slf4j-api-*.jar and this jar to $HIVE_HOME/lib directory.
Download Hive-hcatalog-hbase-storage-handler.jar and add this jar to hcatalog lib folder.
Configure the pig.properties files in $PIG_HOMe/conf

export HADOOP_HOME=<Path to Hadoop installed directory>/etc/hadoop
export HCAT_HOME=<Path to Hcatalog directory>
export HIVE_HOME=<Path to Hive installed directory>

export PIG_CLASSPATH=$HCAT_HOME/share/hcatalog/hive-hcatalog-core*.jar:\
    $HCAT_HOME/share/hcatalog/hive-hcatalog-pig-adapter*.jar:\
    $HIVE_HOME/lib/hive-metastore-*.jar:$HIVE_HOME/lib/libthrift-*.jar:\
    $HIVE_HOME/lib/hive-exec-*.jar:$HIVE_HOME/lib/libfb303-*.jar:\
    $HIVE_HOME/lib/jdo2-api-*-ec.jar:$HIVE_HOME/conf:$HADOOP_HOME/conf:\
    $HIVE_HOME/lib/slf4j-api-*.jar

export PIG_OPTS=-Dhive.metastore.uris=thrift://<Host_name>:<Port>

 */
package com.mahfooz.hcatalog;

public class Hcatalog {
}
