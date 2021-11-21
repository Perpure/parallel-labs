package ru.bmstu.iu9;

import scala.Tuple2;

public class FlightsParser {
    private static final int AIRPORT_ORIGIN_ID_INDEX = 11;
    private static final int AIRPORT_DEST_ID_INDEX = 14;
    private static final int DELAY_INDEX = 18;
    private static final int CANCELLED_INDEX = 19;
    public static final String FLIGHT_DELIMETER = ",";

    public static Tuple2<> parseFlights(String rowRaw){
        String[] row = rowRaw.split(FLIGHT_DELIMETER);

        String delayRaw = row[DELAY_INDEX];
        if (delayRaw.isEmpty()) {
            float delayTime = 0;
        } else {
            float delayTime = Float.parseFloat(delayRaw);
        }

        String cancelledRaw = row[CANCELLED_INDEX];
        boolean isCanceled = Float.parseFloat(cancelledRaw) == 1;

        int airportOriginId = Integer.parseInt(row[AIRPORT_ORIGIN_ID_INDEX]);
        int airportDestId = Integer.parseInt(row[AIRPORT_DEST_ID_INDEX]);
    }

}
