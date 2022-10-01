package com.example.mytama;

public class ButtonC
{
  public static void handle() {
    MainActivity.smallBeep.start();
    if (MainActivity.state.equals("idle")) {
      if (MainActivity.icon_number!=0) {
        MainActivity.icon_number=0;
      }
      else {
        //MainActivity.t=0;
      }
      MainActivity.tv.setText(MainActivity.icon_list[MainActivity.icon_number]);
    }
    else if (MainActivity.state.equals("food choice")) {
      MainActivity.state="idle";
      MainActivity.x=16-MainActivity.W/2;
    }
    else if (MainActivity.state.equals("saying no food")) {
      MainActivity.animation_counter=0;
      MainActivity.state="food choice";
      MainActivity.x=16-MainActivity.W/2;
    }
    else if (MainActivity.state.equals("eating")) {
      MainActivity.animation_counter=0;
      MainActivity.state="food choice";
    }
    else if (MainActivity.state.equals("playing")) {
      MainActivity.state="idle";
      MainActivity.x=16-MainActivity.W/2;
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
      MainActivity.animation_counter=0;
      if (MainActivity.oldState.equals("game intro screen"))
        MainActivity.state = "idle";
      else
        MainActivity.state=MainActivity.oldState;
      MainActivity.animation_counter=MainActivity.oldAnimationCounter;
    }
    else if (MainActivity.state.equals("Menu")){
      MainActivity.state="idle";
      MainActivity.menu_index=0;
      MainActivity.tv.setText("Menu");
      MainActivity.displayVariables=false;
    }
    else if (MainActivity.state.equals("dead1")){
      MainActivity.state="dead2";
      //MainActivity.tv.setText("skipping to dead2");
    }
    else if (MainActivity.state.equals("dead2")){
      MainActivity.state="dead1";
      MainActivity.tv.setText("");
    }
    else if (MainActivity.state.equals("scolded")){
      MainActivity.state="idle";
      MainActivity.animation_counter=0;
    }
  }
}