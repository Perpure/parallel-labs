package ru.bmstu.iu9;

import java.io.Serializable;

public class Flight implements Serializable {
    private final float delay;
    private final boolean isCancelled;


    public Flight(float delay, boolean isCancelled) {
        this.delay = delay;
        this.isCancelled = isCancelled;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public float getDelay() {
        return delay;
    }
}
