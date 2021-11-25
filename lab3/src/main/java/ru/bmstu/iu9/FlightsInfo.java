package ru.bmstu.iu9;

import scala.Serializable;

public class FlightsInfo implements Serializable {
    private float maxDelay;
    private int delayedCount;
    private int cancelledCount;
    private int totalCount;

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
        int isDelayed = flight.getDelay() > 0 ? 1 : 0;
        int isCancelled = flight.isCancelled() ? 1 : 0;
        int newDelayedCount = info.getDelayedCount() + isDelayed;
        int newCancelledCount = info.getCancelledCount() + isCancelled;
        float newMaxDelay = Float.max(info.maxDelay, flight.getDelay());
        return new FlightsInfo(newMaxDelay, newDelayedCount, newCancelledCount, info.getTotalCount() + 1)
    }

    public static FlightsInfo mergeInfo(FlightsInfo info1, FlightsInfo info2)

    public int getDelayedCount() {
        return this.delayedCount;
    }

    public int getCancelledCount() {
        return this.cancelledCount;
    }

    public int getTotalCount() {
        return this.totalCount;
    }


    public float getMaxDelay() {
        return this.maxDelay;
    }
}
