package com.example.mytama;

import android.graphics.Rect;

public class IconsPainter extends Painter {
  public static void draw(){
    if (MainActivity.version.equals("P2")){
      P2IconsPainter.draw();
    } else if (MainActivity.version.equals("Santa")) {
      SantaIconsPainter.draw();
    }
  }
}
