package ru.labs.jstester;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.pf.ReceiveBuilder;
import akka.routing.BalancingPool;

public class RequestRouterActor extends AbstractActor {
    private ActorRef resultStorage;
    private ActorRef testRunnersPool;
    private LoggingAdapter logger = Logging.getLogger(getContext().getSystem(), this);

    public RequestRouterActor() {
        this.resultStorage = getContext().actorOf(
                Props.create(ResultsStorageActor.class), "result-storage");
        this.testRunnersPool = getContext().actorOf(
                new BalancingPool(5).props(Props.create(TestRunnerActor.class, this.resultStorage)), "test-runners");
    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(TestRequest.class, r -> {
                    for (Test test : r.getTests()) {
                        test.setPackageID(r.getPackageID());
                        testRunnersPool.tell(
                                new TestTask(r, test), getSelf());
                    }
                })
                .match(ResultRequest.class, r -> {
                    resultStorage.forward(r, getContext());
                }).matchAny(o -> { logger.warning("got unknown message: {}", o.getClass().toString()); })
                .build();
    }
}
