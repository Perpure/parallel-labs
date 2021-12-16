package ru.bmstu.iu9;

import akka.actor.AbstractActor;

import java.util.ArrayList;

public class ActorConfig extends AbstractActor {
    private ArrayList<String> servers;


    @Override
    public Receive createReceive() {
        return null;
    }
}
