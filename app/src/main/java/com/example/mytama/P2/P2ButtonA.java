package com.example.mytama;

public class P2ButtonA {
  public static void handle(){
    Sounds.beep();
    MainActivity.vibrator.vibrate(50);
    if (MainActivity.state.equals("idle") || MainActivity.state.startsWith("dead")) {
      MainActivity.icon_number = (MainActivity.icon_number + 1)%(MainActivity.icon_list.length);
      if (MainActivity.icon_number == 8) {
        Printer.print("Menu");
      }
    } else if (MainActivity.state.equals("food choice")) {
      MainActivity.food_index = 1 - MainActivity.food_index;
    }
    else if (MainActivity.state.equals("eating")) {
      Animations.animation_counter = 0;
      MainActivity.state = "food choice";
    }
    else if (MainActivity.state.equals("saying no food")) {
      Animations.animation_counter = 0;
      MainActivity.state = "food choice";
    }
    else if (MainActivity.state.equals("playing")) {
      P2Game.answer = "lower";
      Animations.animation_counter = 2;
      MainActivity.state = "show game result";
    }
    else if (MainActivity.state.equals("StatScreen1"))
      MainActivity.state="StatScreen2";
    else if (MainActivity.state.equals("StatScreen2"))
      MainActivity.state="StatScreen3";
    else if (MainActivity.state.equals("StatScreen3"))
      MainActivity.state="StatScreen4";
    else if (MainActivity.state.equals("StatScreen4"))
      MainActivity.state="StatScreen1";
    else if (MainActivity.state.equals("happy") ||
             MainActivity.state.equals("unhappy")||
             MainActivity.state.equals("saying no")) {
      Animations.animation_counter = 0;
      MainActivity.myRunnable.k = -1;
      MainActivity.myRunnable.j = 0;
      MainActivity.state = MainActivity.oldState;
      Animations.animation_counter = Animations.oldAnimationCounter;
    }
    else if (MainActivity.state.equals("Menu")) {
      if (MainActivity.displayVariables)
        MainActivity.displayVariables = false;
      else {
        MainActivity.menu_index = (MainActivity.menu_index+1)%(MainActivity.debugMode*(MainActivity.menu_list.length-4)+4);
        Printer.print(MainActivity.menu_list[MainActivity.menu_index]);
      }
    }
    else if (MainActivity.state.equals("scolded")) {
      MainActivity.state = "idle";
      Animations.animation_counter = 0;
    }
    else {
      Printer.log("Unknown state: " + MainActivity.state);
    }
  }
}
