package ru.bmstu.iu9;

import akka.actor.ActorRef;

public class AnonymousRouter {
    private final ActorRef actorConfig;

    public AnonymousRouter(ActorRef actorConfig) {
        this.actorConfig = actorConfig;
    }

    
}
