package ru.labs.jstester;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;

public class RequestRouter extends AbstractActor {
    private ActorRef resultStorage;

    public RequestRouter(ActorSystem system) {
        system.actorOf(Props.create(ResultsStorage.class));
    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(TestRequest.class, task -> {

                })
                .build();
    }
}
