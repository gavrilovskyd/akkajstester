package ru.labs.jstester;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.ArrayList;
import java.util.List;

public class TestRunner extends AbstractActor {
    private final static String JS_ENGINE = "nashorn";

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(Test.class, r -> {
                    ScriptEngine engine = new ScriptEngineManager().getEngineByName(JS_ENGINE);
                })
                .build();
    }
}
