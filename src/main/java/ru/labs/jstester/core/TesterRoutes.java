package ru.labs.jstester.core;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.dispatch.Mapper;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.model.*;
import akka.http.javadsl.server.AllDirectives;
import akka.http.javadsl.server.PathMatchers;
import akka.http.javadsl.server.Route;
import akka.pattern.Patterns;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.labs.jstester.messages.HttpRequests.ResultRequest;
import ru.labs.jstester.messages.HttpRequests.TestRequest;
import ru.labs.jstester.messages.HttpResponses.TestResultResponse;
import ru.labs.jstester.messages.HttpResponses.TextResponse;
import scala.concurrent.Future;

public class TesterRoutes extends AllDirectives {
    private int TIMEOUT_MS = 5000;

    private ActorRef requestRouter;
    private ActorSystem system;
    private LoggingAdapter logger;

    public TesterRoutes(ActorSystem system, ActorRef requestRouter) {
        this.system = system;
        this.requestRouter = requestRouter;
        this.logger = Logging.getLogger(system, this);
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
                      .map((Object parameter) -> {
                          ObjectMapper mapper = new ObjectMapper();
                          try {
                              if (!(parameter instanceof TestResultResponse[])) {
                                  logger.error("wrong future parameter {}, expected TestResult[]",
                                          parameter.getClass().toString());
                                  return HttpResponse.create()
                                          .withStatus(StatusCodes.INTERNAL_SERVER_ERROR)
                                          .withEntity(
                                                  HttpEntities.create(
                                                          ContentTypes.APPLICATION_JSON,
                                                          mapper.writeValueAsBytes(
                                                                  new TextResponse("wooops!!!")
                                                          )
                                                  )
                                          );
                              }

                              TestResultResponse[] results = ((TestResultResponse[]) parameter);
                              if (results.length == 0) {
                                  logger.warning("submit {} does not exist", packageID);
                                  return HttpResponse.create()
                                          .withStatus(StatusCodes.NOT_FOUND)
                                          .withEntity(
                                                  HttpEntities.create(
                                                          ContentTypes.APPLICATION_JSON,
                                                          mapper.writeValueAsBytes(
                                                                  new TextResponse("submit does not exist")
                                                          )
                                                  )
                                          );
                              }

                              return HttpResponse.create()
                                      .withStatus(StatusCodes.OK)
                                      .withEntity(
                                              HttpEntities.create(
                                                      ContentTypes.APPLICATION_JSON,
                                                      mapper.writeValueAsBytes(parameter)
                                              )
                                      );
                              } catch (JsonProcessingException e) {
                                  logger.error("got json processing exception {}", e.getMessage());
                                  return HttpResponse.create()
                                          .withStatus(StatusCodes.INTERNAL_SERVER_ERROR)
                                          .withEntity("wooops!!!");
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
                                    return complete(
                                            StatusCodes.CREATED,
                                            new TextResponse("submit accepted!"),
                                            Jackson.marshaller()
                                    );
                                }))
                )
        );
    }
}
