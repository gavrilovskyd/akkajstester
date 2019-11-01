package ru.labs.jstester;

import akka.http.javadsl.server.Route;

public class HTTPServer {
    private TesterRoutes testerRoutes;


    public HTTPServer() {
        testerRoutes = new TesterRoutes();
    }

    public Route createRoute() {
        return testerRoutes.routes();
    }
}
