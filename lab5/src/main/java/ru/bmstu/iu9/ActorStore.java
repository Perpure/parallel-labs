package ru.bmstu.iu9;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

public class ActorStore extends AbstractActor {


    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(GetMessage.class, )
                .build();
    }

    private void getResult(GetMessage msg) {
        
    }

}
