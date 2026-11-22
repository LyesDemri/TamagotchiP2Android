package com.example.mytama;

import java.lang.Math;

public class ButtonB {
  public static boolean pressed = false;
  public static boolean previouslyPressed = false;

  static public void handle() {
    pressed = true;
    Sounds.beep();
    MainActivity.vibrator.vibrate(50);
    if (MainActivity.version.equals("P2")) {
      P2ButtonB.handle();
    } else if (MainActivity.version.equals("Santa")) {
      SantaButtonB.handle();
    }
    previouslyPressed = true;
  }
  
  static public void handleRelease() {
    pressed = false;
    previouslyPressed = false;
  }
}