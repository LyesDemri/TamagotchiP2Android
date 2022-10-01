package com.example.mytama;

import android.graphics.Rect;

public class ShowerPainter {
  static int offsetX=(int)(MainActivity.surfW/2-320/2);
  static int offsetY=(int)(MainActivity.surfH/2-160/2);

  static public void paintShower()
  {
    int x = MainActivity.x;
    int y = MainActivity.y;
    int W = MainActivity.W;
    int H = MainActivity.H;
    int k = (MainActivity.myRunnable.k*8);
    k=(int)Math.floor(k/10)*10;

    MainActivity.canvas.drawBitmap(MainActivity.graphics.idle[0], null, new Rect(Math.max(x*10+offsetX-k,offsetX-W*10),y*10+offsetY,Math.max((x+W)*10+offsetX-k,offsetX),(y+H)*10+offsetY),MainActivity.paint);
    MainActivity.canvas.drawBitmap(MainActivity.graphics.shower_tile,
                                   null,
                                   new Rect(Math.max(offsetX+320-k,offsetX),
                                            offsetY,
                                            Math.max(offsetX+380-k,offsetX+60),
                                            160+offsetY),
                                   MainActivity.paint);
    if (MainActivity.dirty)
      MainActivity.canvas.drawBitmap(MainActivity.graphics.poop[0],null, new Rect(Math.max(offsetX+240-k,offsetX-80),
                                                                                  80+offsetY,
                                                                                  Math.max(offsetX+320-k,offsetX),
                                                                                  160+offsetY),
                                     MainActivity.paint);
    if (MainActivity.myRunnable.j == 0 || MainActivity.myRunnable.j==13)
      MainActivity.animation_counter=Math.max(MainActivity.animation_counter-1,0);
    if (MainActivity.animation_counter==0)
    {
      if (MainActivity.dirty)
      {
        MainActivity.state="happy";
        MainActivity.animation_counter=8;
        MainActivity.even=0;
        MainActivity.oldState="idle";
        MainActivity.tsd=0;
        MainActivity.dirty=false;
        MainActivity.myRunnable.j=-1;
      }
      else
        MainActivity.state = "idle";
    }
  }
}