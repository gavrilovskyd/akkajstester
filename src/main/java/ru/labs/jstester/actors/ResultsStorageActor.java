package ru.labs.jstester.actors;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.pf.ReceiveBuilder;
import ru.labs.jstester.messages.HttpRequests.ResultRequest;
import ru.labs.jstester.messages.HttpResponse.TestResultResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ResultsStorageActor extends AbstractActor {
    private HashMap<String, List<TestResultResponse>> innerStorage = new HashMap<>();
    private LoggingAdapter logger = Logging.getLogger(getContext().getSystem(), this);

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(TestResultResponse.class, r -> {
                    List<TestResultResponse> results = innerStorage.get(r.getTest().getPackageID());

                    if (results == null) {
                        results = new ArrayList<>();
                        results.add(r);
                        innerStorage.put(r.getTest().getPackageID(), results);
                    } else {
                        results.add(r);
                    }
                })
                .match(ResultRequest.class, r -> {
                    List<TestResultResponse> results = innerStorage.get(r.getPackageID());

                    if (results == null) {
                        getSender().tell(new TestResultResponse[]{}, getSelf());
                    } else {
                        getSender().tell(results.toArray(new TestResultResponse[0]), getSelf());
                    }
                })
                .matchAny(o -> { logger.warning("got unknown message: {}", o.getClass().toString()); })
                .build();
    }
}
