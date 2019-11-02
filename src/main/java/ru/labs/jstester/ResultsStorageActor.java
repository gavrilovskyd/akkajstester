package ru.labs.jstester;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.pf.ReceiveBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ResultsStorage extends AbstractActor {
    private HashMap<String, List<TestResult>> innerStorage = new HashMap<>();
    private LoggingAdapter logger = Logging.getLogger(getContext().getSystem(), this);

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(TestResult.class, r -> {
                    List<TestResult> results = innerStorage.get(r.getTest().getPackageID());

                    if (results == null) {
                        results = new ArrayList<>();
                        results.add(r);
                        innerStorage.put(r.getTest().getPackageID(), results);
                    } else {
                        results.add(r);
                    }
                })
                .match(ResultRequest.class, r -> {
                    List<TestResult> results = innerStorage.get(r.getPackageID());

                    if (results == null) {
                        getSender().tell(new TestResult[]{}, getSelf());
                    } else {
                        getSender().tell(results.toArray(new TestResult[0]), getSelf());
                    }
                }).matchAny(o -> { logger.warning("got unknown message: {}", o.getClass().toString()); })
                .build();
    }
}
