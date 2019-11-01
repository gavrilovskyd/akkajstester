package ru.labs.jstester;

import akka.actor.ActorRef;
import akka.http.javadsl.server.AllDirectives;
import akka.http.javadsl.server.PathMatchers;
import akka.http.javadsl.server.Route;

import java.util.Optional;
import java.util.concurrent.CompletionStage;

public class TesterRoutes extends AllDirectives {
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
            CompletionStage<Optional<>>
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
