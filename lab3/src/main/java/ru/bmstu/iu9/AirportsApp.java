package ru.bmstu.iu9;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;

public class AirportsApp {
    public static void main(String[] args){
        SparkConf conf = new SparkConf().setAppName("AirportsApp");
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> distFile = sc.textFile("664600583_T_ONTIME_sample.csv");
        JavaPairRDD<Tuple2<Integer, Integer>, Flight>
        JavaRDD<String> splitted = distFile.flatMap(
                s -> Arrays.stream(s.split(" ")).iterator()
        );
        JavaPairRDD<String, Long> wordsWithCount = splitted.mapToPair(s -> new Tuple2<>(s, 1L));
    }
}
