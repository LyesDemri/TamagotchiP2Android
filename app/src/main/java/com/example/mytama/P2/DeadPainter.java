package com.example.mytama;

import android.graphics.Rect;

public class DeadPainter extends Painter {
  public static void draw() {
    if (MainActivity.state.equals("dead1")) {
      if (MainActivity.even==1)
        canvas.drawBitmap(P2Graphics.hashMap.get("ufo"), null, new Rect(0+Screen.offsetX,0+Screen.offsetY,160+Screen.offsetX,160+Screen.offsetY),paint);
      else
        canvas.drawBitmap(P2IdlePainter.flip(P2Graphics.hashMap.get("ufo")),null, new Rect(0+Screen.offsetX,0+Screen.offsetY,160+Screen.offsetX,160+Screen.offsetY),paint);
      drawStars(160);
    }
    else if (MainActivity.state.equals("dead2")) {
      drawStars(0);
      //display age
      int[] value;
      value = Utils.numDecomposition(Tama.age);
      if (value[0]>0)
        canvas.drawBitmap(P2Graphics.hashMap.get("num"+value[0]), null, new Rect(240-10+Screen.offsetX,0+Screen.offsetY,280-10+Screen.offsetX,80+Screen.offsetY),paint);
      canvas.drawBitmap(P2Graphics.hashMap.get("num"+value[1]), null, new Rect(280+Screen.offsetX,0+Screen.offsetY,320+Screen.offsetX,80+Screen.offsetY),paint);
      canvas.drawBitmap(P2Graphics.hashMap.get("yr"), null, new Rect(240+Screen.offsetX,80+Screen.offsetY,320+Screen.offsetX,160+Screen.offsetY),paint);
      MainActivity.tv.setText("Press B to reset");
    }
  }
  
  static void drawStars(int offset) {
    canvas.drawBitmap(P2Graphics.hashMap.get("star3"),null, new Rect(offset + MainActivity.even*80 + Screen.offsetX, 0 + Screen.offsetY, offset + 80 + MainActivity.even*80 + Screen.offsetX, 80 + Screen.offsetY),paint);
    canvas.drawBitmap(P2Graphics.hashMap.get("star1"),null, new Rect(offset + (1-MainActivity.even)*80 + Screen.offsetX, 0 + Screen.offsetY, offset + 80 + (1-MainActivity.even)*80 + Screen.offsetX, 80 + Screen.offsetY),paint);
    canvas.drawBitmap(P2Graphics.hashMap.get("star3"),null, new Rect(offset + (1-MainActivity.even)*80 + Screen.offsetX, 80 + Screen.offsetY, offset + 80 + (1-MainActivity.even)*80 + Screen.offsetX, 160 + Screen.offsetY),paint);
    canvas.drawBitmap(P2Graphics.hashMap.get("star2"),null, new Rect(offset + MainActivity.even*80 + Screen.offsetX, 80 + Screen.offsetY, offset + 80 + MainActivity.even*80 + Screen.offsetX, 160 + Screen.offsetY),paint);  
  }
}
