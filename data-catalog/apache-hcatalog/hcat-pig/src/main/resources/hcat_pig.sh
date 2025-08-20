#Running pig
pig -useHCatalog

#For Pig commands that omit -useHCatalog, you need to tell Pig where to find your HCatalog jars and the Hive jars used by the HCatalog client.
#To do this, you must define the environment variable PIG_CLASSPATH with the appropriate jars.

#export HADOOP_HOME=<path_to_hadoop_install>
#export HIVE_HOME=<path_to_hive_install>
#export HCAT_HOME=<path_to_hcat_install>
export PIG_CLASSPATH=$HCAT_HOME/share/hcatalog/hcatalog-core*.jar:\
$HCAT_HOME/share/hcatalog/hcatalog-pig-adapter*.jar:\
$HIVE_HOME/lib/hive-metastore-*.jar:$HIVE_HOME/lib/libthrift-*.jar:\
$HIVE_HOME/lib/hive-exec-*.jar:$HIVE_HOME/lib/libfb303-*.jar:\
$HIVE_HOME/lib/jdo2-api-*-ec.jar:$HIVE_HOME/conf:$HADOOP_HOME/conf:\
$HIVE_HOME/lib/slf4j-api-*.jar

#export PIG_OPTS=-Dhive.metastore.uris=thrift://<hostname>:<port>

#Or you can pass the jars in your command line:
#<path_to_pig_install>/bin/pig -Dpig.additional.jars=\
$HCAT_HOME/share/hcatalog/hcatalog-core*.jar:\
$HCAT_HOME/share/hcatalog/hcatalog-pig-adapter*.jar:\
$HIVE_HOME/lib/hive-metastore-*.jar:$HIVE_HOME/lib/libthrift-*.jar:\
$HIVE_HOME/lib/hive-exec-*.jar:$HIVE_HOME/lib/libfb303-*.jar:\
#$HIVE_HOME/lib/jdo2-api-*-ec.jar:$HIVE_HOME/lib/slf4j-api-*.jar  <script.pig>

