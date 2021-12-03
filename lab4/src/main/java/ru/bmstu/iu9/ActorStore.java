package ru.bmstu.iu9;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import java.util.ArrayList;
import java.util.HashMap;

public class ActorStore extends AbstractActor {
    private Map<Integer, ArrayList<String>> testResults = new HashMap<>();

    @Override
    public Receive createRecieve() {
        return ReceiveBuilder.create()
    }

    private void storeTestResult(StoreMessage testResult) {

    }
}
