package ru.bmstu.iu9;

import scala.Serializable;

public class FlightsInfo implements Serializable {
    private float maxDelay;
    private int delayedCount;
    private int cancelledCount;
    private  int totalCount;

    public FlightsInfo(float maxDelay, int delayedCount, int cancelledCount, int totalCount) {
        this.maxDelay = maxDelay;
        this.delayedCount = delayedCount;
        this.cancelledCount = cancelledCount;
        this.totalCount = totalCount;
    }

    public static FlightsInfo createInfo(Flight flight) {
        int isDelayed = flight.getDelay() > 0 ? 1 : 0;
        int isCancelled = flight.isCancelled() ? 1 : 0;
        return new FlightsInfo(flight.getDelay(), isDelayed, isCancelled, 1);
    }

    public static FlightsInfo updateInfo(FlightsInfo info, Flight flight) {
        if (flight.getDelay() > 0) {
            info.incDelayedCount();
        }
        if (flight.isCancelled()) {
            info.incCancelledCount();
        }
        
    }
    public static mergeInfo

    public void incDelayedCount() {
        this.delayedCount++;
    }

    public void incCancelledCount() {
        this.cancelledCount++;
    }

    public void updMaxDelay(float delay) {
        if (delay > this.maxDelay) {
            this.maxDelay = delay;
        }
    }
}
