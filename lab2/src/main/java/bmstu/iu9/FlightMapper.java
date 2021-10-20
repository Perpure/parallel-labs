package bmstu.iu9;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FlightMapper extends Mapper<LongWritable, Text, AirportWritableComparable, FlightMapper> {

    private static String DELIMITER_REGEX = ",";
    private static int AIRPORT_ID_INDEX = 14;
    private static int DELAY_NEW_INDEX = 18;

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        if (key.get() != 0) {
            String[] row = value.toString().split(DELIMITER_REGEX);

        }
    }
}
