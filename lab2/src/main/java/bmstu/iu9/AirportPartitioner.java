package bmstu.iu9;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class AirportPartitioner extends Partitioner<AirportWritableComparable, Text> {

    public int get

    @Override
    public int getPartition(AirportWritableComparable airportWritableComparable, Text text, int i) {
        return 0;
    }
}
