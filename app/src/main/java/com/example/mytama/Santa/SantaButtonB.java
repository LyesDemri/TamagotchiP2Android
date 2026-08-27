package com.example.mytama;

public class SantaButtonB {
  public static void handle() {
    Sounds.validate();
    MainActivity.vibrator.vibrate(50);
    if (MainActivity.state.equals("idle")) {
      IdleButtonB.handle();
    } else if (MainActivity.state.equals("food choice")) {
      MainActivity.myRunnable.j = 0;
      MainActivity.oldState = "food choice";
      if (MainActivity.food_index == 0) {
        if (SantaTama.food < 4) {
          if (Tama.stomach == 0) {
            Animations.animation_counter = 7;
            MainActivity.state = "eating";
            Tama.stomach = Math.min(Tama.stomach + 1, 4);
            SantaTama.weight = Math.min(SantaTama.weight + 1, 199);
            Tama.timeSinceHungryChanged = 0;
            Tama.timeSinceHungry = 0;
          } else {
            StoringPainter.startStoringAnimation();
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
            SantaTama.weight = Math.min(SantaTama.weight + 2, 199);
            Tama.timeSinceBored = 0;
            Tama.timeSinceHappyChanged = 0;
          } else {
            StoringPainter.startStoringAnimation();
            SantaTama.snacks = Math.min(SantaTama.snacks + 1, 4);
          }
        } else {
          MainActivity.state = "saying no food";
          Animations.animation_counter = 8;
        }
      }
    } else if (MainActivity.state.equals("storing")) {
      MainActivity.state = MainActivity.oldState;
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
      SantaTama.weight = Math.max(SantaTama.weight - 0.5, 100);
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
    } else if (MainActivity.state.equals("advent calendar")) {
      if (SantaTama.adventVisits[Tama.age - 100] == 0) {
        AdventCalendar.generateObject();
        AdventCalendarPainter.prepareAnimation();
        MainActivity.state = "opening advent calendar";
      }
    } else if (MainActivity.state.equals("bag")) {
      if (SantaTama.bag[BagPainter.objectIndex] > 0) {
        SantaTama.bag[BagPainter.objectIndex]--;
        BagPainter.prepareAnimation();
        SantaEvolver.determineEvolution(BagPainter.objectIndex);
        MainActivity.state = "transforming";
      }
    } else if (MainActivity.state.equals("tama tv")) {
      MainActivity.catchingUp = true;
      for (int i = 0; i < 3600*24; i++) {
        Updater.update();
      }
      MainActivity.catchingUp = false;
    } else if (MainActivity.state.equals("ending")) {
      SantaEndingPainter.phase++;
    }
  }
}
