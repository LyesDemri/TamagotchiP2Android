package com.example.mytama;

import android.graphics.Rect;

public class SantaIconsPainter extends IconsPainter {
  public static void draw(){
    if (MainActivity.icon_number == 1)
      canvas.drawBitmap(SantaGraphics.hashMap.get("santa_scale_icon"), null, new Rect(0+20+Screen.offsetX,-80+20+Screen.offsetY,80+Screen.offsetX-20,0+Screen.offsetY-20),paint);
    else if (MainActivity.icon_number == 2)
      canvas.drawBitmap(SantaGraphics.hashMap.get("santa_food_icon"), null, new Rect(80+20+Screen.offsetX,-80+20+Screen.offsetY,160+Screen.offsetX-20,0+Screen.offsetY-20),paint);
    else if (MainActivity.icon_number == 3)
      canvas.drawBitmap(SantaGraphics.hashMap.get("santa_game_icon"),null, new Rect(160+20+Screen.offsetX,-80+20+Screen.offsetY,240+Screen.offsetX-20,0+Screen.offsetY-20),paint);
    else if (MainActivity.icon_number == 4)      
      canvas.drawBitmap(SantaGraphics.hashMap.get("santa_bag_icon"), null, new Rect(240+20+Screen.offsetX,-80+20+Screen.offsetY,320+Screen.offsetX-20,0+Screen.offsetY-20),paint);
    else if (MainActivity.icon_number == 5)
      canvas.drawBitmap(SantaGraphics.hashMap.get("santa_super_kuchipatchi_icon"), null, new Rect(0+20+Screen.offsetX,160+20+Screen.offsetY,80+Screen.offsetX-20,240+Screen.offsetY-20),paint);
    else if (MainActivity.icon_number == 6)
      canvas.drawBitmap(SantaGraphics.hashMap.get("santa_advent_calendar_icon"), null, new Rect(80+20+Screen.offsetX,160+20+Screen.offsetY,160+Screen.offsetX-20,240+Screen.offsetY-20),paint);
    else if (MainActivity.icon_number == 7)
      canvas.drawBitmap(SantaGraphics.hashMap.get("santa_tv_icon"), null, new Rect(160+20+Screen.offsetX,160+20+Screen.offsetY,240+Screen.offsetX-20,240+Screen.offsetY-20),paint);
    
    //Code for attention icon:
    if (Tama.timeSinceHungry > 0 && Tama.timeSinceHungry < 900 && Tama.stomach == 0 || true) {
      canvas.drawBitmap(SantaGraphics.hashMap.get("santa_attention_icon"), null, new Rect(240+20+Screen.offsetX, 160+20+Screen.offsetY, 320+Screen.offsetX-20, 240+Screen.offsetY-20),paint);
    }
  }
}
