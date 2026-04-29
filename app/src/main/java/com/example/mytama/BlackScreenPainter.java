package com.example.mytama;

import android.graphics.Rect;
import android.graphics.Paint;

public class BlackScreenPainter extends Painter{
  public static void draw(){
    for (int l = 0; l < 2; l++) {
      for (int c = 0; c < 4; c++) {
        if (l == 0 && c == 2 && Tama.sleeping)
          canvas.drawBitmap(P2Graphics.hashMap.get("z"+(MainActivity.even+1)+"dark"), null, new Rect(80*c+Screen.offsetX,80*l+Screen.offsetY,80*(c+1)+Screen.offsetX,80*(l+1)+Screen.offsetY), paint);
        else
          canvas.drawBitmap(P2Graphics.hashMap.get("black_tile"), null, new Rect(80*c+Screen.offsetX,80*l+Screen.offsetY,80*(c+1)+Screen.offsetX,80*(l+1)+Screen.offsetY), paint);
      }
    }
  }
  
  public static void drawPixelGrid(){
    //configure paint to draw grid of pixels for style
    Paint greyPaint = new Paint();
    greyPaint.setDither(false);
    greyPaint.setAntiAlias(false);
    greyPaint.setFilterBitmap(false);
    greyPaint.setARGB(32,0,0,0);
    
    for (int line = 0; line < 32; line++)
      for (int col = 0; col < 16; col++)
        canvas.drawRect(new Rect(Screen.offsetX+line*10,Screen.offsetY+col*10,Screen.offsetX+line*10+9,Screen.offsetY+col*10+9),greyPaint);
  }
}
