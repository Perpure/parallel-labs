package ru.bmstu.iu9;

import scala.Tuple2;

public class FlightsParser {
    private static final int AIRPORT_ORIG_ID_INDEX = 11;
    private static final int AIRPORT_DEST_ID_INDEX = 14;
    private static final int DELAY_INDEX = 18;
    private static final int CANCELLED_INDEX = 19;
    public static final String FLIGHT_DELIMETER = ",";

    public static Tuple2<Tuple2<Integer, Integer>, Flight> parseFlights(String rowRaw){
        String[] row = rowRaw.split(FLIGHT_DELIMETER);

        String delayRaw = row[DELAY_INDEX];
        float delayTime = 0;
        if (!delayRaw.isEmpty()) {
            delayTime = Float.parseFloat(delayRaw);
        }

        String cancelledRaw = row[CANCELLED_INDEX];
        boolean isCanceled = Float.parseFloat(cancelledRaw) == 1;

        int airportOrigId = Integer.parseInt(row[AIRPORT_ORIG_ID_INDEX]);
        int airportDestId = Integer.parseInt(row[AIRPORT_DEST_ID_INDEX]);
        return new Tuple2<>(new Tuple2<>(airportOrigId, airportDestId),
                            new Flight(delayTime, isCanceled, airportOrigId, airportDestId));
    }

}
