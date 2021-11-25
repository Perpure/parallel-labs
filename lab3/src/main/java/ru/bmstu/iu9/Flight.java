package ru.bmstu.iu9;

import java.io.Serializable;

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

    public int getAirportOrigId() {
        return airportOrigId;
    }

    public void setAirportOrigId(int airportOrigId) {
        this.airportOrigId = airportOrigId;
    }

    public int getAirportDestId() {
        return airportDestId;
    }

    public void setAirportDestId(int airportDestId) {
        this.airportDestId = airportDestId;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void setCancelled(boolean cancelled) {
        isCancelled = cancelled;
    }

    public float getDelay() {
        return delay;
    }

    public void setDelay(float delay) {
        this.delay = delay;
    }
}
