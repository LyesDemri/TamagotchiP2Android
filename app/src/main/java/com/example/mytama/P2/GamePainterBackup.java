package com.example.mytama;

import android.graphics.Rect;

public class GamePainterBackup extends Painter
{
  static int offsetX=(int)(Screen.surfW/2-320/2);
  static int offsetY=(int)(Screen.surfH/2-160/2);
  public static void drawFinalGameResults() {
    canvas.drawBitmap(Graphics.hashMap.get("face_icon"), null, new Rect(0+offsetX,0+offsetY,80+offsetX,80+offsetY),paint);
    canvas.drawBitmap(Graphics.hashMap.get("empty_heart"), null, new Rect(160+offsetX,0+offsetY,240+offsetX,80+offsetY),paint);
    canvas.drawBitmap(Graphics.hashMap.get("num"+P2Game.score), null, new Rect(20+offsetX,80+offsetY,60+offsetX,160+offsetY),paint);
    canvas.drawBitmap(Graphics.hashMap.get("vs"), null, new Rect(80+offsetX,80+offsetY,160+offsetX,160+offsetY),paint);
    canvas.drawBitmap(Graphics.hashMap.get("num"+(5-P2Game.score)), null, new Rect(180+offsetX,80+offsetY,220+offsetX,160+offsetY),paint);

    if (Animations.animation_counter == 5)
      Sounds.playSound("display_results_sound");
    if (MainActivity.myRunnable.j == 0 || MainActivity.myRunnable.j == 13)
      Animations.animation_counter = Math.max(Animations.animation_counter - 1,0);
    if (Animations.animation_counter==0) {
      if (P2Game.score >= 3) {
        Sounds.playSound("good_sound");
        MainActivity.state = "happy";
        Animations.animation_counter = 8;
        if (Tama.happy == 4 
           && Tama.stomach == 4
           && (!Tama.dirty)
           && Tama.idealWeight == Tama.weight) {
          Tama.hghlp++;
          Tama.hphlp++;
          Tama.pp++;
        }
        Tama.happy = Math.min(Tama.happy + 1, 4);
        Tama.timeSinceHappyChanged = 0;
        Tama.timeSinceBored = 0;
      }
      else {
        MainActivity.state = "unhappy";
        Animations.animation_counter = 8;
      }
      if (!Tama.character.equals("Babytchi"))
          Tama.weight = Math.max(Tama.weight-1, Tama.idealWeight);
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
      canvas.drawBitmap(Graphics.hashMap.get(Tama.character + "_right"),null, new Rect((16-W/2)*10+offsetX,y*10+offsetY,(16+W/2)*10+offsetX,(y+H)*10+offsetY),paint);
    else
      canvas.drawBitmap(Graphics.hashMap.get(Tama.character + "_left"),null, new Rect((16-W/2)*10+offsetX,y*10+offsetY,(16+W/2)*10+offsetX,(y+H)*10+offsetY),paint);
    //On affiche les chiffres
    canvas.drawBitmap(Graphics.hashMap.get("num"+P2Game.givenNumber), null, new Rect(0+offsetX,0+offsetY,40+offsetX,80+offsetY),paint);
    canvas.drawBitmap(Graphics.hashMap.get("num"+P2Game.secretNumber),null, new Rect(280+offsetX,0+offsetY,320+offsetX,80+offsetY),paint);
    //On avance dans l'animation
    if (MainActivity.myRunnable.j == 0 || MainActivity.myRunnable.j==13)
      Animations.animation_counter=Math.max(Animations.animation_counter-1,0);
    //Si l'animation est terminée
    if (Animations.animation_counter == 0) {
      //on passe a l'animation happy ou unhappy
      if ((P2Game.answer == "higher" && P2Game.secretNumber > P2Game.givenNumber)||
         (P2Game.answer == "lower" && P2Game.secretNumber < P2Game.givenNumber)) {
        MainActivity.state = "happy";
        P2Game.score += 1;
      } else
        MainActivity.state = "unhappy";
      P2Game.round++;
      if (P2Game.round<5) {
        MainActivity.oldState = "playing";
        Utils.generateGameNumbers();
      } else {
        MainActivity.oldState = "final game results";
        Animations.oldAnimationCounter = 5;
      }
      MainActivity.myRunnable.j = -1;
      Animations.animation_counter = 8;
      MainActivity.even = 0;
    }
  }
  
  public static void showPlaying() {
    int x = Tama.x;
    int y = Tama.y;
    int W = Tama.W;
    int H = Tama.H;
    canvas.drawBitmap(Graphics.hashMap.get(Tama.character+"_play_"+(MainActivity.even+1)),null, new Rect((16-W/2)*10+offsetX,y*10+offsetY,(16+W/2)*10+offsetX,(y+H)*10+offsetY),paint);
    canvas.drawBitmap(Graphics.hashMap.get("num"+P2Game.givenNumber),null, new Rect(0+offsetX,0+offsetY,40+offsetX,80+offsetY),paint);
    if (MainActivity.myRunnable.i == 0 || MainActivity.myRunnable.i==13)
        Sounds.playSound("flip_sound");
  }

  public static void showGameIntroScreen() {
    int x = 16 - Tama.W/2; int y = Tama.y;
    int W = Tama.W; int H = Tama.H;
    int k=(MainActivity.myRunnable.k*8-270); //magical value that gives good results
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