package ru.labs.jstester;

import akka.http.javadsl.server.AllDirectives;
import akka.http.javadsl.server.Route;

public class TesterRoutes extends AllDirectives {
    public TesterRoutes() {}

    public Route routes() {
        return route(
                path("submits", () ->
                        route(
                                get(() ->
                                        parameter("package_id")),
                                post(() -> { return complete("POST submits"); })
                        )
                )
        );
    }
}
