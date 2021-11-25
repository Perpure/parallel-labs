package ru.bmstu.iu9;

import scala.Tuple2;

import java.io.IOException;
import java.io.Serializable;

public class AirportsParser implements Serializable {
    private static final String DELIMITER_REGEX = "\",\"";
    private static final int AIRPORT_ID_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int AIRPORT_TYPE = 0;

    public static Tuple2<Integer, String> map(Text value, Mapper.Context context) {
        if (key.get() != 0) {
            String rowRaw = value.toString();
            rowRaw = rowRaw.substring(1, rowRaw.length() - 1);
            String[] row = rowRaw.split(DELIMITER_REGEX);

            String airportName = row[NAME_INDEX];
            int airportID = Integer.parseInt(row[AIRPORT_ID_INDEX]);
        }
    }
}
