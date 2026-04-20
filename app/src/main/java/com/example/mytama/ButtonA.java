package com.example.mytama;

public class ButtonA {
  public static void handle() {
    if (MainActivity.version.equals("P2")) {
      P2ButtonA.handle();
    } else if (MainActivity.version.equals("Santa")) {
      SantaButtonA.handle();
    }
  }
}