package ru.bmstu.iu9;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ActorStore extends AbstractActor {
    private Map<Integer, ArrayList<String>> testResults = new HashMap<>();

    @Override
    public Receive createRecieve() {
        return ReceiveBuilder.create();
    }

    private void storeTestResult(StoreMessage testResult) {
        if (testResults.containsKey(testResult.getPackageId())) {
            testResults.get(testResult.getPackageId()).add(testResult.getTestResult());
        } else {
            testResults.put(testResult.getPackageId(), new ArrayList<>(testResult.getTestResult(),));
        }
    }
}
