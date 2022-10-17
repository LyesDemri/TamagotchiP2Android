package com.example.mytama;

import java.util.Date;
import android.graphics.Rect;

public class ClockPainter {
  static public void showClock()
  {
    Date date=new Date();
    int currentHour=date.getHours();
    int currentMinute=date.getMinutes();
    int currentSecond=date.getSeconds();
    int offsetX=(int)(MainActivity.surfW/2-320/2);
    int offsetY=(int)(MainActivity.surfH/2-160/2 - 10); //was surfH defined incorrectly?

    //affichage des heures
    int[] value;
    if (currentHour == 12)
      value=Utils.numDecomposition(12);
    else
      value=Utils.numDecomposition(currentHour%12);
    if (value[0]>0)
      MainActivity.canvas.drawBitmap(MainActivity.graphics.nums[value[0]],null, new Rect(10+offsetX,0+offsetY,50+offsetX,80+offsetY),MainActivity.paint);
    MainActivity.canvas.drawBitmap(MainActivity.graphics.nums[value[1]],null, new Rect(60+offsetX,0+offsetY,100+offsetX,80+offsetY),MainActivity.paint);
    //affichage du :
    MainActivity.canvas.drawRect(new Rect(110+offsetX,10+offsetY,120+offsetX,20+offsetY),MainActivity.paint);
    MainActivity.canvas.drawRect(new Rect(110+offsetX,70+offsetY,120+offsetX,80+offsetY),MainActivity.paint);
    //affichage des minutes
    value=Utils.numDecomposition(currentMinute);
    MainActivity.canvas.drawBitmap(MainActivity.graphics.nums[value[0]],null, new Rect(130+offsetX,0+offsetY,170+offsetX,80+offsetY),MainActivity.paint);
    MainActivity.canvas.drawBitmap(MainActivity.graphics.nums[value[1]],null, new Rect(180+offsetX,0+offsetY,220+offsetX,80+offsetY),MainActivity.paint);
    //affichage des secondes
    value=Utils.numDecomposition(currentSecond);
    MainActivity.canvas.drawBitmap(MainActivity.graphics.smallNums[value[0]],null, new Rect(230+offsetX,30+offsetY,260+offsetX,80+offsetY),MainActivity.paint);
    MainActivity.canvas.drawBitmap(MainActivity.graphics.smallNums[value[1]],null, new Rect(270+offsetX,30+offsetY,300+offsetX,80+offsetY),MainActivity.paint);
    // affichage des fleches:
    int numFullArrows = currentSecond % 5;
    for (int i = 0; i < 5; i++) {
      if (i < numFullArrows)
        MainActivity.canvas.drawBitmap(MainActivity.graphics.fullArrow,null, new Rect(offsetX+170+i*3*10,offsetY+110,offsetX+170+i*3*10+30,offsetY+160),MainActivity.paint);
      else
        MainActivity.canvas.drawBitmap(MainActivity.graphics.emptyArrow,null, new Rect(offsetX+170+i*3*10,offsetY+110,offsetX+170+i*3*10+30,offsetY+160),MainActivity.paint);
    }
    //affichage du AM/PM
    if (currentHour < 12)
      MainActivity.canvas.drawBitmap(MainActivity.graphics.Aclock,null, new Rect(30+offsetX,90+offsetY,90+offsetX,130+offsetY),MainActivity.paint);
    else
      MainActivity.canvas.drawBitmap(MainActivity.graphics.Pclock,null, new Rect(30+offsetX,90+offsetY,90+offsetX,130+offsetY),MainActivity.paint);
    MainActivity.canvas.drawBitmap(MainActivity.graphics.Mclock,null, new Rect(30+offsetX,140+offsetY,90+offsetX,170+offsetY),MainActivity.paint);

  }
}