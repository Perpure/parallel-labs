package ru.bmstu.iu9;

import akka.actor.ActorRef;
import akka.http.javadsl.Http;

public class AnonymousRouter {
    private final ActorRef actorConfig;
    private final Http httpClient;

    public AnonymousRouter(ActorRef actorConfig, Http httpClient) {
        this.actorConfig = actorConfig;
        this.httpClient = httpClient;
    }

    

}
