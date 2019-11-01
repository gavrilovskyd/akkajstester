package ru.labs.jstester;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class TestRunner extends AbstractActor {
    private final static String JS_ENGINE = "nashorn";

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(TestTask.class, task -> {
                    ScriptEngine engine = new ScriptEngineManager().getEngineByName(JS_ENGINE);
                    engine.eval(task.getJsCode());
                    Invocable invocable = (Invocable)engine;
                    invocable.invokeFunction(task.getFunctionName(), task.getTest().getParams()).toString();
                })
                .build();
    }
}
