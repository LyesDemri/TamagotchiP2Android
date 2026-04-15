package com.example.mytama;

public class P2Egg {
  public static void update() {
    if (Tama.t == 5) {
      MainActivity.state = "hatching";
      Sounds.playSound("hatching_sound");
    }
    else if (MainActivity.state.equals("hatching")) {
      if (Tama.t == 10) {
        Tama.character = "babytchi";
        Graphics.loadCharacterGraphics(MainActivity.context, Tama.character);
        MainActivity.state = "idle";
        Tama.x = 12;
        Tama.y = 8;
        Sounds.playSound("call");
        Utils.notifyUser();
      }
    }
  }
}
