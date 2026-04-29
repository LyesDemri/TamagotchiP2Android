package com.example.mytama;

import java.util.Date;

public class SleepUpdater {
  
  public static void update() {
    if (MainActivity.version.equals("P2")) {
      P2SleepUpdater.update();
    } else if (MainActivity.version.equals("Santa")) {
      SantaSleepUpdater.update();
    }
  }
}
