package ru.bmstu.iu9;

import akka.actor.ActorRef;
import akka.http.javadsl.Http;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.server.Route;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static akka.http.javadsl.server.Directives.*;

public class AnonymousRouter {
    private final ActorRef actorConfig;
    private final Http httpClient;

    public AnonymousRouter(ActorRef actorConfig, Http httpClient) {
        this.actorConfig = actorConfig;
        this.httpClient = httpClient;
    }

    public Route createRoute() {

        return route(
                get(() -> parameter("url", (url) -> parameter("count", (countRaw) -> {
                    int count = Integer.parseInt(countRaw);
                    if (count == 0) {
                        return completeWithFuture(httpClient.singleRequest(HttpRequest.create(url)));
                    } else {

                    }
                })))
        );
    }

    private CompletionStage

}
