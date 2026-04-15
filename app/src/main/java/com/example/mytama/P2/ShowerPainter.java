package com.example.mytama;

import android.graphics.Rect;

public class ShowerPainter extends Painter {
  static int offsetX = (int)(Screen.surfW/2 - 320/2);
  static int offsetY = (int)(Screen.surfH/2 - 160/2);
  static public void paintShower(){
    int x = Tama.x;
    int y = Tama.y;
    int W = Tama.W;
    int H = Tama.H;
    int k = (MainActivity.myRunnable.k*8);
    k = (int)Math.floor(k/10)*10;

    canvas.drawBitmap(Graphics.hashMap.get(Tama.character+"_idle_1"), 
                      null, 
                      new Rect(Math.max(x*10+offsetX-k,offsetX-W*10),
                                        y*10+offsetY,
                                        Math.max((x+W)*10+offsetX-k,offsetX),
                                        (y+H)*10+offsetY),
                      paint);
    canvas.drawBitmap(Graphics.hashMap.get("shower"),
                      null,
                      new Rect(Math.max(offsetX+320-k,offsetX),
                                        offsetY,
                                        Math.max(offsetX+380-k,offsetX+60),
                                        160+offsetY),
                      paint);
    if (P2Tama.dirty)
      canvas.drawBitmap(Graphics.hashMap.get("poop_1"),
                        null, 
                        new Rect(Math.max(offsetX+240-k,offsetX-80),
                                          80+offsetY,
                                          Math.max(offsetX+320-k,offsetX),
                                          160+offsetY),
                                 paint);
    if (MainActivity.myRunnable.j == 0 || MainActivity.myRunnable.j==13)
      Animations.animation_counter=Math.max(Animations.animation_counter-1,0);
    if (Animations.animation_counter==0) {
      if (P2Tama.dirty) {
        MainActivity.state = "happy";
        Animations.animation_counter = 8;
        MainActivity.even = 0;
        MainActivity.oldState = "idle";
        P2Tama.tsd = 0;
        P2Tama.dirty = false;
        MainActivity.myRunnable.j = -1;
      }
      else
        MainActivity.state = "idle";
    }
  }
}