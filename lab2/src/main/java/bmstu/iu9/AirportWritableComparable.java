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

    public int getTypeFlag() {
        return typeFlag;
    }

    public void setTypeFlag(int typeFlag) {
        this.typeFlag = typeFlag;
    }

    public int getAirportID() {
        return airportID;
    }

    public void setAirportID(int airportID) {
        this.airportID = airportID;
    }

    public AirportWritableComparable() {
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

    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.airportID = dataInput.readInt();
        this.typeFlag = dataInput.readInt();
    }
}
