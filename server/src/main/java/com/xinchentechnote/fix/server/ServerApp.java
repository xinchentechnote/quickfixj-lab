package com.xinchentechnote.fix.server;

import quickfix.*;

public class ServerApp implements Application {

  @Override
  public void onCreate(SessionID sessionID) {
    System.out.println("[SERVER] onCreate " + sessionID);
  }

  @Override
  public void onLogon(SessionID sessionID) {
    System.out.println("[SERVER] onLogon " + sessionID);
  }

  @Override
  public void onLogout(SessionID sessionID) {
    System.out.println("[SERVER] onLogout " + sessionID);
  }

  @Override
  public void toAdmin(Message message, SessionID sessionID) {
    System.out.println("[SERVER] toAdmin: " + message);
  }

  @Override
  public void fromAdmin(Message message, SessionID sessionID) {
    System.out.println("[SERVER] fromAdmin: " + message);
  }

  @Override
  public void toApp(Message message, SessionID sessionID) {
    System.out.println("[SERVER] toApp: " + message);
  }

  @Override
  public void fromApp(Message message, SessionID sessionID) {
    System.out.println("[SERVER] fromApp: " + message);
  }
}
