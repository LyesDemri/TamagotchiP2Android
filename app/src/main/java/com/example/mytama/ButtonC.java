package com.example.mytama;

public class ButtonC {
  public static boolean pressed = false;
  public static boolean previouslyPressed = false;

  public static void handle() {
    pressed = true;
    Sounds.beep();
    MainActivity.vibrator.vibrate(50);
    if (ButtonA.pressed && !previouslyPressed) {
      if (MainActivity.state.equals("idle")) {
        Sounds.playSounds = !Sounds.playSounds;
        Printer.print("Sounds activated: " + Sounds.playSounds);
      } else if (MainActivity.state.equals("clock")) {
        Tama.paused = !Tama.paused;
        Printer.print("Paused: " + Tama.paused);
        if (Tama.synced) {
          Tama.synced = false;
          Printer.append("\nTama is no longer in sync with real time");
        }
      }
    } else if (MainActivity.version.equals("P2")) {
      P2ButtonC.handle();
    } else if (MainActivity.version.equals("Santa")) {
      SantaButtonC.handle();
    }
    previouslyPressed = true;
  }
  
  public static void handleRelease(){
    pressed = false;
    previouslyPressed = false;
  }
}