package ru.bmstu.iu9;

import akka.NotUsed;
import akka.actor.ActorRef;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.model.Query;
import akka.pattern.Patterns;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import akka.japi.Pair;

import java.time.Duration;

public class HttpFlow {
    public static final String TEST_URL_ARG_NAME = "testUrl";
    public static final String COUNT_ARG_NAME = "count";
    public static final Integer NUM_WORKERS = 2;
    public static final Integer TIMEOUT_SECS = 10;

    public static Flow<HttpRequest, HttpResponse, NotUsed> httpFlow(
            ActorMaterializer materializer, ActorRef actor
    ) {
        return Flow.of(HttpRequest.class)
                .map(req -> {
                    Query query = req.getUri().query();
                    return new Pair<>(
                            query.getOrElse(TEST_URL_ARG_NAME, "http://rambler.ru"),
                            Integer.parseInt(query.getOrElse(COUNT_ARG_NAME, "1"))
                    );
                })
                .mapAsync(NUM_WORKERS, req -> Patterns.ask(
                        actor,
                        new GetMessage(req.first()),
                        Duration.ofSeconds(TIMEOUT_SECS)
                    )
                .thenCompose(res -> {
                    if ((int) res != -1) {
                        
                    }
                })


                )
    }
}
