package bmstu.iu9;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FlightMapper extends Mapper<LongWritable, Text, AirportWritableComparable, Text> {

    private static String DELIMITER_REGEX = ",";
    private static int AIRPORT_ID_INDEX = 14;
    private static int DELAY_INDEX = 17;
    private static int FLIGHT_TYPE = 1;

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        if (key.get() != 0) {
            String[] row = value.toString().split(DELIMITER_REGEX);
            String delayRaw = row[DELAY_INDEX];
            if (delayRaw.isEmpty()) return;
            int airportID = Integer.parseInt(row[AIRPORT_ID_INDEX]);
            context.write(new AirportWritableComparable(airportID, FLIGHT_TYPE),
                          new Text(delayRaw));
        }
    }
}
