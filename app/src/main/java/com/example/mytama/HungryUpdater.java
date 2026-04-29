package com.example.mytama;

public class HungryUpdater {
  public static void update() {
    if (MainActivity.state.equals("P2")) {
      P2HungryUpdater.update();
    } else if (MainActivity.state.equals("Santa")) {
      SantaHungryUpdater.update();
    }
  }
}
