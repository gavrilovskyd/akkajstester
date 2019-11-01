package ru.labs.jstester;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import akka.routing.BalancingPool;

public class RequestRouter extends AbstractActor {
    private ActorRef resultStorage;
    private ActorRef testRunnersPool;

    public RequestRouter() {
        this.resultStorage = getContext().actorOf(
                Props.create(ResultsStorage.class), "result-storage");
        this.testRunnersPool = getContext().actorOf(
                new BalancingPool(5).props(Props.create(TestRunner.class)), "test-runners");
    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(TestRequest.class, r -> {
                    for (Test test : r.getTests()) {
                        testRunnersPool.tell(
                                new TestTask(r.getMeta(), test), getSelf());
                    }
                })
                .match(ResultRequest.class, r -> {
                    this.resultStorage.forward(r, getContext());
                })
                .build();
    }
}
