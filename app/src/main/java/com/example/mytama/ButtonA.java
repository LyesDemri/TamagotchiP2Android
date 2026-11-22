package com.example.mytama;

public class ButtonA {
  public static boolean pressed = false;
  public static boolean previouslyPressed = false;
  
  public static void handle() {
    pressed = true;
    Sounds.beep();
    MainActivity.vibrator.vibrate(50);
    if (MainActivity.version.equals("P2")) {
      P2ButtonA.handle();
    } else if (MainActivity.version.equals("Santa")) {
      SantaButtonA.handle();
    }
    previouslyPressed = true;
  }
  
  public static void handleRelease() {
    pressed = false;
    previouslyPressed = false;
  }
}