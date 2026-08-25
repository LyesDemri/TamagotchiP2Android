package com.example.mytama;

public class HappinessPainter extends Painter {
  public static void draw(){
    if (MainActivity.version.equals("P2")) {
      P2HappinessPainter.draw();
    } else if (MainActivity.version.equals("Santa")) {
      SantaHappinessPainter.draw();
    }
  }
}
