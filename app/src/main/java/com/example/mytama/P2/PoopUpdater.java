package com.example.mytama;

public class PoopUpdater {
  public static void update(){
    if (Tama.character.equals("Babytchi")) {
      if (Tama.t == (900*0+20) || Tama.t==2700+5)
        poop();
    }
    else {
      if (!Tama.sleeping && !MainActivity.state.equals("Egg")) {
        Tama.tslp += 1;
        if (Tama.tslp == Tama.pp)
          poop();
      }
    }
    if (Tama.dirty) {
      if (!Tama.character.equals("Egg"))
        Tama.tsd++;
      if (Tama.tsd == 15*60)
        Tama.careMisses++;
      if (Tama.tsd == 12*60*60)
        Tama.sick = true;
    }
  }
  
  public static void poop() {
    if (MainActivity.isOpen) {
      MainActivity.state = "pooping";
      Animations.animation_counter = 16;
    }
    Utils.notifyUser();
    Tama.dirty = true;
    Tama.tslp = 0;
  }
}
