package ru.labs.jstester;

import akka.actor.ActorSystem;

public class JSTesterApp {
    public static void main(String[] args) throws Exception {
        ActorSystem system = ActorSystem.create("TestingSystem");

    }
}
