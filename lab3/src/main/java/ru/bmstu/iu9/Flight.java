package ru.bmstu.iu9;

import scala.Serializable;

public class Flight implements Serializable {
    private float delay;
    private boolean isCancelled;
    private int airportOrigId;
    private int airportDestId;


    public Flight(float delay, boolean isCancelled, int airportOrigId, int airportDestId) {
        this.delay = delay;
        this.isCancelled = isCancelled;
        this.airportOrigId = airportOrigId;
        this.airportDestId = airportDestId;
    }
}
