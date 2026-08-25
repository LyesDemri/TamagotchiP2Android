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
      SantaSleepUpdater.update();
    }
  }
}