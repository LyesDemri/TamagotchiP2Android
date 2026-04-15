package com.example.mytama;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class VersionSelectPainter extends Painter{
  
  public static int textSize;
  public static String[] versionList = {"P2", "Santa"};
  
  public static void draw() {
    canvas.drawColor(Color.argb(255,255,255,255));
    textSize = 50;
    Paint p = new Paint();
    p.setTextSize((float)textSize);
    p.setARGB(255, 0, 0, 0);
    
    float entryHeight = Painter.canvas.getHeight()/10;
    int verticalLineX = Painter.canvas.getWidth() - canvas.getWidth()/10;
    int offset = 60; //Need to find out why this is necessary
    for (int i = 0; i < (versionList.length); i++) {
      canvas.drawText(versionList[i], 0, i*entryHeight - textSize/4 + offset, p);
      canvas.drawLine(0, i*entryHeight + offset, canvas.getWidth(), i*entryHeight + offset, p);
    }
  }
}