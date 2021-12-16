package ru.bmstu.iu9;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.japi.pf.ReceiveBuilder;

import java.util.HashMap;
import java.util.Map;

public class ActorStore extends AbstractActor {
    private Map<String, Long> storedTimes = new HashMap<>();

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(GetMessage.class, this::getResult)

                .build();
    }

    private void getResult(GetMessage msg) {
        String url = msg.getUrl();
        if (storedTimes.containsKey(url)) {
            sender().tell(storedTimes.get(msg.getUrl()),
                    ActorRef.noSender());
        } else {
            sender().tell(-1,
                    ActorRef.noSender());
        }
    }

    private void storeTime(StoreMessage msg) {
        storedTimes.put(msg.getUrl(), msg.getTime());
    }
}
