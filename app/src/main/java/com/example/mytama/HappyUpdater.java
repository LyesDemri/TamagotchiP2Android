package com.example.mytama;

public class HappyUpdater {
  public static void update(){
    if (MainActivity.state.equals("P2")) {
      P2HappyUpdater.update();
    } else if (MainActivity.state.equals("Santa")) {
      SantaHappyUpdater.update();
    }
  }
}
