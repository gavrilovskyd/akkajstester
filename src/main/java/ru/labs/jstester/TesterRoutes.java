package ru.labs.jstester;

import akka.http.javadsl.server.AllDirectives;
import akka.http.javadsl.server.PathMatchers;
import akka.http.javadsl.server.Route;

public class TesterRoutes extends AllDirectives {
    public TesterRoutes() {}

    public Route routes() {
        return route(
                path("submits", () ->
                        route(
                                path(PathMatchers.segment(), packageID -> route(
                                            getSubmit(packageID)
                                        )
                                ),
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
