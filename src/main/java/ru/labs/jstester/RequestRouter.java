package ru.labs.jstester;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import akka.routing.BalancingPool;

public class RequestRouter extends AbstractActor {
    private ActorRef resultStorage;
    private ActorRef runnersPool;

    public RequestRouter() {
        this.resultStorage = getContext().actorOf(Props.create(ResultsStorage.class), "result-storage");

    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(TestRequest.class, task -> {

                })
                .build();
    }
}
