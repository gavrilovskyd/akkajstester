package ru.labs.jstester;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ResultsStorage extends AbstractActor {
    private HashMap<String, List<TestResult>> innerStorage = new HashMap<>();

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
                        getSender().tell(results.sort((o1, o2) -> o1.getTest().getInnerID() - o2.getTest().getInnerID()););
                    }
                })
                .build();
    }
}
