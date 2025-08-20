#From the version Hive-0.11.0 onwards, HCatalog comes with Hive installation.
#Step 1:  Verifying JAVA Installation

          java -version

#Step 2:  Installing Java

          export JAVA_HOME=/usr/local/jdk1.7.0_71
          export PATH=PATH:$JAVA_HOME/bin

#Step 3:  Verifying Hadoop Installation

          hadoop version

#Step 4:  You can set Hadoop environment variables by appending the following commands to ~/.bashrc file.
          export HADOOP_HOME=/usr/local/hadoop
          export HADOOP_MAPRED_HOME=$HADOOP_HOME
          export HADOOP_COMMON_HOME=$HADOOP_HOME
          export HADOOP_HDFS_HOME=$HADOOP_HOME
          export YARN_HOME=$HADOOP_HOME
          export HADOOP_COMMON_LIB_NATIVE_DIR=$HADOOP_HOME/lib/native
          export PATH=$PATH:$HADOOP_HOME/sbin:$HADOOP_HOME/bin

          source ~/.bashrc

#step 5:
    export HCAT_HOME=$HiVE_HOME/HCatalog

