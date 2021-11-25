package ru.bmstu.iu9;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Int;
0
import scala.Tuple2;

public class AirportsApp {
    public static void main(String[] args){
        SparkConf conf = new SparkConf().setAppName("AirportsApp");
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> distFile = sc.textFile("664600583_T_ONTIME_sample.csv");
        JavaPairRDD<Tuple2<Integer, Integer>, Flight> flights = distFile.mapToPair(FlightsParser::parseFlights);
        JavaPairRDD<Tuple2<Integer, Integer>, FlightsInfo> flightsInfo = flights.combineByKey(
                FlightsInfo::createInfo, FlightsInfo::updateInfo, FlightsInfo::mergeInfo
        );

        

    }
}
