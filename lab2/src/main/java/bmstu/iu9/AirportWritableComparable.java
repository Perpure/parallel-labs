package bmstu.iu9;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class AirportWritableComparable implements WritableComparable {

    private int airportID;
    private int typeFlag;

    public AirportWritableComparable(int airportID, int typeFlag) {
        this.airportID = airportID;
        this.typeFlag = typeFlag;
    }

    @Override
    public int compareTo(Object o) {
        AirportWritableComparable airport = (AirportWritableComparable) o;
        if (this.airportID > airport.airportID) return 1;
        if (this.airportID < airport.airportID) return -1;
        return Integer.compare(this.typeFlag, airport.typeFlag);
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(airportID);
        dataOutput.writeInt(typeFlag);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.airportID = dataInput.readInt();
        this.typeFlag = dataInput.readInt();
    }

    public int hashCode() {
        return
    }
}
