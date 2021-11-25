package ru.bmstu.iu9;

import java.io.Serializable;

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
        int newDelayedCount = info.delayedCount + isDelayed;
        int newCancelledCount = info.cancelledCount + isCancelled;
        float newMaxDelay = Float.max(info.maxDelay, flight.getDelay());
        return new FlightsInfo(newMaxDelay, newDelayedCount, newCancelledCount, info.totalCount + 1);
    }

    public static FlightsInfo mergeInfo(FlightsInfo info1, FlightsInfo info2) {
        return new FlightsInfo(
                Float.max(info1.maxDelay, info2.maxDelay),
                info1.delayedCount + info2.delayedCount,
                info1.cancelledCount + info2.cancelledCount,
                info1.totalCount + info2.totalCount
        );
    }

}
