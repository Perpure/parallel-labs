package ru.bmstu.iu9;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.routing.Routee;

import java.util.ArrayList;

public class ActorRouter extends AbstractActor {
    private static final int NUM_WORKERS = 3;

    @Override
    public Receive createReceive() {
        return null;
    }

    public ActorRouter() {
        ActorRef actorStore = getContext().actorOf(Props.create(ActorStore.class));
        getContext().watch(actorStore);

        ArrayList<Routee> workers = new ArrayList<>();
        for (int i = 0; i < NUM_WORKERS; i++) {
            ActorRef worker = getContext().actorOf(Props.create(ActorRunTest.class));
            
        }
    }
}
