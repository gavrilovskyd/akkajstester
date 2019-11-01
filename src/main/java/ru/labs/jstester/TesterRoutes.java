package ru.labs.jstester;

import akka.actor.ActorRef;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.model.StatusCode;
import akka.http.javadsl.model.StatusCodes;
import akka.http.javadsl.server.AllDirectives;
import akka.http.javadsl.server.PathMatchers;
import akka.http.javadsl.server.Route;
import akka.pattern.Patterns;
import scala.concurrent.Future;

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
              return completeOKWithFuture(possibleResult, Jackson.marshaller());
    });
    }

    private Route postSubmit() {
        return pathEnd(() ->
                route(
                        post(() ->
                                entity(Jackson.unmarshaller(TestRequest.class), r -> {
                                    this.requestRouter.tell(r, ActorRef.noSender());
                                    return complete(StatusCodes.OK, "Tests started!");
                                }))
                )
        );
    }
}
