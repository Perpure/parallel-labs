package ru.bmstu.iu9;

import akka.Done;
import akka.actor.ActorRef;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.server.Route;
import akka.pattern.Patterns;

import java.util.concurrent.CompletionStage;
import java.util.concurrent.Future;

public class CreateRouterInstance {
    private ActorRef router;

    public CreateRouterInstance(ActorRef router) {
        this.router = router;
    }


    public Route createRoute() {

        return route(
                get(() -> pathPrefix("packageId", (id) -> {
                    Future<Object> response = Patterns.ask(ActorRouter)
                })),
                post(() ->
                        path("create-order", () ->
                                entity(Jackson.unmarshaller(Order.class), order -> {
                                    CompletionStage<Done> futureSaved = saveOrder(order);
                                    return onSuccess(futureSaved, done ->
                                            complete("order created")
                                    );
                                })))
        );
    }
}
