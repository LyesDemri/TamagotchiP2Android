package com.example.mytama;

import android.graphics.Rect;

public class GamePainter 
{
  static int offsetX=(int)(MainActivity.surfW/2-320/2);
  static int offsetY=(int)(MainActivity.surfH/2-160/2);

  public static void drawFinalGameResults()
  {
    MainActivity.canvas.drawBitmap(MainActivity.graphics.face_icon,null, new Rect(0+offsetX,0+offsetY,80+offsetX,80+offsetY),MainActivity.paint);
    MainActivity.canvas.drawBitmap(MainActivity.graphics.emptyHeart,null, new Rect(160+offsetX,0+offsetY,240+offsetX,80+offsetY),MainActivity.paint);
    MainActivity.canvas.drawBitmap(MainActivity.graphics.nums[MainActivity.score],null, new Rect(20+offsetX,80+offsetY,60+offsetX,160+offsetY),MainActivity.paint);
    MainActivity.canvas.drawBitmap(MainActivity.graphics.VS,null, new Rect(80+offsetX,80+offsetY,160+offsetX,160+offsetY),MainActivity.paint);
    MainActivity.canvas.drawBitmap(MainActivity.graphics.nums[5-MainActivity.score],null, new Rect(180+offsetX,80+offsetY,220+offsetX,160+offsetY),MainActivity.paint);

    if (MainActivity.animation_counter==5)
      MainActivity.displayResultsSound.start();
    if (MainActivity.myRunnable.j == 0 || MainActivity.myRunnable.j==13)
      MainActivity.animation_counter=Math.max(MainActivity.animation_counter-1,0);
    if (MainActivity.animation_counter==0)
    {
      if (MainActivity.score>=3)
      {
        MainActivity.state = "happy";
        MainActivity.animation_counter=8;
        if (MainActivity.happy==4 
           && MainActivity.stomach==4
           && (!MainActivity.dirty)
           && MainActivity.idealWeight==MainActivity.weight)
        {
          MainActivity.hghlp+=1;
          MainActivity.hphlp+=1;
          MainActivity.pp+=1;
        }
        MainActivity.happy=Math.min(MainActivity.happy+1,4);
        MainActivity.timeSinceHappyChanged=0;
        MainActivity.timeSinceBored=0;
      }
      else
      {
        MainActivity.state = "unhappy";
        MainActivity.animation_counter=8;
      }
      if (!MainActivity.character.equals("Babytchi"))
          MainActivity.weight=Math.max(MainActivity.weight-1,MainActivity.idealWeight);
      MainActivity.oldState="game intro screen";
      MainActivity.even=1;
      MainActivity.oldAnimationCounter=6;
      MainActivity.score=0;
      MainActivity.round=0;
      MainActivity.myRunnable.k=-1;
      MainActivity.myRunnable.j=-1;
    }
  }

  public static void showGameResult()
  {
    int x = MainActivity.x;
    int y = MainActivity.y;
    int W = MainActivity.W;
    int H = MainActivity.H;
    //on dessine le tmgc dans le bon côté
    if (MainActivity.answer=="higher")
      MainActivity.canvas.drawBitmap(MainActivity.graphics.right,null, new Rect((16-W/2)*10+offsetX,y*10+offsetY,(16+W/2)*10+offsetX,(y+H)*10+offsetY),MainActivity.paint);
    else
      MainActivity.canvas.drawBitmap(MainActivity.graphics.left,null, new Rect((16-W/2)*10+offsetX,y*10+offsetY,(16+W/2)*10+offsetX,(y+H)*10+offsetY),MainActivity.paint);
    //On affiche les chiffres
    MainActivity.canvas.drawBitmap(MainActivity.graphics.nums[MainActivity.givenNumber],null, new Rect(0+offsetX,0+offsetY,40+offsetX,80+offsetY),MainActivity.paint);
    MainActivity.canvas.drawBitmap(MainActivity.graphics.nums[MainActivity.secretNumber],null, new Rect(280+offsetX,0+offsetY,320+offsetX,80+offsetY),MainActivity.paint);
    //On avance dans l'animation
    if (MainActivity.myRunnable.j == 0 || MainActivity.myRunnable.j==13)
      MainActivity.animation_counter=Math.max(MainActivity.animation_counter-1,0);
    //Si l'animation est terminée
    if (MainActivity.animation_counter==0)
    {
      //on passe a l'animation happy ou unhappy
      if ((MainActivity.answer=="higher" && MainActivity.secretNumber>MainActivity.givenNumber)||
         (MainActivity.answer=="lower" && MainActivity.secretNumber<MainActivity.givenNumber))
      {
        MainActivity.state = "happy";
        MainActivity.score+=1;
      }
      else
        MainActivity.state = "unhappy";
      MainActivity.round++;
      if (MainActivity.round<5)
      {
        MainActivity.oldState="playing";
        Utils.generateGameNumbers();
      }
      else
      {
        
        MainActivity.oldState="final game results";
        MainActivity.oldAnimationCounter=5;
      }
      MainActivity.myRunnable.j=-1;
      MainActivity.animation_counter=8;
      MainActivity.even=0;
    }
  }
  
  public static void showPlaying()
  {
    int x=MainActivity.x;
    int y=MainActivity.y;
    int W=MainActivity.W;
    int H=MainActivity.H;
    MainActivity.canvas.drawBitmap(MainActivity.graphics.play[MainActivity.even],null, new Rect((16-W/2)*10+offsetX,y*10+offsetY,(16+W/2)*10+offsetX,(y+H)*10+offsetY),MainActivity.paint);
    MainActivity.canvas.drawBitmap(MainActivity.graphics.nums[MainActivity.givenNumber],null, new Rect(0+offsetX,0+offsetY,40+offsetX,80+offsetY),MainActivity.paint);
    if (MainActivity.myRunnable.i == 0 || MainActivity.myRunnable.i==13)
        MainActivity.flipSound.start();
  }

  public static void showGameIntroScreen()
  {
    int x=16-MainActivity.W/2;
    int y=MainActivity.y;
    int W=MainActivity.W;
    int H=MainActivity.H;
    int k=(MainActivity.myRunnable.k*8-200);
    if (k<0) k=0;
    k=(int)Math.floor(k/10)*10;

    MainActivity.canvas.drawBitmap(MainActivity.graphics.fullHeart,null, new Rect(Math.max(0+offsetX-k,offsetX-80),0+offsetY,80+offsetX-k,80+offsetY),MainActivity.paint);
    MainActivity.canvas.drawBitmap(MainActivity.graphics.fullHeart,null, new Rect(Math.max(160+offsetX-k,offsetX-80),0+offsetY,240+offsetX-k,80+offsetY),MainActivity.paint);
    MainActivity.canvas.drawBitmap(MainActivity.graphics.emptyHeart,null, new Rect(Math.max(80+offsetX-k,offsetX-80),80+offsetY,160+offsetX-k,160+offsetY),MainActivity.paint);
    MainActivity.canvas.drawBitmap(MainActivity.graphics.emptyHeart,null, new Rect(Math.max(240+offsetX-k,offsetX-80),80+offsetY,320+offsetX-k,160+offsetY),MainActivity.paint);
    MainActivity.canvas.drawBitmap(MainActivity.graphics.idle[0],null, new Rect(Math.min(x*10+offsetX+320-k,offsetX+320),y*10+offsetY,Math.min((x+W)*10+offsetX+320-k,offsetX+320+W*10),(y+H)*10+offsetY),MainActivity.paint);
    if (MainActivity.animation_counter==6)
      MainActivity.gameBegin.start();
    if (MainActivity.myRunnable.j == 0 || MainActivity.myRunnable.j==13)
      MainActivity.animation_counter=Math.max(MainActivity.animation_counter-1,0);
    if (MainActivity.animation_counter==0)
      MainActivity.state = "playing";
  }
}