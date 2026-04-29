package com.example.mytama;

public class SantaButtonB {
  public static void handle() {
    Sounds.validate();
    MainActivity.vibrator.vibrate(50);
    if (MainActivity.state.equals("idle")) {
      IdleButtonB.handle();
    } else if (MainActivity.state.equals("food choice")) {
      MainActivity.myRunnable.j = 0;
      if (MainActivity.food_index == 0) {
        if (SantaTama.food < 4) {
          if (Tama.stomach == 0) {
            Animations.animation_counter = 7;
            MainActivity.state = "eating";
            Tama.stomach = Math.min(Tama.stomach + 1, 4);
            Tama.weight = Math.min(Tama.weight + 1, 199);
            Tama.timeSinceHungryChanged = 0;
            Tama.timeSinceHungry = 0;
          } else {
            Animations.animation_counter = 9;
            MainActivity.state = "storing";
            SantaTama.food = Math.min(SantaTama.food + 1, 4);
          }
        } else {
          MainActivity.state = "saying no food";
          Animations.animation_counter = 8;
        }
      } else {
        if (SantaTama.snacks < 4) {
          if (SantaTama.happy == 0) {
            Animations.animation_counter = 7;
            MainActivity.state  = "eating";
            Tama.happy = Math.min(Tama.happy + 1, 4);
            Tama.weight = Math.min(Tama.weight + 2, 199);
            Tama.timeSinceBored = 0;
            Tama.timeSinceHappyChanged = 0;
          } else {
            Animations.animation_counter = 9;
            MainActivity.state = "storing";
            SantaTama.snacks = Math.min(SantaTama.snacks + 1, 4);
          }
        } else {
          MainActivity.state = "saying no food";
          Animations.animation_counter = 8;
        }
      }
    } else if (MainActivity.state.equals("storing")) {
      MainActivity.state = "food choice";
    } else if (MainActivity.state.equals("eating")) {
      Animations.animation_counter = 0;
      MainActivity.state = "food choice";
    } else if (MainActivity.state.equals("saying no food")) {
      Animations.animation_counter = 0;
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
      Animations.animation_counter = 5;
      MainActivity.state = "getting in chimney";
      SantaGame.generateResult();
      MainActivity.myRunnable.j = 0;
    } else if (MainActivity.state.equals("StatScreen0")) {
      if (SantaTama.stats_index == 0) {
        MainActivity.state = "StatScreen1";
      } else {
        MainActivity.state = "Pantry1";
      }
    } else if (MainActivity.state.equals("StatScreen1"))
      MainActivity.state = "StatScreen2";
    else if (MainActivity.state.equals("StatScreen2"))
      MainActivity.state = "StatScreen3";
    else if (MainActivity.state.equals("StatScreen3"))
      MainActivity.state = "StatScreen4";
    else if (MainActivity.state.equals("StatScreen4"))
      MainActivity.state = "StatScreen5";
    else if (MainActivity.state.equals("StatScreen5"))
      MainActivity.state = "StatScreen1";
    else if (MainActivity.state.equals("Pantry1")){
      MainActivity.state = "Pantry2";
    } else if (MainActivity.state.equals("Pantry2")){
      MainActivity.state = "Pantry1";
    }
    else if (MainActivity.state.equals("Menu")) {
      MenuButtonB.handle();
    } else if (MainActivity.state.equals("dead2"))
      Tama.reset();
    else if (MainActivity.state.equals("clock")) {
      MainActivity.state = "idle";
      MainActivity.debugCounter = 0;
    } else if (MainActivity.state.equals("scolded")) {
      MainActivity.state = "idle";
      MainActivity.myRunnable.j = 0;
      Animations.animation_counter = 0;
    }
  }
}
