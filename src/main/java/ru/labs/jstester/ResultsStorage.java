package ru.labs.jstester;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import java.util.HashMap;

public class ResultsStorage extends AbstractActor {
    private HashMap<String, TestResult[]> innerStorage = new HashMap<>();

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(TestResult.class, r -> {
                    innerStorage.put(r.getTest()., m.getValue());
                    System.out.println(String.format("message: K: %s, V: %s", m.getKey(), m.getValue()));
                })
                .build();
    }
}
