package com.xinchentechnote.fix.server;

import quickfix.*;

public class ServerMain {

    public static void main(String[] args) throws Exception {
        SessionSettings settings = new SessionSettings("server/server.cfg");

        Application app = new ServerApp();
        MessageStoreFactory storeFactory = new MemoryStoreFactory();
        LogFactory logFactory = new ScreenLogFactory(true, true, true);
        MessageFactory messageFactory = new DefaultMessageFactory();

        SocketAcceptor acceptor = new SocketAcceptor(
                app, storeFactory, settings, logFactory, messageFactory
        );

        acceptor.start();
        System.out.println("FIX Server started...");
    }
}
