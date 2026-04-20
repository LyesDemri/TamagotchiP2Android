package com.example.mytama;

import android.graphics.Rect;

public class P2StatsPainter extends Painter {
  public static void draw() {
    if (MainActivity.state.equals("StatScreen1")) {
      try  {
        canvas.drawBitmap(Graphics.hashMap.get("face_icon"), null, new Rect(0 + Screen.offsetX, 0 + Screen.offsetY, 80 + Screen.offsetX, 80 + Screen.offsetY), paint);
        canvas.drawBitmap(Graphics.hashMap.get("yr"),null, new Rect(240+Screen.offsetX,0+Screen.offsetY,320+Screen.offsetX,80+Screen.offsetY),paint);
        canvas.drawBitmap(Graphics.hashMap.get("scale_icon"), null, new Rect(0+Screen.offsetX,80+Screen.offsetY,80+Screen.offsetX,160+Screen.offsetY),paint);
        canvas.drawBitmap(Graphics.hashMap.get("lb"), null, new Rect(240+Screen.offsetX,80+Screen.offsetY,320+Screen.offsetX,160+Screen.offsetY),paint);
        int[] value;
        value = Utils.numDecomposition(Tama.age);
        if (value[0]>0)
          canvas.drawBitmap(Graphics.hashMap.get("num"+value[0]),null, new Rect(120+Screen.offsetX,0+Screen.offsetY,160+Screen.offsetX,80+Screen.offsetY),paint);
        canvas.drawBitmap(Graphics.hashMap.get("num"+value[1]),null, new Rect(200+Screen.offsetX,0+Screen.offsetY,240+Screen.offsetX,80+Screen.offsetY),paint);
        value = Utils.numDecomposition(Tama.weight);
        if (value[0]>0)
          canvas.drawBitmap(Graphics.hashMap.get("num"+value[0]),null, new Rect(120+Screen.offsetX,80+Screen.offsetY,160+Screen.offsetX,160+Screen.offsetY),paint);
        canvas.drawBitmap(Graphics.hashMap.get("num"+value[1]),null, new Rect(200+Screen.offsetX,80+Screen.offsetY,240+Screen.offsetX,160+Screen.offsetY),paint);
      }
      catch (Exception e) {
        MainActivity.tv.setText(e.getMessage());
      }
    }
    else if (MainActivity.state.equals("StatScreen2")) {
      canvas.drawBitmap(Graphics.hashMap.get("discipline_screen"), null, new Rect(0+Screen.offsetX,0+Screen.offsetY,320+Screen.offsetX,160+Screen.offsetY),paint);
      int c = 30;
      for (int i = 0; i < 4; i++) {
        for (int j = 0; j < 4; j++) {
          if (P2Tama.discipline > i) {
            canvas.drawRect(new Rect(c+Screen.offsetX,120+Screen.offsetY,(c+10)+Screen.offsetX,140+Screen.offsetY),paint);
            c = c+20;
            if (j == 3 && (i%2) == 1)
              break;
          }
        }
      }
    }
    else if (MainActivity.state.equals("StatScreen3")) {
      canvas.drawBitmap(Graphics.hashMap.get("hungry_word"),null, new Rect(0+Screen.offsetX,0+Screen.offsetY,240+Screen.offsetX,80+Screen.offsetY),paint);
      for (int j=0;j<4;j++) {
        if (j < Tama.stomach)
          canvas.drawBitmap(Graphics.hashMap.get("full_heart"), null, new Rect(j*80+Screen.offsetX,80+Screen.offsetY,j*80+80+Screen.offsetX,160+Screen.offsetY),paint);
        else
          canvas.drawBitmap(Graphics.hashMap.get("empty_heart"), null, new Rect(j*80+Screen.offsetX,80+Screen.offsetY,j*80+80+Screen.offsetX,160+Screen.offsetY),paint);
      }
    }
    else if (MainActivity.state.equals("StatScreen4")) {
      canvas.drawBitmap(Graphics.hashMap.get("happy_word"), null, new Rect(0+Screen.offsetX,0+Screen.offsetY,240+Screen.offsetX,80+Screen.offsetY),paint);
      for (int j=0;j<4;j++) {
        if (j < Tama.happy)
          canvas.drawBitmap(Graphics.hashMap.get("full_heart"), null, new Rect(j*80+Screen.offsetX,80+Screen.offsetY,j*80+80+Screen.offsetX,160+Screen.offsetY),paint);
        else
          canvas.drawBitmap(Graphics.hashMap.get("empty_heart"), null, new Rect(j*80+Screen.offsetX,80+Screen.offsetY,j*80+80+Screen.offsetX,160+Screen.offsetY),paint);
      }
    }
  }
}
