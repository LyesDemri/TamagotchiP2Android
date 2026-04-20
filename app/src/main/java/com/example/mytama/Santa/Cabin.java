package com.example.mytama;

public class Cabin {
  public static void update() {
    if (Tama.t == 5) {
      MainActivity.state = "hatching";
      Sounds.playSound("cabin_exit");
    }
    else if (MainActivity.state.equals("hatching")) {
      if (Tama.t == 11) {
        Tama.character = "santatchi";
        Graphics.loadCharacterGraphics(MainActivity.context, Tama.character);
        MainActivity.state = "idle";
        Tama.x = 12;
        Tama.y = 0;
        Sounds.playSound("santa_call_sound");
        Utils.notifyUser();
      }
    }
  }
}
