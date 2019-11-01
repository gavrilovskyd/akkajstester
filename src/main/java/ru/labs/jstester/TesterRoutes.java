package ru.labs.jstester;

import akka.actor.ActorRef;
import akka.http.javadsl.server.AllDirectives;
import akka.http.javadsl.server.PathMatchers;
import akka.http.javadsl.server.Route;

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
            return complete(String.format("GET submits %s", packageID));
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
