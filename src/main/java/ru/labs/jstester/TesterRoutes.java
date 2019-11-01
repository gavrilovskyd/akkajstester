package ru.labs.jstester;

import akka.actor.ActorRef;
import akka.http.javadsl.server.AllDirectives;
import akka.http.javadsl.server.PathMatchers;
import akka.http.javadsl.server.Route;
import akka.pattern.Patterns;
import akka.pattern.Patterns$;

import java.util.concurrent.Future;

public class TesterRoutes extends AllDirectives {
    private int TIMEOUT_MS = 5000;

    private ActorRef requestRouter;

    public TesterRoutes(ActorRef requestRouter) {
        this.requestRouter = requestRouter;
    }

    public Route routes() {
        return route(
                pathPrefix("submits", () ->
                        route(
                                path(PathMatchers.segment(), packageID -> route(getSubmit(packageID))),
                                postSubmit()
                        )
                )
        );
    }

    private Route getSubmit(String packageID) {
        return get(()-> {
              Future<Object> possibleResult = Patterns.ask(this.requestRouter, new ResultRequest(packageID), TIMEOUT_MS);
              return onSuccess(() -> )
    });
    }

    private Route postSubmit() {
        return pathEnd(() ->
                route(
                        post(() -> { return complete("POST submits"); })
                )
        );
    }
}
