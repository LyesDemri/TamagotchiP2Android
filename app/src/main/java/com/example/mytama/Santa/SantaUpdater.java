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
      if (SantaTama.distance >= 14) {
        if (Tama.age <= 111 && !SantaTama.arrivedOnTime) {
          SantaTama.arrivedOnTime = true;
          Printer.print("Santa Arrived on time");
        }
        if (Tama.age >= 111 && !SantaTama.endingPlayed) {
          SantaTama.runEnding();
        }
      }
    }
  }
}