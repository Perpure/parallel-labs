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
hadoop fs -copyFromLocal airport.csv
export HADOOP_CLASSPATH=target/Airport.jar
hadoop ru.bmstu.AirportMain flights.csv airport.csv output
hadoop fs -copyToLocal output 
