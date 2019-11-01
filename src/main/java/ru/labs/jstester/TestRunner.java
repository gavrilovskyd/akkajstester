package ru.labs.jstester;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import java.util.ArrayList;
import java.util.List;

public class TestRunner extends AbstractActor {
    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(Test.class, r -> {

                })
                .build();
    }
}
