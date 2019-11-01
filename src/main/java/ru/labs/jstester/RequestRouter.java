package ru.labs.jstester;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.japi.pf.ReceiveBuilder;

public class RequestRouter extends AbstractActor {
    private ActorRef resultStorage;

    public RequestRouter(ActorRef resultStorage) {
        this.resultStorage = resultStorage;
    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(TestRequest.class, task -> {

                })
                .build();
    }
}
