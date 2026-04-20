package com.example.mytama;

import android.graphics.Rect;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.graphics.Canvas;

public class SantaIdlePainter extends SantaPainter {
  static boolean goingBack = false;
  static int jumpCtr = 0;
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
          if ((x == 0 || x == 32-W) && jumpCtr == 0) {
            goingBack = false;
            jumpCtr = 5;
          } else if ((x == 2) && !goingBack && Tama.xIncrement < 0) {
            Tama.xIncrement = -Tama.xIncrement;
            goingBack = true;
          } else if ((x == 30-W) && !goingBack && Tama.xIncrement > 0) {
            Tama.xIncrement = -Tama.xIncrement;
            goingBack = true;
          } else if ((x == 6 && Tama.xIncrement>0 || x == 10 && Tama.xIncrement < 0) && goingBack) {
            Tama.xIncrement = -Tama.xIncrement;
          }
          if (jumpCtr == 0)
            x = x + Tama.xIncrement;
          else {
            jumpCtr--;
            if (jumpCtr == 0){
              Tama.xIncrement = - Tama.xIncrement;
              x = x + Tama.xIncrement;
            }
          }
        }       
        if (Tama.xIncrement < 0) {
          if (jumpCtr == 4 || jumpCtr == 2)
            drawSpriteAt(SantaGraphics.hashMap.get(Tama.character+"_happy"),x,y);
          else
            drawSpriteAt(SantaGraphics.hashMap.get(Tama.character+"_idle_"+(MainActivity.even+1)),x,y);
        } else {
          if (jumpCtr == 4 || jumpCtr == 2)
            drawSpriteAt(flip(SantaGraphics.hashMap.get(Tama.character+"_happy")),x,y);
          else
            drawSpriteAt(flip(SantaGraphics.hashMap.get(Tama.character+"_idle_"+(MainActivity.even+1))),x,y);
        }
      }
    } catch (Exception e) {
      Printer.print("Error in IdlePainter");
      Printer.print(e.getMessage());
    }
    Tama.x = x; Tama.y = y;
    Tama.W = W; Tama.H = H;
  }
}