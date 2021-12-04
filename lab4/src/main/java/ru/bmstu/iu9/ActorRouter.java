package ru.bmstu.iu9;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.routing.ActorRefRoutee;
import akka.routing.RoundRobinRoutingLogic;
import akka.routing.Routee;
import akka.routing.Router;

import java.util.ArrayList;

public class ActorRouter extends AbstractActor {
    private static final int NUM_WORKERS = 3;
    private final ActorRef actorStore;
    private final Router router;

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(GetResultMessage.class, this::tellStoreActor)
                .match(JsonRequest.class, this::readJson)
                .build();
    }

    private void readJson(JsonRequest jsonRequest) {
        for (JsonTest test: jsonRequest.getTests()) {
            router.route(new RunTestMessage(
                    jsonRequest.getPackageId(),
                    jsonRequest.getJsScript(),
                    jsonRequest.getFunctionName(),
                    test.getExpectedResult(),
                    test.getTestName(),
                    test.getParams()
            ), actorStore);
        }
    }

    private void tellStoreActor(GetResultMessage msg) {
        actorStore.tell(msg, sender());
    }

    public ActorRouter() {
        actorStore = getContext().actorOf(Props.create(ActorStore.class));
        getContext().watch(actorStore);

        ArrayList<Routee> workers = new ArrayList<>();
        for (int i = 0; i < NUM_WORKERS; i++) {
            ActorRef worker = getContext().actorOf(Props.create(ActorRunTest.class));
            getContext().watch(worker);
            workers.add(new ActorRefRoutee(worker));
        }

        router = new Router(new RoundRobinRoutingLogic(), workers);
    }
}
