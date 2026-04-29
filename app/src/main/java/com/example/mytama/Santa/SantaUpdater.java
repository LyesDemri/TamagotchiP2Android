package com.example.mytama;

public class SantaUpdater extends Updater  {
  public static void update() {
    //update
    Tama.t++;
    if (Tama.character.equals("cabin")) {
      Cabin.update();
    } else {
      if (!Tama.sleeping) {
        SantaHungryUpdater.update();
        SantaHappyUpdater.update();
      }
      PoopUpdater.update();
      SicknessUpdater.update();
      SantaSleepUpdater.update();
      DisciplineUpdater.update();
      //Evolution Stuff
      if (Tama.t == (65*60) || Tama.t == 2*24*3600 || Tama.t == 5*24*3600||
        ((Tama.t == 8*24*3600) && (Tama.character.equals("zuccitchi")))) {
        Darwin.evolve();
      }
    }
  }
}