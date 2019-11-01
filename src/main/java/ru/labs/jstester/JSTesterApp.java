package ru.labs.jstester;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class JSTesterApp {
    public static void main(String[] args) throws Exception {
        ActorSystem system = ActorSystem.create("testing-system");

        ActorRef storageActor = system.actorOf(Props.create(StorageActor.class), "storage-actor");
        storageActor.tell(new StorageActor.StoreMessage("hello", "world"), ActorRef.noSender());

    }
}
