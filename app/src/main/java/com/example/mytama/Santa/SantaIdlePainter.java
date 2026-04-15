package com.example.mytama;

import android.graphics.Rect;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.graphics.Canvas;
import com.example.test.Schmilblick;

public class SantaIdlePainter extends SantaPainter {
  static boolean goingBack = false;
  public static void draw() {
    int x = Tama.x, y = Tama.y;
    int W = Tama.W, H = Tama.H;
    int offsetX = (int)(Screen.surfW/2 - 320/2);
    int offsetY = (int)(Screen.surfH/2 - 160/2);
    try {
      if (Tama.sleeping) {
        drawSpriteAt(P2Graphics.hashMap.get(Tama.character+"_sleep_"+(MainActivity.even+1)), 16 - W/2,y);
        drawSpriteAt(P2Graphics.hashMap.get("z"+(MainActivity.even+1)), 16 + W/2, y);
      } else {  
        if (MainActivity.myRunnable.i==0 || MainActivity.myRunnable.i==13) {
          //if santa is on the edge of the screen, turn around
          if (x == 0 || x == 32-W) {
            Tama.xIncrement = - Tama.xIncrement;
            goingBack = false;
          } else if ((x == 2) && !goingBack && Tama.xIncrement < 0) {
            Tama.xIncrement = -Tama.xIncrement;
            goingBack = true;
          } else if ((x == 30-W) && !goingBack && Tama.xIncrement > 0) {
            Tama.xIncrement = -Tama.xIncrement;
            goingBack = true;
          } else if ((x == 16-W/2) && goingBack) {
            Tama.xIncrement = -Tama.xIncrement;
          }
          x = x + Tama.xIncrement;
          Printer.print(x);
        }       
        if (Tama.xIncrement < 0) {
          drawSpriteAt(SantaGraphics.hashMap.get(Tama.character+"_idle_"+(MainActivity.even+1)),x,y);
        } else
          drawSpriteAt(flip(SantaGraphics.hashMap.get(Tama.character+"_idle_"+(MainActivity.even+1))),x,y);
      }
    } catch (Exception e) {
      Printer.print("Error in IdlePainter");
      Printer.print(e.getMessage());
    }
    Tama.x = x; Tama.y = y;
    Tama.W = W; Tama.H = H;
  }
}