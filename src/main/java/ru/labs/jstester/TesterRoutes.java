package ru.labs.jstester;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Status;
import akka.dispatch.Mapper;
import akka.dispatch.OnComplete;
import akka.http.javadsl.Http;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.model.*;
import akka.http.javadsl.server.AllDirectives;
import akka.http.javadsl.server.PathMatchers;
import akka.http.javadsl.server.Route;
import akka.pattern.Patterns;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import scala.concurrent.Future;

import java.util.List;

public class TesterRoutes extends AllDirectives {
    private int TIMEOUT_MS = 5000;

    private ActorRef requestRouter;
    private ActorSystem system;

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
              Future<HttpResponse> possibleResponse = Patterns
                      .ask(requestRouter, new ResultRequest(packageID), TIMEOUT_MS)
                      .map(new Mapper<Object, HttpResponse>(){
                          @Override
                          public HttpResponse apply(Object parameter) {
                              HttpResponse resp = HttpResponse.create();
                              resp.withStatus(StatusCodes.NOT_FOUND)
                                      .withEntity("submit does not found");
                              return resp;

                              //ObjectMapper mapper = new ObjectMapper();
                              //if (parameter == null) {
                              //    resp.withStatus(StatusCodes.NOT_FOUND)
                              //            .withEntity("submit does not found");
                              //} else {
                              //    try {
                              //        byte[] marshaled = mapper.writeValueAsBytes(parameter);
                              //        resp.withStatus(StatusCodes.OK)
                              //                .withEntity(HttpEntities.create(ContentTypes.APPLICATION_JSON, marshaled));
                              //    } catch (JsonProcessingException e) {
                              //        resp.withStatus(StatusCodes.INTERNAL_SERVER_ERROR)
                              //                .withEntity("Unknown error");
                              //    }
                              //}
//
                              //return resp;
                          }
                      }, system.dispatcher());
              return completeWithFutureResponse(possibleResponse);
    });
    }

    private Route postSubmit() {
        return pathEnd(() ->
                route(
                        post(() ->
                                entity(Jackson.unmarshaller(TestRequest.class), r -> {
                                    requestRouter.tell(r, ActorRef.noSender());
                                    return complete(StatusCodes.OK, "Tests started!");
                                }))
                )
        );
    }
}
