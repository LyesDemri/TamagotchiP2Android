package com.example.mytama;

public class PoopUpdater {
  public static void update(){
    if (Tama.character.equals("babytchi")) {
      if (Tama.t == (900) || Tama.t==2700+5)
        poop();
    }
    else {
      if (!Tama.sleeping && !MainActivity.state.equals("Egg")) {
        P2Tama.tslp += 1;
        if (P2Tama.tslp == P2Tama.pp)
          poop();
      }
    }
    if (P2Tama.dirty) {
      if (!Tama.character.equals("Egg"))
        P2Tama.tsd++;
      if (P2Tama.tsd == 12*60*60)
        P2Tama.sick = true;
    }
  }
  
  public static void poop() {
    if (MainActivity.isOpen) {
      MainActivity.state = "pooping";
      Animations.animation_counter = 16;
    }
    P2Tama.dirty = true;
    P2Tama.tslp = 0;
  }
}
