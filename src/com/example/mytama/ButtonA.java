package com.example.mytama;

public class ButtonA 
{
  public static void handle()
  {
    MainActivity.smallBeep.start();
    if (MainActivity.state.equals("idle")||MainActivity.state.equals("dead"))
    {
      MainActivity.icon_number=(MainActivity.icon_number+1)%(MainActivity.icon_list.length);
      if (MainActivity.icon_number==8)
      {
        MainActivity.tv.setText("Menu");
      }
    }
    else if (MainActivity.state.equals("food choice"))
    {
      MainActivity.food_index=1-MainActivity.food_index;
    }
    else if (MainActivity.state.equals("eating"))
    {
      MainActivity.animation_counter=0;
      MainActivity.state="food choice";
    }
    else if (MainActivity.state.equals("saying no food"))
    {
      MainActivity.animation_counter=0;
      MainActivity.state="food choice";
    }
    else if (MainActivity.state.equals("playing"))
    {
      MainActivity.answer="lower";
      MainActivity.animation_counter=2;
      MainActivity.state="show game result";
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
             MainActivity.state.equals("saying no"))
    {
      MainActivity.animation_counter=0;
      MainActivity.state=MainActivity.oldState;
      MainActivity.animation_counter=MainActivity.oldAnimationCounter;
    }
    else if (MainActivity.state.equals("Menu"))
    {
      if (MainActivity.displayVariables)
        MainActivity.displayVariables=false;
      else
      {
        MainActivity.menu_index+=1;
        if (MainActivity.menu_index==MainActivity.menu_list.length)
        {
          MainActivity.menu_index=0;
        }
        MainActivity.tv.setText(MainActivity.menu_list[MainActivity.menu_index]);
      }
    }
    else if (MainActivity.state.equals("scolded"))
    {
      MainActivity.state="idle";
      MainActivity.animation_counter=0;
    }
    else
    {
      //MainActivity.tv.setText("Unknown state: "+MainActivity.state);
    }
  }
}