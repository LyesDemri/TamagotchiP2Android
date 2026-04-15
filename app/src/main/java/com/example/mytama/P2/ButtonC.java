package com.example.mytama;

public class ButtonC
{
  public static void handle() {
    Sounds.playSound("small_beep");
    MainActivity.vibrator.vibrate(50);
    if (MainActivity.state.equals("idle")) {
      if (MainActivity.icon_number != 0) {
        MainActivity.icon_number = 0;
      }
      MainActivity.tv.setText(MainActivity.icon_list[MainActivity.icon_number]);
    }
    else if (MainActivity.state.equals("food choice")) {
      MainActivity.state="idle";
      Tama.x = 16 - Tama.W/2;
    }
    else if (MainActivity.state.equals("saying no food")) {
      Animations.animation_counter=0;
      MainActivity.state="food choice";
      Tama.x = 16 - Tama.W/2;
    }
    else if (MainActivity.state.equals("eating")) {
      Animations.animation_counter=0;
      MainActivity.state="food choice";
    }
    else if (MainActivity.state.equals("playing")) {
      MainActivity.state="idle";
      Tama.x = 16 - Tama.W/2;
    }
    else if (MainActivity.state.equals("StatScreen1"))
      MainActivity.state="idle";
    else if (MainActivity.state.equals("StatScreen2"))
      MainActivity.state="idle";
    else if (MainActivity.state.equals("StatScreen3"))
      MainActivity.state="idle";
    else if (MainActivity.state.equals("StatScreen4"))
      MainActivity.state="idle";
    else if (MainActivity.state.equals("happy") ||
             MainActivity.state.equals("unhappy")||
             MainActivity.state.equals("saying no")) {
      Animations.animation_counter=0;
      if (MainActivity.oldState.equals("game intro screen"))
        MainActivity.state = "idle";
      else
        MainActivity.state = MainActivity.oldState;
      Animations.animation_counter = Animations.oldAnimationCounter;
    }
    else if (MainActivity.state.equals("Menu")){
      MainActivity.state = "idle";
      MainActivity.menu_index=0;
      MainActivity.tv.setText("Menu");
      MainActivity.displayVariables=false;
    }
    else if (MainActivity.state.equals("dead1")){
      MainActivity.state="dead2";
    }
    else if (MainActivity.state.equals("dead2")){
      MainActivity.state="dead1";
    }
    else if (MainActivity.state.equals("scolded")){
      MainActivity.state="idle";
      Animations.animation_counter=0;
    } else if (MainActivity.state.equals("clock")) {
      MainActivity.debugCounter++;
      if (MainActivity.debugCounter == 10) {
        MainActivity.debugCounter = 0;
        MainActivity.debugMode = 1 - MainActivity.debugMode;
        Printer.log("Debug mode = " + MainActivity.debugMode);
      }
    }
  }
}