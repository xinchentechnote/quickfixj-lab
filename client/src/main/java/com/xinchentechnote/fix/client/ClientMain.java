package com.xinchentechnote.fix.client;

import quickfix.*;

public class ClientMain {

    public static void main(String[] args) throws Exception {
        SessionSettings settings = new SessionSettings("./conf/client.cfg");

        Application app = new ClientApp();
        MessageStoreFactory storeFactory = new MemoryStoreFactory();
        LogFactory logFactory = new ScreenLogFactory(true, true, true);
        MessageFactory messageFactory = new DefaultMessageFactory();

        SocketInitiator initiator = new SocketInitiator(
                app, storeFactory, settings, logFactory, messageFactory
        );

        initiator.start();
        System.out.println("FIX Client started...");
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            initiator.stop();
        }));

        Thread.currentThread().join();
    }
}
