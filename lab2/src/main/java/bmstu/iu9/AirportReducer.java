package bmstu.iu9;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

public class AirportReducer extends Reducer<AirportWritableComparable, Text, Text, Text> {

    @Override
    protected void reduce(AirportWritableComparable key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        Iterator<Text> iter = values.iterator();
        Text airportName = new Text(iter.next());
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE, count = 0, sum = 0;
        while (iter.hasNext()) {
            int delay = Integer.parseInt(iter.next().toString());
            min = Integer.min(min, delay);
            max = Integer.max(max, delay);
            sum += delay;
            count++;
        }
        if (count > 0) {
            Text outValue = new Text(min + ", " + max + ", " + sum / (double) count);
            context.write(airportName, outValue);
        }
    }
}
