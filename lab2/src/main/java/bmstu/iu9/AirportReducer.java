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
        double min = Double.MAX_VALUE, max = Double.MIN_VALUE, sum = 0;
        int count = 0;
        while (iter.hasNext()) {
            double delay = Double.parseDouble(iter.next().toString());
            min = Double.min(min, delay);
            max = Double.max(max, delay);
            sum += delay;
            count++;
        }
        if (count > 0) {
            Text outValue = new Text(min + ", " + max + ", " + sum / count);
            context.write(airportName, outValue);
        }
    }
}
