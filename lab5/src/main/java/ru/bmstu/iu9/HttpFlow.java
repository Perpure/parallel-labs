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
import akka.stream.javadsl.Sink;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class HttpFlow {
    public static final String TEST_URL_ARG_NAME = "testUrl";
    public static final String COUNT_ARG_NAME = "count";
    public static final Integer NUM_WORKERS = 2;
    public static final Integer TIMEOUT_SECS = 10;

    public static Flow<HttpRequest, HttpResponse, NotUsed> httpFlow(
            ActorMaterializer materializer, ActorRef actor
    ) {
        return Flow.of(HttpRequest.class)
                .map(request -> {
                    Query query = request.getUri().query();
                    return new Pair<>(
                            query.getOrElse(TEST_URL_ARG_NAME, "http://rambler.ru"),
                            Integer.parseInt(query.getOrElse(COUNT_ARG_NAME, "1"))
                    );
                })
                .mapAsync(NUM_WORKERS, request -> Patterns.ask(
                        actor,
                        new GetMessage(request.first()),
                        Duration.ofSeconds(TIMEOUT_SECS)
                    )
                .thenCompose(result_time -> {
                    if ((int) result_time != -1) {
                        return CompletableFuture.completedFuture(
                                new Pair<>(
                                        request.first(),
                                        (int) result_time
                                )
                        );
                    }
                    Sink<Pair<String, Integer>, CompletionStage<Integer>> testSink = Flow
                            

                })


                )
    }
}
