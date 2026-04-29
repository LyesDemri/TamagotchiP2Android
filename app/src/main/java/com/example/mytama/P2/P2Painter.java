package com.example.mytama;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import java.lang.Math;

public class P2Painter extends Painter { 
  public static void draw() {
    //draw tama screen background 
    canvas.drawBitmap(Graphics.hashMap.get("p2bg"), null, new Rect(Screen.offsetX,Screen.offsetY-80+10,Screen.offsetX+320,Screen.offsetY+320-80+10),paint);
        
    //Draw black screen if light is off
    if (!P2Tama.lightsOn && !MainActivity.state.startsWith("StatScreen") && !MainActivity.state.equals("clock") && !MainActivity.state.startsWith("dead")) {
      BlackScreenPainter.draw();
    } else {
      //check current state and draw accordingly
      if (MainActivity.state.equals("idle") || MainActivity.state.equals("Menu"))
        P2IdlePainter.draw();
      else if (MainActivity.state.equals("food choice") || MainActivity.state.equals("eating") || MainActivity.state.equals("saying no food"))
        FoodPainter.draw();
      else if (MainActivity.state.startsWith("StatScreen"))
        StatsPainter.draw();
      else if (MainActivity.state.equals("happy") || MainActivity.state.equals("unhappy") || MainActivity.state.equals("scolded"))
        HappinessPainter.draw();
      else if (MainActivity.state.equals("saying no") || MainActivity.state.equals("pooping"))
        NoPoopPainter.draw();
      else if (MainActivity.state.equals("game intro screen"))
        P2GamePainter.showGameIntroScreen();
      else if (MainActivity.state.equals("playing"))
        P2GamePainter.showPlaying();
      else if (MainActivity.state.equals("show game result"))
        P2GamePainter.showGameResult();
      else if (MainActivity.state.equals("final game results"))
        P2GamePainter.drawFinalGameResults();
      else if (MainActivity.state.equals("Egg")){
        if (MainActivity.even == 1) {
          canvas.drawBitmap(Graphics.hashMap.get("egg_idle_"+(MainActivity.even+1)),null, new Rect(80+Screen.offsetX,0+Screen.offsetY,240+Screen.offsetX,160+Screen.offsetY),paint);
        } else {
          drawSpriteAt(Graphics.hashMap.get("egg_idle_"+(MainActivity.even+1)), 8, 0);
        }
      } else if (MainActivity.state.equals("hatching"))
        if (Tama.t <= 8) {
          canvas.drawBitmap(Graphics.hashMap.get("egg_idle_2"),null, new Rect(80+(MainActivity.even*2-1)*5+Screen.offsetX,0+Screen.offsetY,240+(MainActivity.even*2-1)*5+Screen.offsetX,160+Screen.offsetY),paint);
        } else
          canvas.drawBitmap(Graphics.hashMap.get("hatching"),null, new Rect(80+Screen.offsetX,0+Screen.offsetY,240+Screen.offsetX,160+Screen.offsetY),paint);
      else if (MainActivity.state.startsWith("dead"))
        DeadPainter.draw();
      else if (MainActivity.state.equals("clock"))
        ClockPainter.showClock();
      else if (MainActivity.state.equals("washing"))
        ShowerPainter.paintShower();
        
      BlackScreenPainter.drawPixelGrid();      
     }
     
     IconsPainter.draw();
     
    //draw the tama shell:
    canvas.drawBitmap(Graphics.hashMap.get("mytama3"), null, new Rect(bgx,bgy,bgx+bgW,bgy+bgH),paint);
  }
}