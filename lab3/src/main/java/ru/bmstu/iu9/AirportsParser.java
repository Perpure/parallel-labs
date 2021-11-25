package ru.bmstu.iu9;

import scala.Tuple2;

import java.io.IOException;
import java.io.Serializable;

public class AirportsParser implements Serializable {
    private static final String DELIMITER_REGEX = "\",\"";
    private static final int AIRPORT_ID_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int AIRPORT_TYPE = 0;

    private static String removeQuotes(String s) {
        return s.substring(1, s.length() - 1);
    }

    public static Tuple2<Integer, String> parseAirports(String rowRaw) {
        rowRaw = removeQuotes(rowRaw);
        String[] row = rowRaw.split(DELIMITER_REGEX);
        String airportName = row[NAME_INDEX];
        int airportID = Integer.parseInt(row[AIRPORT_ID_INDEX]);
        return new Tuple2<>(airportID, airportName);
    }

    public static boolean IsDataRow(String row) {
        
    }
}
