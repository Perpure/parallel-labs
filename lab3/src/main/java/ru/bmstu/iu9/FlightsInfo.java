package ru.bmstu.iu9;

import scala.Serializable;

public class FlightsInfo implements Serializable {
    private int maxDelay;
    private int lateCount;
    private int cancelledCount;

    public FlightsInfo(int maxDelay, int lateCount, int cancelledCount) {
        this.maxDelay = maxDelay;
        this.lateCount = lateCount;
        this.cancelledCount = cancelledCount;
    }
}
