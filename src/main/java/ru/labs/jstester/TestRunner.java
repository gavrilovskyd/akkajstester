package ru.labs.jstester;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.japi.pf.ReceiveBuilder;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class TestRunner extends AbstractActor {
    private final static String JS_ENGINE = "nashorn";

    private ActorRef resultStorage;

    public TestRunner(ActorRef resultStorage) {
        this.resultStorage = resultStorage;
    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(TestTask.class, task -> {
                    ScriptEngine engine = new ScriptEngineManager().getEngineByName(JS_ENGINE);
                    engine.eval(task.getJsCode());

                    Invocable invocable = (Invocable)engine;
                    String output = invocable.invokeFunction(
                            task.getFunctionName(), task.getTest().getParams()
                    ).toString(); // TODO: add timeout

                    String status = (output.equals(task.getTest().getExpectedResult()) ?
                            TestResult.OK_STATUS : TestResult.WRONG_ANSWER_STATUS );

                    this.resultStorage.tell(new TestResult(task.getTest(), output));
                })
                .build();
    }
}
