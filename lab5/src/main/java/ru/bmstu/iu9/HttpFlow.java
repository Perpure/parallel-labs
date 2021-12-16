package ru.bmstu.iu9;

import akka.NotUsed;
import akka.actor.ActorRef;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.model.Query;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import javafx.util.Pair;

public class HttpFlow {
    public static final String TEST_URL_ARG_NAME = "testUrl";
    public static final String COUNT_ARG_NAME = "count";

    public static Flow<HttpRequest, HttpResponse, NotUsed> httpFlow(
            ActorMaterializer materializer, ActorRef actor
    ) {
        return Flow.of(HttpRequest.class)
                .map(req -> {
                    Query query = req.getUri().query();
                    return new Pair<>(
                            query.get(TEST_URL_ARG_NAME),
                            Integer.parseInt(query.get(COUNT_ARG_NAME))
                    )
                })
    }
}
