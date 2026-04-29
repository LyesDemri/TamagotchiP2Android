package com.example.mytama;

import java.util.Date;

public class SantaSleepUpdater extends SleepUpdater {
  public static void update() {
    int currentHour = new Date().getHours();
    int currentMinute = new Date().getMinutes();
    int currentSecond = new Date().getSeconds();
    if (Tama.character.equals("babytchi")) {
      if (Tama.t == 2400) {
        Tama.sleeping = true;
        MainActivity.state = "idle";
        Utils.notifyUser();
      }
      if (Tama.t == 2700) {
        Tama.sleeping = false;
        P2Tama.timeSinceNeedsLightsOff = 0;
        P2Tama.lightsOn = true;
        Tama.age += 1;
      }
    } else {
      if (Tama.t == Tama.timeToSleep) {
        Tama.sleeping = true;
        Utils.notifyUser();
        Tama.timeToSleep += 24*3600;
      } else if (Tama.t == Tama.timeToWake) {
        Tama.sleeping = false;
        P2Tama.lightsOn = true;
        Tama.timeToWake += 24*3600;
        Tama.age += 1;
      }
    }
    //Doit-on ajouter un care miss?
    if ((Tama.sleeping)&&(P2Tama.lightsOn)) {
      P2Tama.timeSinceNeedsLightsOff += 1;
      if (P2Tama.timeSinceNeedsLightsOff == 15*60) {
        P2Tama.careMisses += 1;
      }
    }
  }
}
