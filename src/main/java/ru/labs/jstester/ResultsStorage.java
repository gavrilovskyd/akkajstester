package ru.labs.jstester;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import java.util.HashMap;
import java.util.List;

public class ResultsStorage extends AbstractActor {
    private HashMap<String, List<TestResult>> innerStorage = new HashMap<>();

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(TestResult.class, r -> {
                    TestResult[] results = innerStorage.get(r.getTest().getPackageID());

                    if (results == null) {
                        results = new TestResult[]{r};
                        innerStorage.put(r.getTest().getPackageID(), results);
                    } else {
                        results
                    }


                })
                .build();
    }
}
