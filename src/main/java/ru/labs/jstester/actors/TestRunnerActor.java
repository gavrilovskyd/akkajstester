package ru.labs.jstester.actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.pf.ReceiveBuilder;
import ru.labs.jstester.messages.HttpResponses.TestResultResponse;
import ru.labs.jstester.messages.TestTask;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class TestRunnerActor extends AbstractActor {
    private final static String JS_ENGINE = "nashorn";

    private ActorRef resultStorage;
    private LoggingAdapter logger = Logging.getLogger(getContext().getSystem(), this);

    public TestRunnerActor(ActorRef resultStorage) {
        this.resultStorage = resultStorage;
    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(TestTask.class, task -> {
                    ScriptEngine engine = new ScriptEngineManager().getEngineByName(JS_ENGINE);

                    String output;
                    String status;
                    try {
                        engine.eval(task.getMeta().getJsCode());
                        Invocable invocable = (Invocable)engine;
                        output = invocable.invokeFunction(
                                task.getMeta().getFunctionName(), task.getTest().getParams()
                        ).toString();

                        status = (output.equals(task.getTest().getExpectedResult()) ?
                                TestResultResponse.OK_STATUS : TestResultResponse.WRONG_ANSWER_STATUS );
                    } catch (ScriptException e) {
                        output = e.getMessage();
                        status = TestResultResponse.RUNTIME_ERROR_STATUS;
                    }

                    resultStorage.tell(new TestResultResponse(task.getTest(), output, status), getSelf());
                })
                .matchAny(o -> { logger.warning("got unknown message: {}", o.getClass().toString()); })
                .build();
    }
}
