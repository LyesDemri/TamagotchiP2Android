package com.example.mytama;

import java.util.Date;
import android.graphics.Rect;

public class ClockPainter extends Painter {
  static public void showClock()
  {
    //Date date = new Date();
    Date date = TimeWizard.getTamagotchiTime();
    int currentHour = date.getHours();
    int currentMinute = date.getMinutes();
    int currentSecond = date.getSeconds();
    int offsetX = (int)(Screen.surfW/2 - 320/2);
    int offsetY = (int)(Screen.surfH/2 - 160/2 - 10); //was surfH defined incorrectly?

    //affichage des heures
    int[] value;
    if (currentHour == 12)
      value = Utils.numDecomposition(12);
    else
      value = Utils.numDecomposition(currentHour%12);
    if (value[0] > 0)
      canvas.drawBitmap(Graphics.hashMap.get("num"+value[0]),null, new Rect(10+offsetX,0+offsetY,50+offsetX,80+offsetY),paint);
    canvas.drawBitmap(Graphics.hashMap.get("num"+value[1]),null, new Rect(60+offsetX,0+offsetY,100+offsetX,80+offsetY),paint);
    //affichage du :
    canvas.drawRect(new Rect(110 + offsetX, 10+offsetY, 120+offsetX, 20+offsetY), paint);
    canvas.drawRect(new Rect(110 + offsetX, 70+offsetY, 120+offsetX, 80+offsetY), paint);
    //affichage des minutes
    value=Utils.numDecomposition(currentMinute);
    canvas.drawBitmap(Graphics.hashMap.get("num"+value[0]), null, new Rect(130+offsetX,0+offsetY,170+offsetX,80+offsetY),paint);
    canvas.drawBitmap(Graphics.hashMap.get("num"+value[1]), null, new Rect(180+offsetX,0+offsetY,220+offsetX,80+offsetY),paint);
    //affichage des secondes
    value=Utils.numDecomposition(currentSecond);
    canvas.drawBitmap(Graphics.hashMap.get("num"+value[0]+"small"), null, new Rect(230+offsetX,30+offsetY,260+offsetX,80+offsetY),paint);
    canvas.drawBitmap(Graphics.hashMap.get("num"+value[1]+"small"), null, new Rect(270+offsetX,30+offsetY,300+offsetX,80+offsetY),paint);
    // affichage des fleches:
    int numFullArrows = currentSecond % 5;
    for (int i = 0; i < 5; i++) {
      if (i < numFullArrows)
        canvas.drawBitmap(Graphics.hashMap.get("fullarrow"), null, new Rect(offsetX+170+i*3*10,offsetY+110,offsetX+170+i*3*10+30,offsetY+160),paint);
      else
        canvas.drawBitmap(Graphics.hashMap.get("emptyarrow"), null, new Rect(offsetX+170+i*3*10,offsetY+110,offsetX+170+i*3*10+30,offsetY+160),paint);
    }
    //affichage du AM/PM
    if (currentHour < 12)
      canvas.drawBitmap(Graphics.hashMap.get("aclock"), null, new Rect(30+offsetX,90+offsetY,90+offsetX,130+offsetY),paint);
    else
      canvas.drawBitmap(Graphics.hashMap.get("pclock"), null, new Rect(30+offsetX,90+offsetY,90+offsetX,130+offsetY),paint);
    canvas.drawBitmap(Graphics.hashMap.get("mclock"), null, new Rect(30+offsetX,140+offsetY,90+offsetX,170+offsetY),paint);
  }
}