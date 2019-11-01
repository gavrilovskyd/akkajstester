package ru.labs.jstester;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.dispatch.Mapper;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.model.*;
import akka.http.javadsl.server.AllDirectives;
import akka.http.javadsl.server.PathMatchers;
import akka.http.javadsl.server.Route;
import akka.pattern.Patterns;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import scala.concurrent.Future;

public class TesterRoutes extends AllDirectives {
    private int TIMEOUT_MS = 5000;

    private ActorRef requestRouter;
    private ActorSystem system;

    public TesterRoutes(ActorSystem system, ActorRef requestRouter) {
        this.system = system;
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
                              if (!(parameter instanceof TestResult[])) {
                                  return HttpResponse.create().withStatus(StatusCodes.INTERNAL_SERVER_ERROR)
                                          .withEntity("Wooops!!!");
                              }

                              ObjectMapper mapper = new ObjectMapper();
                              TestResult[] results = ((TestResult[])parameter);
                              if (results.length == 0) {
                                  return HttpResponse.create().withStatus(StatusCodes.NOT_FOUND)
                                          .withEntity("Submit does not found");
                              }

                              try {
                                  System.out.println(parameter);
                                  byte[] marshaled = mapper.writeValueAsBytes(parameter);
                                  return HttpResponse.create().withStatus(StatusCodes.OK)
                                          .withEntity(HttpEntities.create(ContentTypes.APPLICATION_JSON, marshaled));
                              } catch (JsonProcessingException e) {
                                  return HttpResponse.create().withStatus(StatusCodes.INTERNAL_SERVER_ERROR)
                                          .withEntity("Wooops!!!");
                              }

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
