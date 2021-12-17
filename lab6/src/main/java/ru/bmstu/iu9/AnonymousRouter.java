package ru.bmstu.iu9;

import akka.actor.ActorRef;
import akka.http.javadsl.Http;
import akka.http.javadsl.server.Route;

public class AnonymousRouter {
    private final ActorRef actorConfig;
    private final Http httpClient;

    public AnonymousRouter(ActorRef actorConfig, Http httpClient) {
        this.actorConfig = actorConfig;
        this.httpClient = httpClient;
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
