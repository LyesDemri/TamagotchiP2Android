package com.example.mytama;

public class IdleButtonB {
  public static void handle() {
    if (MainActivity.version.equals("Santa")){
      SantaIdleButtonB.handle();
    } else if (MainActivity.version.equals("P2")){
      P2IdleButtonB.handle();
    }
  }
}
