#!/bin/bash

stop-yarn.sh
stop-dfs.sh
rm -rf ~/hdata/datanode
rm -rf ~/hdata/namenode

mkdir ~/hdata/datanode
mkdir ~/hdata/namenode

hdfs namenode -format
start-dfs.sh
start-yarn.sh
hdfs dfs -mkdir /user
hdfs dfs -mkdir /user/fall

mvn package
hadoop fs -copyFromLocal warandpeace1.txt 
export HADOOP_CLASSPATH=target/WordCount.jar
hadoop WordCount warandpeace1.txt output
hadoop fs -copyToLocal output 
