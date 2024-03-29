package ru.labs.jstester.core;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.server.Route;
import ru.labs.jstester.actors.RequestRouterActor;

public class HTTPServer {
    private TesterRoutes testerRoutes;

    public HTTPServer(ActorSystem system) {
        ActorRef requestRouter = system.actorOf(Props.create(RequestRouterActor.class), "request-router");
        this.testerRoutes = new TesterRoutes(system, requestRouter);
    }

    public Route createRoute() {
        return testerRoutes.routes();
    }
}
