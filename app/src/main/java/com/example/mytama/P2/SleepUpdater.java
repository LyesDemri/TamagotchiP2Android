package com.example.mytama;

import java.util.Date;

public class SleepUpdater {
  public static void update() {
    int currentHour = new Date().getHours();
    int currentMinute = new Date().getMinutes();
    int currentSecond = new Date().getSeconds();
    if (Tama.character.equals("Babytchi")) {
      if (Tama.t == 2400) {
        Tama.sleeping = true;
        MainActivity.state = "idle";
        Utils.notifyUser();
      }
      if (Tama.t == 2700) {
        Tama.sleeping = false;
        Tama.timeSinceNeedsLightsOff = 0;
        Tama.lightsOn = true;
        Tama.age += 1;
      }
    } else {
    if (Tama.t == Tama.timeToSleep) {
        Tama.sleeping = true;
         Utils.notifyUser();
          Tama.timeToSleep += 24*3600;
        }
        else if (Tama.t == Tama.timeToWake) {
          Tama.sleeping = false;
          Tama.lightsOn = true;
          Tama.timeToWake += 24*3600;
          Tama.age += 1;
        }
      }
      //Doit-on ajouter un care miss?
      if ((Tama.sleeping)&&(Tama.lightsOn)) {
        Tama.timeSinceNeedsLightsOff += 1;
        if (Tama.timeSinceNeedsLightsOff == 15*60) {
          Tama.careMisses += 1;
        }
      }
  }
}
