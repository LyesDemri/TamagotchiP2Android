package com.example.mytama;

public class ButtonC
{
  public static void handle() {
    if (MainActivity.version.equals("P2")) {
      P2ButtonC.handle();
    } else if (MainActivity.version.equals("Santa")) {
      SantaButtonC.handle();
    }
  }
}