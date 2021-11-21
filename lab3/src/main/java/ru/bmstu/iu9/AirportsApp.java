package ru.bmstu.iu9;

import org.apache.spark.SparkConf;

public class AirportsApp {
    public static void main(String[] args){
        SparkConf conf = new SparkConf(Hadoop ).setAppName(Hadoop "example");
        JavaSparkContext sc = new JavaSparkContext(Hadoop conf);
        JavaRDD<String> distFile = sc.textFile(Hadoop "war-and-peace-1.txt");
        JavaRDD<String> splitted = distFile.flatMap(Hadoop
                s -> Arrays.stream(Hadoop s.split(Hadoop " ")).iterator(Hadoop )
        );
        JavaPairRDD<String, Long> wordsWithCount =
        splitted.mapToPair(Hadoop
                s -> new Tuple2<>(Hadoop s, 1l)
        );
    }
}
