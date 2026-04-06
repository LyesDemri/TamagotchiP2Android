package com.example.mytama;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import java.lang.Math;

public class Painter {
  static int i;
  static int[] foodAnimPositions, ageChars, weightChars;
  static Canvas canvas;
  static Paint paint;
  static int x, y, H, W;
  static int bgW = (int)(790*1.1), bgH = (int)(838*1.1);
  static int bgx = -90, bgy = -50;
  
  public static void draw() {
    x = Tama.x; y = Tama.y;
    W = Tama.W; H = Tama.H;
    
    paint = new Paint();
    paint.setDither(false);
    paint.setAntiAlias(false);
    paint.setFilterBitmap(false);
    
    canvas = Screen.screen.getHolder().lockCanvas();

    //Check if drawing surface is valid and draw
    if (Screen.screen.getHolder().getSurface().isValid() && canvas != null) {
      Screen.canvasWidth = canvas.getWidth();
      Screen.canvasHeight = canvas.getHeight();
      canvas.drawColor(Color.argb(255,0,0,0));  //refresh screen with white color
      if (!MainActivity.state.equals("reset_screen") && !MainActivity.state.equals("tama_select_screen")) {
        //draw tama screen background
        canvas.drawBitmap(Graphics.hashMap.get("p2bg"), null, new Rect(Screen.offsetX,Screen.offsetY-80+10,Screen.offsetX+320,Screen.offsetY+320-80+10),paint);
        
        //Draw black screen if light is off
        if (!Tama.lightsOn && !MainActivity.state.startsWith("StatScreen") && !MainActivity.state.equals("clock") && !MainActivity.state.startsWith("dead")) {
          BlackScreenPainter.draw();
        } else {
          //check current state and draw accordingly
          if (MainActivity.state.equals("idle") || MainActivity.state.equals("Menu"))
            IdlePainter.draw();
          else if (MainActivity.state.equals("food choice") || MainActivity.state.equals("eating") || MainActivity.state.equals("saying no food"))
            FoodPainter.draw();
          else if (MainActivity.state.startsWith("StatScreen"))
            StatsPainter.draw();
          else if (MainActivity.state.equals("happy") || MainActivity.state.equals("unhappy") || MainActivity.state.equals("scolded"))
            HappinessPainter.draw();
          else if (MainActivity.state.equals("saying no") || MainActivity.state.equals("pooping"))
            NoPoopPainter.draw();
          else if (MainActivity.state.equals("game intro screen"))
            GamePainter.showGameIntroScreen();
          else if (MainActivity.state.equals("playing"))
            GamePainter.showPlaying();
          else if (MainActivity.state.equals("show game result"))
            GamePainter.showGameResult();
          else if (MainActivity.state.equals("final game results"))
            GamePainter.drawFinalGameResults();
          else if (MainActivity.state.equals("Egg"))
            canvas.drawBitmap(Graphics.hashMap.get("egg_idle_"+(MainActivity.even+1)),null, new Rect(80+Screen.offsetX,0+Screen.offsetY,240+Screen.offsetX,160+Screen.offsetY),paint);
          else if (MainActivity.state.equals("hatching"))
            if (Tama.t <= 8)
              canvas.drawBitmap(Graphics.hashMap.get("egg_idle_2"),null, new Rect(80+(MainActivity.even*2-1)*5+Screen.offsetX,0+Screen.offsetY,240+(MainActivity.even*2-1)*5+Screen.offsetX,160+Screen.offsetY),paint);
            else
              canvas.drawBitmap(Graphics.hashMap.get("hatching"),null, new Rect(80+Screen.offsetX,0+Screen.offsetY,240+Screen.offsetX,160+Screen.offsetY),paint);
          else if (MainActivity.state.startsWith("dead"))
            DeadPainter.draw();
          else if (MainActivity.state.equals("clock"))
            ClockPainter.showClock();
          else if (MainActivity.state.equals("washing"))
            ShowerPainter.paintShower();
        }
        
        BlackScreenPainter.drawPixelGrid();      
        IconsPainter.draw();
        
        //draw the tama shell:
        canvas.drawBitmap(Graphics.hashMap.get("mytama3"), null, new Rect(bgx,bgy,bgx+bgW,bgy+bgH),paint);
      }
      else if (MainActivity.state.equals("reset_screen"))
        ResetScreenPainter.draw();
      else if (MainActivity.state.equals("tama_select_screen")) {
        TamaSelectPainter.draw();
      }
      Screen.screen.getHolder().unlockCanvasAndPost(canvas);
    }
  }

  public static void drawSpriteAt(Bitmap sprite, int x, int y){
    x = x*10 + Screen.offsetX;
    y = y*10 + Screen.offsetY;
    int h = sprite.getHeight()/3;
    int w = sprite.getWidth()/3;
    canvas.drawBitmap(sprite,null, new Rect(x,y,x+w,y+h),paint);
  }
}