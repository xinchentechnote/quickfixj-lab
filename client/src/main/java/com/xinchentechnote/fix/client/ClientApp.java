package com.xinchentechnote.fix.client;

import quickfix.*;

public class ClientApp implements Application {

  @Override
  public void onCreate(SessionID sessionID) {
    System.out.println("[CLIENT] onCreate " + sessionID);
  }

  @Override
  public void onLogon(SessionID sessionID) {
    System.out.println("[CLIENT] onLogon " + sessionID);
  }

  @Override
  public void onLogout(SessionID sessionID) {
    System.out.println("[CLIENT] onLogout " + sessionID);
  }

  @Override
  public void toAdmin(Message message, SessionID sessionID) {
    System.out.println("[CLIENT] toAdmin: " + message);
  }

  @Override
  public void fromAdmin(Message message, SessionID sessionID) {
    System.out.println("[CLIENT] fromAdmin: " + message);
  }

  @Override
  public void toApp(Message message, SessionID sessionID) {
    System.out.println("[CLIENT] toApp: " + message);
  }

  @Override
  public void fromApp(Message message, SessionID sessionID) {
    System.out.println("[CLIENT] fromApp: " + message);
  }
}
