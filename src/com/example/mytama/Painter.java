package com.example.mytama;

import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Paint;
import java.lang.Math;


public class Painter 
{
  static int i;
  static Rect rect2;
  static int[] ageChars;
  static int[] weightChars;

  public static void draw()
  {
    //MainActivity.tv.setText("We are in draw()");
    int x=MainActivity.x;
    int y=MainActivity.y;
    int W=MainActivity.W;
    int H=MainActivity.H;
    Paint greyPaint=new Paint();
    greyPaint.setDither(false);
    greyPaint.setAntiAlias(false);
    greyPaint.setFilterBitmap(false);
    greyPaint.setARGB(32,0,0,0);

    if (MainActivity.screen.getHolder().getSurface().isValid())
    {
      MainActivity.canvas = MainActivity.screen.getHolder().lockCanvas();
      MainActivity.canvas.drawColor(Color.argb(0,255,255,255));
      int bgx=-90;
      int bgy=-50;
      int bgW=(int)(790*1.1);
      int bgH=(int)(838*1.1);
      int offsetX=(int)(MainActivity.surfW/2-320/2);
      int offsetY=(int)(MainActivity.surfH/2-160/2);

      //MainActivity.canvas.drawBitmap(MainActivity.graphics.bg,null,new Rect(bgx,bgy,bgx+bgW,bgy+bgH),MainActivity.paint);
      MainActivity.canvas.drawBitmap(MainActivity.graphics.p2bg,null,new Rect(offsetX,offsetY-80+10,offsetX+320,offsetY+320-80+10),MainActivity.paint);
      
      if (MainActivity.state.equals("idle")||MainActivity.state.equals("Menu"))
        IdlePainter.draw();
      else if (MainActivity.state.equals("food choice"))
      {
        MainActivity.canvas.drawBitmap(MainActivity.graphics.arrows[3],null, new Rect(0+offsetX,MainActivity.food_index*80+offsetY,80+offsetX,80+MainActivity.food_index*80+offsetY),MainActivity.paint);
        MainActivity.canvas.drawBitmap(MainActivity.graphics.hamburger_bmp[0],null,new Rect(80+offsetX,0+offsetY,160+offsetX,80+offsetY),MainActivity.paint);
        MainActivity.canvas.drawBitmap(MainActivity.graphics.cake_bmp[0],null,new Rect(80+offsetX,80+offsetY,160+offsetX,160+offsetY),MainActivity.paint);
      }
      else if (MainActivity.state.equals("eating"))
      {
        i=7-MainActivity.animation_counter;
        rect2=MainActivity.foodAnimPositions[i];
        MainActivity.canvas.drawBitmap(MainActivity.graphics.eat[MainActivity.eatAnim[i]],null, new Rect((16-W/2)*10+offsetX,y*10+offsetY,(16+W/2)*10+offsetX,(y+H)*10+offsetY),MainActivity.paint);
        MainActivity.canvas.drawBitmap(MainActivity.graphics.foods[MainActivity.food_index][MainActivity.foodAnim[i]],null, rect2,MainActivity.paint);
        if (MainActivity.myRunnable.j==0 || MainActivity.myRunnable.j==13)
            MainActivity.animation_counter=Math.max(MainActivity.animation_counter-1,0);
        if (MainActivity.animation_counter==0)
          MainActivity.state = "food choice";
      }
      else if (MainActivity.state.equals("saying no food"))
      {
        i=7-MainActivity.animation_counter;
        rect2=MainActivity.noFoodAnimPositions[i];
        MainActivity.canvas.drawBitmap(MainActivity.graphics.no[MainActivity.eatAnim[i]],null, new Rect((16-W/2)*10+offsetX,y*10+offsetY,(16+W/2)*10+offsetX,(y+H)*10+offsetY),MainActivity.paint);
        MainActivity.canvas.drawBitmap(MainActivity.graphics.foods[MainActivity.food_index][0],null, rect2,MainActivity.paint);
        if (MainActivity.myRunnable.j==0 || MainActivity.myRunnable.j==13)
            MainActivity.animation_counter=Math.max(MainActivity.animation_counter-1,0);
        if (MainActivity.animation_counter==0)
          MainActivity.state = "food choice";
      }
      else if (MainActivity.state.equals("StatScreen1"))
      {
        try 
        {
          MainActivity.canvas.drawBitmap(MainActivity.graphics.face_icon,null, new Rect(0+offsetX,0+offsetY,80+offsetX,80+offsetY),MainActivity.paint);
          MainActivity.canvas.drawBitmap(MainActivity.graphics.yr,null, new Rect(240+offsetX,0+offsetY,320+offsetX,80+offsetY),MainActivity.paint);
          MainActivity.canvas.drawBitmap(MainActivity.graphics.scale,null, new Rect(0+offsetX,80+offsetY,80+offsetX,160+offsetY),MainActivity.paint);
          MainActivity.canvas.drawBitmap(MainActivity.graphics.lb,null, new Rect(240+offsetX,80+offsetY,320+offsetX,160+offsetY),MainActivity.paint);
          int[] value;
          value=Utils.numDecomposition(MainActivity.age);
          if (value[0]>0)
            MainActivity.canvas.drawBitmap(MainActivity.graphics.nums[value[0]],null, new Rect(120+offsetX,0+offsetY,160+offsetX,80+offsetY),MainActivity.paint);
          MainActivity.canvas.drawBitmap(MainActivity.graphics.nums[value[1]],null, new Rect(200+offsetX,0+offsetY,240+offsetX,80+offsetY),MainActivity.paint);
          value=Utils.numDecomposition(MainActivity.weight);
          if (value[0]>0)
            MainActivity.canvas.drawBitmap(MainActivity.graphics.nums[value[0]],null, new Rect(120+offsetX,80+offsetY,160+offsetX,160+offsetY),MainActivity.paint);
          MainActivity.canvas.drawBitmap(MainActivity.graphics.nums[value[1]],null, new Rect(200+offsetX,80+offsetY,240+offsetX,160+offsetY),MainActivity.paint);
        }
        catch (Exception e)
        {
          MainActivity.tv.setText(e.getMessage());
        }
      }
      else if (MainActivity.state.equals("StatScreen2"))
      {
        MainActivity.canvas.drawBitmap(MainActivity.graphics.disciplineScreen,null, new Rect(0+offsetX,0+offsetY,320+offsetX,160+offsetY),MainActivity.paint);
        if (MainActivity.discipline>0)
        {
          MainActivity.canvas.drawRect(new Rect(30+offsetX,120+offsetY,40+offsetX,140+offsetY),MainActivity.paint);
          MainActivity.canvas.drawRect(new Rect(50+offsetX,120+offsetY,60+offsetX,140+offsetY),MainActivity.paint);
          MainActivity.canvas.drawRect(new Rect(70+offsetX,120+offsetY,80+offsetX,140+offsetY),MainActivity.paint);
        }
        if (MainActivity.discipline>1)
        {
          MainActivity.canvas.drawRect(new Rect(90+offsetX,120+offsetY,100+offsetX,140+offsetY),MainActivity.paint);
          MainActivity.canvas.drawRect(new Rect(110+offsetX,120+offsetY,120+offsetX,140+offsetY),MainActivity.paint);
          MainActivity.canvas.drawRect(new Rect(130+offsetX,120+offsetY,140+offsetX,140+offsetY),MainActivity.paint);
          MainActivity.canvas.drawRect(new Rect(150+offsetX,120+offsetY,160+offsetX,140+offsetY),MainActivity.paint);
        }
        if (MainActivity.discipline>2)
        {
          MainActivity.canvas.drawRect(new Rect(170+offsetX,120+offsetY,180+offsetX,140+offsetY),MainActivity.paint);
          MainActivity.canvas.drawRect(new Rect(190+offsetX,120+offsetY,200+offsetX,140+offsetY),MainActivity.paint);
          MainActivity.canvas.drawRect(new Rect(210+offsetX,120+offsetY,220+offsetX,140+offsetY),MainActivity.paint);
        }
        if (MainActivity.discipline>3)
        {
          MainActivity.canvas.drawRect(new Rect(230+offsetX,120+offsetY,240+offsetX,140+offsetY),MainActivity.paint);
          MainActivity.canvas.drawRect(new Rect(250+offsetX,120+offsetY,260+offsetX,140+offsetY),MainActivity.paint);
          MainActivity.canvas.drawRect(new Rect(270+offsetX,120+offsetY,280+offsetX,140+offsetY),MainActivity.paint);
          MainActivity.canvas.drawRect(new Rect(290+offsetX,120+offsetY,300+offsetX,140+offsetY),MainActivity.paint);
        }
      }
      else if (MainActivity.state.equals("StatScreen3"))
      {
        MainActivity.canvas.drawBitmap(MainActivity.graphics.hungryWord,null, new Rect(0+offsetX,0+offsetY,240+offsetX,80+offsetY),MainActivity.paint);
        for (int j=0;j<4;j++)
        {
          if (j<MainActivity.stomach)
            MainActivity.canvas.drawBitmap(MainActivity.graphics.fullHeart,null, new Rect(j*80+offsetX,80+offsetY,j*80+80+offsetX,160+offsetY),MainActivity.paint);
          else
            MainActivity.canvas.drawBitmap(MainActivity.graphics.emptyHeart,null, new Rect(j*80+offsetX,80+offsetY,j*80+80+offsetX,160+offsetY),MainActivity.paint);
        }
      }
      else if (MainActivity.state.equals("StatScreen4"))
      {
        MainActivity.canvas.drawBitmap(MainActivity.graphics.happyWord,null, new Rect(0+offsetX,0+offsetY,240+offsetX,80+offsetY),MainActivity.paint);
        for (int j=0;j<4;j++)
        {
          if (j<MainActivity.happy)
            MainActivity.canvas.drawBitmap(MainActivity.graphics.fullHeart,null, new Rect(j*80+offsetX,80+offsetY,j*80+80+offsetX,160+offsetY),MainActivity.paint);
          else
            MainActivity.canvas.drawBitmap(MainActivity.graphics.emptyHeart,null, new Rect(j*80+offsetX,80+offsetY,j*80+80+offsetX,160+offsetY),MainActivity.paint);
        }
      }
      else if (MainActivity.state.equals("happy"))
      {
        if (MainActivity.even==1)
        {
          MainActivity.canvas.drawBitmap(MainActivity.graphics.happy,null, new Rect((16-W/2)*10+offsetX,y*10+offsetY,(16+W/2)*10+offsetX,(y+H)*10+offsetY),MainActivity.paint);
          MainActivity.canvas.drawBitmap(MainActivity.graphics.sun,null, new Rect((16+W/2)*10+offsetX,y*10+offsetY,(16+W/2+8)*10+offsetX,(y+8)*10+offsetY),MainActivity.paint);
        }
        else
          MainActivity.canvas.drawBitmap(MainActivity.graphics.idle[1],null, new Rect((16-W/2)*10+offsetX,y*10+offsetY,(16+W/2)*10+offsetX,(y+H)*10+offsetY),MainActivity.paint);
        if (MainActivity.animation_counter==8 && MainActivity.myRunnable.j==0)
            MainActivity.goodSound.start();
        if (MainActivity.myRunnable.j==0 || MainActivity.myRunnable.j==13)
          MainActivity.animation_counter=Math.max(MainActivity.animation_counter-1,0);
        if (MainActivity.animation_counter==0)
        {
          MainActivity.state = MainActivity.oldState;
          MainActivity.animation_counter=MainActivity.oldAnimationCounter;
          MainActivity.x=8;
          MainActivity.myRunnable.k=-1;
        }
      }
      else if (MainActivity.state.equals("unhappy"))
      {
        MainActivity.canvas.drawBitmap(MainActivity.graphics.unhappy[MainActivity.even],null, new Rect((16-W/2)*10+offsetX,y*10+offsetY,(16+W/2)*10+offsetX,(y+H)*10+offsetY),MainActivity.paint);
        MainActivity.canvas.drawBitmap(MainActivity.graphics.cloud[MainActivity.even],null, new Rect((16+W/2)*10+offsetX,y*10+offsetY,(16+W/2+8)*10+offsetX,(y+8)*10+offsetY),MainActivity.paint);
        if (MainActivity.animation_counter==8)
          MainActivity.badSound.start();
        if (MainActivity.myRunnable.j==0 || MainActivity.myRunnable.j==13)
            MainActivity.animation_counter=Math.max(MainActivity.animation_counter-1,0);
        if (MainActivity.animation_counter==0)
        {
          MainActivity.state = MainActivity.oldState;
          MainActivity.animation_counter=MainActivity.oldAnimationCounter;
          MainActivity.x=8;
          MainActivity.myRunnable.k=-1;
        }
      }
      else if (MainActivity.state.equals("scolded"))
      {
        MainActivity.canvas.drawBitmap(MainActivity.graphics.unhappy[MainActivity.even],null, new Rect((16-W/2)*10+offsetX,y*10+offsetY,(16+W/2)*10+offsetX,(y+H)*10+offsetY),MainActivity.paint);
        MainActivity.canvas.drawBitmap(MainActivity.graphics.cloud[MainActivity.even],null, new Rect((16+W/2)*10+offsetX,y*10+offsetY,(16+W/2+8)*10+offsetX,(y+8)*10+offsetY),MainActivity.paint);
        if (MainActivity.animation_counter==8)
          MainActivity.disciplineSound.start();
        if (MainActivity.myRunnable.j==0 || MainActivity.myRunnable.j==13)
            MainActivity.animation_counter=Math.max(MainActivity.animation_counter-1,0);
        if (MainActivity.animation_counter==0)
        {
          MainActivity.state = MainActivity.oldState;
          MainActivity.animation_counter=MainActivity.oldAnimationCounter;
          MainActivity.x=8;
          MainActivity.goodSound.start();
        }
      }
      else if (MainActivity.state.equals("saying no"))
      {
        MainActivity.canvas.drawBitmap(MainActivity.graphics.no[MainActivity.even],null, new Rect((16-W/2)*10+offsetX,y*10+offsetY,(16+W/2)*10+offsetX,(y+H)*10+offsetY),MainActivity.paint);
        if (MainActivity.myRunnable.j==0 || MainActivity.myRunnable.j==13)
          MainActivity.animation_counter=Math.max(MainActivity.animation_counter-1,0);
        if (MainActivity.animation_counter==0)
        {
          MainActivity.state = MainActivity.oldState;
          MainActivity.animation_counter=MainActivity.oldAnimationCounter;
          MainActivity.x=8;
        }
      }
      else if (MainActivity.state.equals("pooping"))
      {
        if (MainActivity.animation_counter>8)
          MainActivity.canvas.drawBitmap(MainActivity.graphics.no[0],null, new Rect((16-W/2+MainActivity.even)*10+offsetX,y*10+offsetY,(16+W/2+MainActivity.even)*10+offsetX,(y+H)*10+offsetY),MainActivity.paint);
        else
        {
          //draw tamagotchi happy with poop
          MainActivity.canvas.drawBitmap(MainActivity.graphics.happy,null, new Rect((16-W/2)*10+offsetX,y*10+offsetY,(16+W/2)*10+offsetX,(y+H)*10+offsetY),MainActivity.paint);
          MainActivity.canvas.drawBitmap(MainActivity.graphics.poop[MainActivity.even],null, new Rect(240+offsetX,80+offsetY,320+offsetX,160+offsetY),MainActivity.paint);
        }
        if (MainActivity.myRunnable.j==0 || MainActivity.myRunnable.j==13)
            MainActivity.animation_counter=Math.max(MainActivity.animation_counter-1,0);
        if (MainActivity.animation_counter<=0)
        {
          MainActivity.state = "idle";
          MainActivity.x=8;
        }
      }
      else if (MainActivity.state.equals("game intro screen"))
        GamePainter.showGameIntroScreen();
      else if (MainActivity.state.equals("playing"))
        GamePainter.showPlaying();
      else if (MainActivity.state.equals("show game result"))
        GamePainter.showGameResult();
      else if (MainActivity.state.equals("final game results"))
        GamePainter.drawFinalGameResults();
      else if (MainActivity.state.equals("Egg"))
      {
          MainActivity.canvas.drawBitmap(MainActivity.graphics.egg[MainActivity.even],null, new Rect(80+offsetX,0+offsetY,240+offsetX,160+offsetY),MainActivity.paint);
      }
      else if (MainActivity.state.equals("hatching"))
      {
          if (MainActivity.t<=8)
            MainActivity.canvas.drawBitmap(MainActivity.graphics.egg[1],null, new Rect(80+(MainActivity.even*2-1)*5+offsetX,0+offsetY,240+(MainActivity.even*2-1)*5+offsetX,160+offsetY),MainActivity.paint);
          else
            MainActivity.canvas.drawBitmap(MainActivity.graphics.hatching,null, new Rect(80+offsetX,0+offsetY,240+offsetX,160+offsetY),MainActivity.paint);
      }
      else if (MainActivity.state.equals("dead1"))
      {
        if (MainActivity.even==1)
          MainActivity.canvas.drawBitmap(MainActivity.graphics.ufo,null, new Rect(0+offsetX,0+offsetY,160+offsetX,160+offsetY),MainActivity.paint);
        else
          MainActivity.canvas.drawBitmap(IdlePainter.flip(MainActivity.graphics.ufo),null, new Rect(0+offsetX,0+offsetY,160+offsetX,160+offsetY),MainActivity.paint);
        MainActivity.canvas.drawBitmap((MainActivity.graphics.star3),null, new Rect(160+MainActivity.even*80+offsetX,0+offsetY,240+MainActivity.even*80+offsetX,80+offsetY),MainActivity.paint);
        MainActivity.canvas.drawBitmap((MainActivity.graphics.star1),null, new Rect(160+(1-MainActivity.even)*80+offsetX,0+offsetY,240+(1-MainActivity.even)*80+offsetX,80+offsetY),MainActivity.paint);
        MainActivity.canvas.drawBitmap((MainActivity.graphics.star3),null, new Rect(160+(1-MainActivity.even)*80+offsetX,80+offsetY,240+(1-MainActivity.even)*80+offsetX,160+offsetY),MainActivity.paint);
        MainActivity.canvas.drawBitmap((MainActivity.graphics.star2),null, new Rect(160+MainActivity.even*80+offsetX,80+offsetY,240+MainActivity.even*80+offsetX,160+offsetY),MainActivity.paint);
      }
      else if (MainActivity.state.equals("dead2"))
      {
        MainActivity.tv.setText("drawing dead2");
        //display stars
        MainActivity.canvas.drawBitmap((MainActivity.graphics.star3),null, new Rect(MainActivity.even*80+offsetX,0+offsetY,80+MainActivity.even*80+offsetX,80+offsetY),MainActivity.paint);
        MainActivity.canvas.drawBitmap((MainActivity.graphics.star1),null, new Rect((1-MainActivity.even)*80+offsetX,0+offsetY,80+(1-MainActivity.even)*80+offsetX,80+offsetY),MainActivity.paint);
        MainActivity.canvas.drawBitmap((MainActivity.graphics.star3),null, new Rect((1-MainActivity.even)*80+offsetX,80+offsetY,80+(1-MainActivity.even)*80+offsetX,160+offsetY),MainActivity.paint);
        MainActivity.canvas.drawBitmap((MainActivity.graphics.star2),null, new Rect(MainActivity.even*80+offsetX,80+offsetY,80+MainActivity.even*80+offsetX,160+offsetY),MainActivity.paint);
        //display age
        int[] value;
        value=Utils.numDecomposition(MainActivity.age);
        if (value[0]>0)
          MainActivity.canvas.drawBitmap(MainActivity.graphics.nums[value[0]],null, new Rect(240-10+offsetX,0+offsetY,280-10+offsetX,80+offsetY),MainActivity.paint);
        MainActivity.canvas.drawBitmap(MainActivity.graphics.nums[value[1]],null, new Rect(280+offsetX,0+offsetY,320+offsetX,80+offsetY),MainActivity.paint);
        MainActivity.canvas.drawBitmap(MainActivity.graphics.yr,null, new Rect(240+offsetX,80+offsetY,320+offsetX,160+offsetY),MainActivity.paint);
        
        MainActivity.tv.setText("Press B to reset");
      }
      else if (MainActivity.state.equals("clock"))
        ClockPainter.showClock();
      else if (MainActivity.state.equals("washing"))
        ShowerPainter.paintShower();
      else
      {
        //MainActivity.tv.setText("Unknown state: "+MainActivity.state);
      }
      //MainActivity.canvas.drawRect(new Rect(offsetX,offsetY,offsetX+320,offsetY+1),MainActivity.paint);
      //MainActivity.canvas.drawRect(new Rect(offsetX,offsetY+160,offsetX+320,offsetY+161),MainActivity.paint);
      //peu importe ce qu'on a dessine, on dessine un ecran noir si la lumiere est eteinte
      if (!MainActivity.lightsOn &&
          !MainActivity.state.equals("StatScreen1") &&
          !MainActivity.state.equals("StatScreen2") &&
          !MainActivity.state.equals("StatScreen3") &&
          !MainActivity.state.equals("StatScreen4") &&
          !MainActivity.state.equals("clock")
         )
      {
        MainActivity.canvas.drawBitmap(MainActivity.graphics.black_tile,null,new Rect(offsetX,offsetY,80+offsetX,80+offsetY),MainActivity.paint);
        MainActivity.canvas.drawBitmap(MainActivity.graphics.black_tile,null,new Rect(80+offsetX,offsetY,160+offsetX,80+offsetY),MainActivity.paint);
        if (MainActivity.sleeping)
          MainActivity.canvas.drawBitmap(MainActivity.graphics.Zdark[MainActivity.even],null,new Rect(160+offsetX,offsetY,240+offsetX,80+offsetY),MainActivity.paint);
        else
          MainActivity.canvas.drawBitmap(MainActivity.graphics.black_tile,null,new Rect(160+offsetX,offsetY,240+offsetX,80+offsetY),MainActivity.paint);
        MainActivity.canvas.drawBitmap(MainActivity.graphics.black_tile,null,new Rect(240+offsetX,offsetY,320+offsetX,80+offsetY),MainActivity.paint);
        MainActivity.canvas.drawBitmap(MainActivity.graphics.black_tile,null,new Rect(offsetX,80+offsetY,80+offsetX,160+offsetY),MainActivity.paint);
        MainActivity.canvas.drawBitmap(MainActivity.graphics.black_tile,null,new Rect(80+offsetX,80+offsetY,160+offsetX,160+offsetY),MainActivity.paint);
        MainActivity.canvas.drawBitmap(MainActivity.graphics.black_tile,null,new Rect(160+offsetX,80+offsetY,240+offsetX,160+offsetY),MainActivity.paint);
        MainActivity.canvas.drawBitmap(MainActivity.graphics.black_tile,null,new Rect(240+offsetX,80+offsetY,320+offsetX,160+offsetY),MainActivity.paint);
      }

      for (int line=0;line<32;line++)
        for (int col=0; col<16;col++)
          MainActivity.canvas.drawRect(new Rect(offsetX+line*10,offsetY+col*10,offsetX+line*10+9,offsetY+col*10+9),greyPaint);

      if (MainActivity.icon_number==1)
        MainActivity.canvas.drawBitmap(MainActivity.graphics.foodIcon,null, new Rect(0+20+offsetX,-80+20+offsetY,80+offsetX-20,0+offsetY-20),MainActivity.paint);
      else if (MainActivity.icon_number==2)
        MainActivity.canvas.drawBitmap(MainActivity.graphics.lightsIcon,null, new Rect(80+20+offsetX,-80+20+offsetY,160+offsetX-20,0+offsetY-20),MainActivity.paint);
      else if (MainActivity.icon_number==3)
        MainActivity.canvas.drawBitmap(MainActivity.graphics.gameIcon,null, new Rect(160+20+offsetX,-80+20+offsetY,240+offsetX-20,0+offsetY-20),MainActivity.paint);
      else if (MainActivity.icon_number==4)      
        MainActivity.canvas.drawBitmap(MainActivity.graphics.medicineIcon,null, new Rect(240+20+offsetX,-80+20+offsetY,320+offsetX-20,0+offsetY-20),MainActivity.paint);
      else if (MainActivity.icon_number==5)
        MainActivity.canvas.drawBitmap(MainActivity.graphics.toiletIcon,null, new Rect(0+20+offsetX,160+20+offsetY,80+offsetX-20,240+offsetY-20),MainActivity.paint);
      else if (MainActivity.icon_number==6)
        MainActivity.canvas.drawBitmap(MainActivity.graphics.statusIcon,null, new Rect(80+20+offsetX,160+20+offsetY,160+offsetX-20,240+offsetY-20),MainActivity.paint);
      else if (MainActivity.icon_number==7)
        MainActivity.canvas.drawBitmap(MainActivity.graphics.disciplineIcon,null, new Rect(160+20+offsetX,160+20+offsetY,240+offsetX-20,240+offsetY-20),MainActivity.paint);
        
      //code pour l'attention icon
      if ((((MainActivity.timeSinceHungry > 0 && MainActivity.timeSinceHungry < 900 && MainActivity.stomach==0)
        ||(MainActivity.timeSinceBored > 0 && MainActivity.timeSinceBored < 900 && MainActivity.happy==0)
        ||(MainActivity.timeSinceNeedsLightsOff > 0 && MainActivity.timeSinceNeedsLightsOff < 900 && MainActivity.sleeping)
        ||(MainActivity.timeSinceSick > 0 && MainActivity.timeSinceSick < 900 && MainActivity.sick)
        ||(MainActivity.tsd > 0 && MainActivity.tsd < 900 && MainActivity.dirty)
        ||(MainActivity.needsDiscipline))
        &&(MainActivity.isAlive))
        )
      {
        MainActivity.canvas.drawBitmap(MainActivity.graphics.attentionIcon,null, new Rect(240+20+offsetX,160+20+offsetY,320+offsetX-20,240+offsetY-20),MainActivity.paint);
      }
      MainActivity.canvas.drawBitmap(MainActivity.graphics.bg,null,new Rect(bgx,bgy,bgx+bgW,bgy+bgH),MainActivity.paint);
      MainActivity.screen.getHolder().unlockCanvasAndPost(MainActivity.canvas);
    }
  }
}