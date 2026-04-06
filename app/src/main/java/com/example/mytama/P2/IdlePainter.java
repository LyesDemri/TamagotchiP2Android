package com.example.mytama;

import android.graphics.Rect;
import android.graphics.Matrix;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.graphics.Canvas;
import com.example.test.Schmilblick;

public class IdlePainter extends Painter {
  public static void draw() {
    int x = Tama.x, y = Tama.y;
    int W = Tama.W, H = Tama.H;
    int offsetX = (int)(Screen.surfW/2 - 320/2);
    int offsetY = (int)(Screen.surfH/2 - 160/2);
    try {
    if (Tama.sick) {
      if (Tama.sleeping) {
        drawSpriteAt(Graphics.hashMap.get(Tama.character+"_sleep_"+(MainActivity.even+1)), 16 - W/2, y);
        drawSpriteAt(Graphics.hashMap.get("z"+(MainActivity.even+1)), 16 + W/2, y);
      } else {
        drawSpriteAt(Graphics.hashMap.get(Tama.character+"_sick_"+(MainActivity.even+1)),16-W/2,y);
      }
      drawSpriteAt(Graphics.hashMap.get("skull"),24,0);
    }
    else {
      if (Tama.sleeping) {
        drawSpriteAt(Graphics.hashMap.get(Tama.character+"_sleep_"+(MainActivity.even+1)), 16 - W/2,y);
        drawSpriteAt(Graphics.hashMap.get("z"+(MainActivity.even+1)), 16 + W/2, y);
      } else {
        if (Tama.walks) {
          //pour changer de sprite chaque 2 top d'horloge
          double even2 = Math.floor(((Tama.t*2)%4)/2);         
          if (MainActivity.myRunnable.i==0 || MainActivity.myRunnable.i==15) {
              //si le tamagotchi est sur les bords, on le force à quitter le bord
              if (x==0)
                Tama.xIncrement=2;
              else if (x==(32-W))
                Tama.xIncrement=-2;
              else    //sinon, on genere un increment aleatoire a gauche ou a droite
                Tama.xIncrement=(int)((Math.round(Math.random()))*4-2);
              x = x + Tama.xIncrement;
          }       
          if (Tama.xIncrement<0) {
            drawSpriteAt(Graphics.hashMap.get(Tama.character+"_idle_"+((int)even2+1)),x,y);
          } else
            drawSpriteAt(flip(Graphics.hashMap.get(Tama.character+"_idle_"+((int)even2+1))),x,y);
        } else
          drawSpriteAt(Graphics.hashMap.get(Tama.character+"_idle_"+(MainActivity.even+1)),x,y);
      }
    }
    if (Tama.dirty)
      drawSpriteAt(Graphics.hashMap.get("poop_"+(MainActivity.even+1)), 24, 8);
    }
    catch (Exception e) {
      Printer.print("Error in IdlePainter");
      Printer.print(e.getMessage());
    }
    Tama.x = x; Tama.y = y;
    Tama.W = W; Tama.H = H;
  }

  public static Bitmap flip(Bitmap d) { 
    Matrix m = new Matrix();
    m.preScale(-1, 1);
    Bitmap src = d;
    Bitmap dst = Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), m, false);
    dst.setDensity(DisplayMetrics.DENSITY_DEFAULT);
    return dst;
  }
}