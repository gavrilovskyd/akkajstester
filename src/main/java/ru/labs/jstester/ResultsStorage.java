package ru.labs.jstester;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import java.util.HashMap;

public class ResultsStorage extends AbstractActor {
    private HashMap<String, String> storage = new HashMap<>();

    public static final class StoreMessage {
        private String key;
        private String value;

        public StoreMessage(String k, String v) {
            this.key = k;
            this.value = v;
        }

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }
    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(StoreMessage.class, m -> {
                    storage.put(m.getKey(), m.getValue());
                    System.out.println(String.format("message: K: %s, V: %s", m.getKey(), m.getValue()));
                })
                .build();
    }
}
