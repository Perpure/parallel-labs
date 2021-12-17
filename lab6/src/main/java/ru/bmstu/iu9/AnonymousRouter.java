package ru.bmstu.iu9;

import akka.actor.ActorRef;
import akka.http.javadsl.Http;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.model.Query;
import akka.http.javadsl.model.Uri;
import akka.http.javadsl.server.Route;
import akka.japi.Pair;
import akka.pattern.Patterns;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static akka.http.javadsl.server.Directives.*;

public class AnonymousRouter {
    private final ActorRef actorConfig;
    private final Http httpClient;
    private final int TIMEOUT_SECS = 10;

    public AnonymousRouter(ActorRef actorConfig, Http httpClient) {
        this.actorConfig = actorConfig;
        this.httpClient = httpClient;
    }

    public Route createRoute() {

        return route(
                get(() -> parameter("url", (url) -> parameter("count", (countRaw) -> {
                    int count = Integer.parseInt(countRaw);
                    System.out.println(count);
                    if (count == 0) {
                        return completeWithFuture(httpClient.singleRequest(HttpRequest.create(url)));
                    } else {
                        return completeWithFuture(sendToRandomServer(url, count - 1));
                    }
                })))
        );
    }

    private CompletionStage<HttpResponse> sendToRandomServer(String url, Integer count) {
        return Patterns.ask(actorConfig, new EmptyMessage(), Duration.ofSeconds(TIMEOUT_SECS))
                .thenCompose(server -> {
                    System.out.println(server);
                    Uri uri = Uri.create((String) server)
                            .query(Query.create(
                                    Pair.create("url", url),
                                    Pair.create("count", count.toString())));
                    return httpClient.singleRequest(HttpRequest.create(String.valueOf(uri)));
                });
    }

}
