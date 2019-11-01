package ru.labs.jstester;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import java.util.HashMap;

public class StorageActor extends AbstractActor {
    private HashMap<String, String> storage = new HashMap<>();

    public static final class 

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create().build();
    }
}
