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
spark-submit --class ru.bmstu.iu9.AirportsApp --master yarn-client --num-executors 3  target/AirportsApp.jar
hadoop fs -copyToLocal output
