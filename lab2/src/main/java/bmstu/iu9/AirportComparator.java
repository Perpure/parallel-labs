package bmstu.iu9;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class AirportComparator extends WritableComparator {

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        AirportWritableComparable airport1 = (AirportWritableComparable) a;
        AirportWritableComparable airport2 = (AirportWritableComparable) b;
        return Integer.compare(airport1.getAirportID(), airport2.getAirportID());
    }
}
