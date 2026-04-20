package com.example.mytama;

import java.lang.Math;

public class ButtonB {
  static public void handle() {
    if (MainActivity.version.equals("P2")) {
      P2ButtonB.handle();
    } else if (MainActivity.version.equals("Santa")) {
      SantaButtonB.handle();
    }
  }
}