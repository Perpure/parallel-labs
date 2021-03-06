package ru.bmstu.iu9;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ActorStore extends AbstractActor {
    private Map<String, ArrayList<String>> testsResults = new HashMap<>();

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(GetStoredMessage.class, this::getResult)
                .match(StoreMessage.class, this::storeTestResult)
                .build();
    }

    private void storeTestResult(StoreMessage testResult) {
        if (testsResults.containsKey(testResult.getPackageId())) {
            testsResults.get(testResult.getPackageId()).add(testResult.getTestResult());
        } else {
            ArrayList<String> newTestList = new ArrayList<>();
            newTestList.add(testResult.getTestResult());
            testsResults.put(testResult.getPackageId(), newTestList);
        }
    }

    private void getResult(GetStoredMessage query) {
        System.out.println(testsResults);
        ArrayList<String> testResults = testsResults.get(query.getPackageId());
        sender().tell(new TestResultsMessage(query.getPackageId(), testResults),
                      getContext().getParent());
    }


}
