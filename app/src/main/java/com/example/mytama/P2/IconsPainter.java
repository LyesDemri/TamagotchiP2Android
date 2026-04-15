package com.example.mytama;

import android.graphics.Rect;

public class IconsPainter extends Painter {
  public static void draw(){
    if (MainActivity.icon_number == 1)
      canvas.drawBitmap(P2Graphics.hashMap.get("food_icon"), null, new Rect(0+20+Screen.offsetX,-80+20+Screen.offsetY,80+Screen.offsetX-20,0+Screen.offsetY-20),paint);
    else if (MainActivity.icon_number == 2)
      canvas.drawBitmap(P2Graphics.hashMap.get("lights_icon"), null, new Rect(80+20+Screen.offsetX,-80+20+Screen.offsetY,160+Screen.offsetX-20,0+Screen.offsetY-20),paint);
    else if (MainActivity.icon_number == 3)
      canvas.drawBitmap(P2Graphics.hashMap.get("game_icon"),null, new Rect(160+20+Screen.offsetX,-80+20+Screen.offsetY,240+Screen.offsetX-20,0+Screen.offsetY-20),paint);
    else if (MainActivity.icon_number == 4)      
      canvas.drawBitmap(P2Graphics.hashMap.get("medicine_icon"), null, new Rect(240+20+Screen.offsetX,-80+20+Screen.offsetY,320+Screen.offsetX-20,0+Screen.offsetY-20),paint);
    else if (MainActivity.icon_number == 5)
      canvas.drawBitmap(P2Graphics.hashMap.get("toilet_icon"), null, new Rect(0+20+Screen.offsetX,160+20+Screen.offsetY,80+Screen.offsetX-20,240+Screen.offsetY-20),paint);
    else if (MainActivity.icon_number == 6)
      canvas.drawBitmap(P2Graphics.hashMap.get("stats_icon"), null, new Rect(80+20+Screen.offsetX,160+20+Screen.offsetY,160+Screen.offsetX-20,240+Screen.offsetY-20),paint);
    else if (MainActivity.icon_number == 7)
      canvas.drawBitmap(P2Graphics.hashMap.get("training_icon"), null, new Rect(160+20+Screen.offsetX,160+20+Screen.offsetY,240+Screen.offsetX-20,240+Screen.offsetY-20),paint);
        
    //Code for attention icon:
    if ((((Tama.timeSinceHungry > 0 && Tama.timeSinceHungry < 900 && Tama.stomach==0)
      ||(Tama.timeSinceBored > 0 && Tama.timeSinceBored < 900 && Tama.happy==0)
      ||(P2Tama.timeSinceNeedsLightsOff > 0 && P2Tama.timeSinceNeedsLightsOff < 900 && Tama.sleeping)
      ||(P2Tama.timeSinceSick > 0 && P2Tama.timeSinceSick < 900 && P2Tama.sick)
      ||(P2Tama.tsd > 0 && P2Tama.tsd < 900 && P2Tama.dirty)
      ||(P2Tama.needsDiscipline))
      &&(Tama.isAlive))
      ) {
      canvas.drawBitmap(P2Graphics.hashMap.get("attention_icon"), null, new Rect(240+20+Screen.offsetX, 160+20+Screen.offsetY, 320+Screen.offsetX-20, 240+Screen.offsetY-20),paint);
    }
  }
}
