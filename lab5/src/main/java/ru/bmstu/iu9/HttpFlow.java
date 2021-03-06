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
import akka.stream.javadsl.Keep;
import akka.stream.javadsl.Sink;
import akka.stream.javadsl.Source;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.Dsl;

import java.time.Duration;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class HttpFlow {
    public static final String TEST_URL_ARG_NAME = "testUrl";
    public static final String COUNT_ARG_NAME = "count";
    public static final Integer NUM_WORKERS = 2;
    public static final int TIMEOUT_SECS = 10;

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
            .thenCompose(resultTime -> {
                System.out.println(resultTime);
                if ((long) resultTime != -1) {
                    return CompletableFuture.completedFuture(
                            new Pair<>(
                                    request.first(),
                                    (long) resultTime
                            )
                    );
                }
                Sink<Pair<String, Integer>, CompletionStage<Long>> testSink =
                        Flow.<Pair<String, Integer>>create()
                                .mapConcat(msg ->
                                        Collections.nCopies(
                                                msg.second(),
                                                msg.first())
                                )
                                .mapAsync(request.second(), url -> {
                                    long startTime = System.currentTimeMillis();
                                    AsyncHttpClient client = Dsl.asyncHttpClient();
                                    return client.prepareGet(url)
                                            .execute()
                                            .toCompletableFuture()
                                            .thenApply(response -> System.currentTimeMillis() - startTime);
                                })
                                .toMat(Sink.fold(0L, Long::sum), Keep.right());
                return Source.from(Collections.singletonList(request))
                        .toMat(testSink, Keep.right())
                        .run(materializer)
                        .thenApply(allTime -> new Pair<>(request.first(), allTime / request.second()));

            })
        )
        .map(result -> {
            actor.tell(
                new StoreMessage(result.first(), result.second()), ActorRef.noSender());
                return HttpResponse.create().withEntity(result.first() + ": " + result.second() + "ms");
        });
    }
}
