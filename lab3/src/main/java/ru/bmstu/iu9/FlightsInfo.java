package ru.bmstu.iu9;

import scala.Serializable;

public class FlightsInfo implements Serializable {
    private int maxDelay;
    private int lateCount;
    private int cancelledCount;
    private  int totalCount;

    public FlightsInfo(int maxDelay, int lateCount, int cancelledCount, int totalCount) {
        this.maxDelay = maxDelay;
        this.lateCount = lateCount;
        this.cancelledCount = cancelledCount;
        this.totalCount = totalCount;
    }

    public static createInfo () {
        
    }
    public static updateInfo
    public static mergeInfo
}
