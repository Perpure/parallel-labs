package ru.bmstu.iu9;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import java.util.HashMap;
import java.util.Map;

public class ActorStore extends AbstractActor {
    private Map<String, Integer> storedTimes = new HashMap<>();

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(GetMessage.class, )
                .build();
    }

    private void getResult(GetMessage msg) {
        String url = msg.getUrl();
        if (storedTimes.containsKey(url)) {
            
        }
    }

}
