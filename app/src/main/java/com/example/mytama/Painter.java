package com.example.mytama;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.DisplayMetrics;
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
 
    if (Screen.screen.getHolder().getSurface().isValid() && canvas != null) {
      Screen.canvasWidth = canvas.getWidth();
      Screen.canvasHeight = canvas.getHeight();
      canvas.drawColor(Color.argb(255,0,0,0));  //refresh screen with white color
 
      if (MainActivity.state.equals("reset_screen"))
        ResetScreenPainter.draw();
      else if (MainActivity.state.equals("tama_select_screen")) {
        TamaSelectPainter.draw();
      } else if (MainActivity.state.equals("version_select_screen")) {
        VersionSelectPainter.draw();
      } else {
        if (MainActivity.version.equals("P2")){
          try { 
            P2Painter.draw();
          } catch (Exception e) {
            Printer.append("Error in P2Painter: " + e.getMessage());
          }
        } else if (MainActivity.version.equals("Santa")) {
          SantaPainter.draw();
        } else {
          Printer.append("Unknown version: " + MainActivity.version);
        }
      }
    }
    Screen.screen.getHolder().unlockCanvasAndPost(canvas);
  }
  
  public static Bitmap flip(Bitmap d) { 
    Matrix m = new Matrix();
    m.preScale(-1, 1);
    Bitmap src = d;
    Bitmap dst = Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), m, false);
    dst.setDensity(DisplayMetrics.DENSITY_DEFAULT);
    return dst;
  }
  
  public static void drawSpriteAt(Bitmap sprite, int x, int y){
    x = x*10 + Screen.offsetX;
    y = y*10 + Screen.offsetY;
    double scaling_factor = 2.8125; // Android scales the graphics by this factor
    int h = (int)(sprite.getHeight()/scaling_factor);
    int w = (int)(sprite.getWidth()/scaling_factor);
    canvas.drawBitmap(sprite,null, new Rect(x, y, x+w, y+h),paint);
  }
}