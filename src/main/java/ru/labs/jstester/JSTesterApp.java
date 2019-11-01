package ru.labs.jstester;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.Http;
import akka.stream.ActorMaterializer;

public class JSTesterApp {
    public static void main(String[] args) throws Exception {
        ActorSystem system = ActorSystem.create("testing-system");

        final Http http = Http.get(system);
        final ActorMaterializer materializer = ActorMaterializer.create(system);
        
    }
}
