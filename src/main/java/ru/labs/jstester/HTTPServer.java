package ru.labs.jstester;

import akka.actor.ActorRef;
import akka.http.javadsl.server.Route;

public class HTTPServer {
    private TesterRoutes testerRoutes;

    public HTTPServer(ActorRef requestRouter) {
        testerRoutes = new TesterRoutes(requestRouter);
    }

    public Route createRoute() {
        return testerRoutes.routes();
    }
}
