package ru.bmstu.iu9;

import akka.Done;
import akka.actor.ActorRef;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.server.Route;
import akka.pattern.Patterns;
import akka.util.Timeout;
import scala.concurrent.Future;

import java.time.Duration;
import java.util.concurrent.CompletionStage;

import static akka.http.javadsl.server.Directives.*;

public class CreateRouterInstance {
    private final ActorRef router;

    public CreateRouterInstance(ActorRef router) {
        this.router = router;
    }


    public Route createRoute() {

        return route(
                get(() -> parameter("packageId", (id) -> {
                    Future<Object> response = Patterns.ask(
                            router,
                            new GetStoredMessage(id),
                            Timeout.create(Duration.ofSeconds(15)));
                    return completeOKWithFuture(response, Jackson.marshaller());
                })),
                post(() -> entity(Jackson.unmarshaller(JsonRequest.class), (jsonRequest) -> {
                    router.tell(jsonRequest, ActorRef.noSender());
                    return complete("Request received");
                }))
        );
    }
}
