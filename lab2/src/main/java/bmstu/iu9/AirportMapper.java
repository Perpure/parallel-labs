package bmstu.iu9;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class AirportMapper extends Mapper<LongWritable, Text, AirportWritableComparable, Text> {
    private static String DELIMITER_REGEX = "\",\"";
    private static int AIRPORT_ID_INDEX = 0;
    private static int NAME_INDEX = 1;
    private static int AIRPORT_TYPE = 0;

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        if (key.get() != 0) {
            String[] row = value.toString().split(DELIMITER_REGEX);
            String airportName = row[NAME_INDEX].substring(1);
            String airportIDRaw = row[AIRPORT_ID_INDEX];
            airportIDRaw = airportIDRaw.substring(0, airportIDRaw.length() - 1);
            int airportID = Integer.parseInt(airportIDRaw);
            context.write(new AirportWritableComparable(airportID, AIRPORT_TYPE),
                          new IntWritable(delay));
        }
    }
}
