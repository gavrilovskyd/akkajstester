package ru.labs.jstester;

import akka.http.javadsl.server.AllDirectives;
import akka.http.javadsl.server.Route;

public class TesterRoutes extends AllDirectives {
    public TesterRoutes() {}

    public Route routes() {
        return route(
                path("submits"), () ->
                        path(
                                get(() -> { return complete("GET submits"); })

                        )
        )
    }
}
