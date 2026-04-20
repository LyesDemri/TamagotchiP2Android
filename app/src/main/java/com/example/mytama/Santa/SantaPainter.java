package com.example.mytama;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import java.lang.Math;

public class SantaPainter extends Painter {
  static double cabinCtr = 0;
  public static void draw() {
    //Check if drawing surface is valid and draw
    if (Screen.screen.getHolder().getSurface().isValid() && canvas != null) {
      if (!MainActivity.state.equals("reset_screen") && !MainActivity.state.equals("tama_select_screen") && !MainActivity.state.equals("version_select_screen")) {
        //draw tama screen background
        canvas.drawBitmap(SantaGraphics.hashMap.get("santabg"), null, new Rect(Screen.offsetX,Screen.offsetY-80+10,Screen.offsetX+320,Screen.offsetY+320-80+10),paint);
        
        //check current state and draw accordingly
        if (MainActivity.state.equals("idle") || MainActivity.state.equals("Menu"))
          SantaIdlePainter.draw();
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
        else if (MainActivity.state.equals("Cabin")) {
          int ind = (int)(cabinCtr/25) + 1;
          canvas.drawBitmap(SantaGraphics.hashMap.get("cabin_idle_"+(ind)),null, new Rect(80+Screen.offsetX,0+Screen.offsetY,240+Screen.offsetX,160+Screen.offsetY),paint);
          cabinCtr = (cabinCtr + 1)%50;
        } else if (MainActivity.state.equals("hatching")) {
          if (Tama.t <= 8)
            canvas.drawBitmap(SantaGraphics.hashMap.get("cabin_open_door"),null, new Rect(80+Screen.offsetX, 0+Screen.offsetY, 240+Screen.offsetX, 160+Screen.offsetY), paint);
          else {
            if (Tama.t ==9) Sounds.playSound("new_character");
            canvas.drawBitmap(SantaGraphics.hashMap.get("santatchi_hatching"),null, new Rect(80+Screen.offsetX, 0+Screen.offsetY, 240+Screen.offsetX, 160+Screen.offsetY), paint);
          }
        } else if (MainActivity.state.startsWith("dead"))
          DeadPainter.draw();
        else if (MainActivity.state.equals("clock"))
          ClockPainter.showClock();
        else if (MainActivity.state.equals("washing"))
          ShowerPainter.paintShower();
        
        BlackScreenPainter.drawPixelGrid();      
        IconsPainter.draw();
        
        //draw tama shell
        canvas.drawBitmap(SantaGraphics.hashMap.get("mysanta"), null, new Rect(bgx,bgy,bgx+bgW,bgy+bgH),paint);
      }
    }
  }
}