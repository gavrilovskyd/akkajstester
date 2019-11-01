package ru.labs.jstester;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.server.Route;

public class HTTPServer {
    private TesterRoutes testerRoutes;

    public HTTPServer(ActorSystem system) {
        system.actorOf(Props.create(RequestRouter.class), "request-router");
        this.testerRoutes = new TesterRoutes(requestRouter);
    }

    public Route createRoute() {
        return testerRoutes.routes();
    }
}
