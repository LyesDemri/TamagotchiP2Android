package com.example.mytama;

import java.util.Date;

public class SantaSleepUpdater extends SleepUpdater {
  public static void update() {
    int currentHour = new Date().getHours();
    int currentMinute = new Date().getMinutes();
    int currentSecond = new Date().getSeconds();
    
    if (Tama.t == Tama.timeToSleep) {
      Tama.sleeping = true;
      Utils.notifyUser(Tama.name + " fell asleep", "call");
      Tama.timeToSleep += 24*3600;
    } else if (Tama.t == Tama.timeToWake) {
      Tama.sleeping = false;
      Tama.timeToWake += 24*3600;
      Tama.age += 1;
      int[] newAdventVisits = new int[SantaTama.adventVisits.length + 1];
      for (int i = 0; i < SantaTama.adventVisits.length; i++) {
        newAdventVisits[i] = SantaTama.adventVisits[i];
      }
      SantaTama.adventVisits = newAdventVisits;
    }
  }
}
