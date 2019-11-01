package ru.labs.jstester;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import java.util.ArrayList;
import java.util.List;

public class TestRunner extends AbstractActor {
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
                        getSender().tell(results.toArray(), getSelf()); // TODO: add sort by innerID
                    }
                })
                .build();
    }
}
