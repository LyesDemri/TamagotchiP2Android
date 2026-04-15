package com.example.mytama;

import java.lang.Math;

public class ButtonB {
  static public void handle() {
    Sounds.playSound("small_beep");
    MainActivity.vibrator.vibrate(50);
    if (MainActivity.state.equals("idle")) {
      IdleButtonB.handle();
    } else if (MainActivity.state.equals("food choice")) {
      if (MainActivity.food_index==0) {
        MainActivity.myRunnable.j=0;
        if (Tama.stomach<4) {
          Animations.animation_counter = 7;
          MainActivity.myRunnable.j = 0;
          MainActivity.state = "eating";
          Tama.stomach=Math.min(Tama.stomach+1,4);
          if (!Tama.character.equals("babytchi"))
            Tama.weight=Math.min(Tama.weight+1,99);
          Tama.timeSinceHungryChanged=0;
          Tama.timeSinceHungry=0;
        } else {
          MainActivity.state = "saying no food";
          Animations.animation_counter=7;
        }
      } else {
        Animations.animation_counter = 7;
        MainActivity.myRunnable.j = 0;
        MainActivity.state  = "eating";
        Tama.happy=Math.min(Tama.happy+1,4);
        if (!Tama.character.equals("Babytchi"))
            Tama.weight=Math.min(Tama.weight+2,99);
        Tama.timeSinceBored=0;
        Tama.timeSinceHappyChanged=0;
        P2Tama.cakesEaten+=1;
      }
      MainActivity.even=0;
    } else if (MainActivity.state.equals("eating")) {
      Animations.animation_counter=0;
      MainActivity.state="food choice";
    } else if (MainActivity.state.equals("saying no food")) {
      Animations.animation_counter=0;
      MainActivity.state="food choice";
    } else if (MainActivity.state.equals("happy") ||
             MainActivity.state.equals("unhappy")||
             MainActivity.state.equals("saying no")) {
      Animations.animation_counter = 0;
      MainActivity.myRunnable.j = 0;
      MainActivity.myRunnable.k = -1;
      MainActivity.state = MainActivity.oldState;
      Animations.animation_counter = Animations.oldAnimationCounter;
    } else if (MainActivity.state.equals("playing")) {
      P2Game.answer="higher";
      Animations.animation_counter=2;
      MainActivity.state="show game result";
      MainActivity.myRunnable.j=0;
    } else if (MainActivity.state.equals("StatScreen1"))
      MainActivity.state="StatScreen2";
    else if (MainActivity.state.equals("StatScreen2"))
      MainActivity.state="StatScreen3";
    else if (MainActivity.state.equals("StatScreen3"))
      MainActivity.state="StatScreen4";
    else if (MainActivity.state.equals("StatScreen4"))
      MainActivity.state="StatScreen1";
    else if (MainActivity.state.equals("Menu")) {
      MenuButtonB.handle();
    } else if (MainActivity.state.equals("dead2"))
      Tama.reset();
    else if (MainActivity.state.equals("clock")) {
      MainActivity.state="idle";
      MainActivity.debugCounter = 0;
    } else if (MainActivity.state.equals("scolded")) {
      MainActivity.state="idle";
      MainActivity.myRunnable.j=0;
      Animations.animation_counter=0;
    }
  }
}