package ru.bmstu.iu9;

import akka.actor.AbstractActor;

public class RouterActor extends AbstractActor {
    private static final int NUM_WORKERS = 3;

    @Override
    public Receive createReceive() {
        return null;
    }
}
