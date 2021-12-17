package ru.bmstu.iu9;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import java.util.ArrayList;

public class ActorConfig extends AbstractActor {
    private ArrayList<String> servers;


    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()

                .build();
    }

    private void getServers(ServersMessage msg) {
        servers = msg.getServers();
    }

    private void getRandomServer() {
        
    }
}
