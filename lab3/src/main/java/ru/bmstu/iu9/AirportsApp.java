package ru.bmstu.iu9;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.broadcast.Broadcast;
import scala.Tuple2;

import java.util.Map;

public class AirportsApp {
    public static void main(String[] args){
        SparkConf conf = new SparkConf().setAppName("AirportsApp");
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> distFile = sc.textFile("664600583_T_ONTIME_sample.csv");
        JavaPairRDD<Tuple2<Integer, Integer>, Flight> flights = distFile.filter(FlightsParser::isDataRow)
                .mapToPair(FlightsParser::parseFlights);
        JavaPairRDD<Tuple2<Integer, Integer>, FlightsInfo> flightsInfo = flights.combineByKey(
                FlightsInfo::createInfo, FlightsInfo::updateInfo, FlightsInfo::mergeInfo
        );

        JavaRDD<String> airportsFile = sc.textFile("664600583_T_ONTIME_sample.csv");
        JavaPairRDD<Integer, String> airports = airportsFile.filter(AirportsParser::isDataRow)
                .mapToPair(AirportsParser::parseAirports);

        Map<Integer, String> airportsMap = airports.collectAsMap();
        final Broadcast<Map<Integer, String>> airportsBroadcasted = sc.broadcast(airportsMap);

        JavaRDD<String> infoRDD = flightsInfo.map(
                flightInfo -> {
                    Map<Integer, String> airportsBroadcastedMap = airportsBroadcasted.getValue();
                    String origAirport = airportsBroadcastedMap.get(flightInfo._1._1);
                    String destAirport = airportsBroadcastedMap.get(flightInfo._1._2);
                    
                }
        )

    }


}
