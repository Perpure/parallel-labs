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
hadoop fs -copyFromLocal flights.csv
hadoop fs -copyFromLocal airports.csv
export HADOOP_CLASSPATH=target/AirportsApp.jar
hadoop ru.bmstu.iu9.AirportsApp flights.csv airports.csv output
hadoop fs -copyToLocal output
