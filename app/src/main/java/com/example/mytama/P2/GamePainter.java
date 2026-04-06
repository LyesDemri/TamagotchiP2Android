package com.example.mytama;

import android.graphics.Rect;

public class GamePainter extends Painter
{
  static int offsetX = (int)(Screen.surfW/2-320/2);
  static int offsetY = (int)(Screen.surfH/2-160/2);
  public static void drawFinalGameResults() {
    drawSpriteAt(Graphics.hashMap.get("face_icon"), 0, 0);
    drawSpriteAt(Graphics.hashMap.get("empty_heart"), 16, 0);
    drawSpriteAt(Graphics.hashMap.get("num"+P2Game.score), 2, 8);
    drawSpriteAt(Graphics.hashMap.get("vs"), 8 ,8);
    drawSpriteAt(Graphics.hashMap.get("num"+(5-P2Game.score)), 18, 8);

    if (Animations.animation_counter == 5)
      Sounds.playSound("display_results_sound");
    if (MainActivity.myRunnable.j == 0 || MainActivity.myRunnable.j == 13)
      Animations.animation_counter = Math.max(Animations.animation_counter - 1,0);
    if (Animations.animation_counter==0) {
      P2Game.handleWinLoss();
      MainActivity.oldState = "game intro screen";
      MainActivity.even = 1;
      Animations.oldAnimationCounter = 6;
      P2Game.score = 0;
      P2Game.round = 0;
      MainActivity.myRunnable.k--;
      MainActivity.myRunnable.j--;
    }
  }

  public static void showGameResult(){
    int x = Tama.x, y = Tama.y;
    int W = Tama.W, H = Tama.H;
    //on dessine le tmgc dans le bon côté
    if (P2Game.answer == "higher")
      drawSpriteAt(Graphics.hashMap.get(Tama.character + "_right"), 16-W/2, y);
    else
      drawSpriteAt(Graphics.hashMap.get(Tama.character + "_left"),16-W/2, y);
    //On affiche les chiffres
    drawSpriteAt(Graphics.hashMap.get("num"+P2Game.givenNumber), 0, 0);
    drawSpriteAt(Graphics.hashMap.get("num"+P2Game.secretNumber), 28, 0);
    //On avance dans l'animation
    if (MainActivity.myRunnable.j == 0 || MainActivity.myRunnable.j==13)
      Animations.animation_counter=Math.max(Animations.animation_counter-1,0);
    //Si l'animation est terminée
    if (Animations.animation_counter == 0) {
      P2Game.endRound();
    }
  }
  
  public static void showPlaying() {
    int x = Tama.x, y = Tama.y;
    int W = Tama.W, H = Tama.H;
    drawSpriteAt(Graphics.hashMap.get(Tama.character+"_play_"+(MainActivity.even+1)), 16 - W/2, y);
    drawSpriteAt(Graphics.hashMap.get("num"+P2Game.givenNumber), 0, 0);
    if (MainActivity.myRunnable.i == 0 || MainActivity.myRunnable.i==13)
        Sounds.playSound("flip_sound");
  }

  public static void showGameIntroScreen() {
    int x = 16 - Tama.W/2; int y = Tama.y;
    int W = Tama.W; int H = Tama.H;
    int k = (MainActivity.myRunnable.k*8-270); //magical value that gives good results
    if (k<0) k=0;
    k=(int)Math.floor(k/10)*10;
    canvas.drawBitmap(Graphics.hashMap.get("full_heart"), null, new Rect(Math.max(  0+offsetX-k,offsetX-80), 0+offsetY, 80+offsetX-k, 80+offsetY),paint);
    canvas.drawBitmap(Graphics.hashMap.get("full_heart"), null, new Rect(Math.max(160+offsetX-k,offsetX-80), 0+offsetY,240+offsetX-k, 80+offsetY),paint);
    canvas.drawBitmap(Graphics.hashMap.get("empty_heart"), null, new Rect(Math.max( 80+offsetX-k,offsetX-80),80+offsetY,160+offsetX-k,160+offsetY),paint);
    canvas.drawBitmap(Graphics.hashMap.get("empty_heart"), null, new Rect(Math.max(240+offsetX-k,offsetX-80),80+offsetY,320+offsetX-k,160+offsetY),paint);
    canvas.drawBitmap(Graphics.hashMap.get(Tama.character+"_play_1"),
                                   null,
                                   new Rect(Math.min(x*10+offsetX+320-k, offsetX+320),
                                            y*10+offsetY,
                                            Math.min((x+W)*10+offsetX+320-k,offsetX+320+W*10),
                                            (y+H)*10+offsetY),
                                   paint);
    if (Animations.animation_counter==6)
      Sounds.playSound("game_begin");
    if (MainActivity.myRunnable.j == 0 || MainActivity.myRunnable.j==13)
      Animations.animation_counter=Math.max(Animations.animation_counter-1,0);
    if (Animations.animation_counter==0)
      MainActivity.state = "playing";
  }
}