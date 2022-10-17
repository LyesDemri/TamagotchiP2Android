package com.example.mytama;

import java.lang.Math;
import android.content.Intent;

public class ButtonB {
  static public void handle() {
    MainActivity.smallBeep.start();
    if (MainActivity.state.equals("idle")) {
      if (MainActivity.icon_list[MainActivity.icon_number]=="Food" && MainActivity.isAlive) {
        if (!MainActivity.sleeping) {
          if (!MainActivity.sick&&!MainActivity.needsDiscipline) {
            MainActivity.state="food choice";
            MainActivity.food_index=0;
          }
          else {
            MainActivity.state="saying no";
            MainActivity.oldState="idle";
            MainActivity.animation_counter=8;
            MainActivity.myRunnable.j=0;
          }
        }
      }
      else if (MainActivity.icon_list[MainActivity.icon_number]=="Lights" && MainActivity.isAlive) {
        MainActivity.lightsOn = !MainActivity.lightsOn;
        if (MainActivity.sleeping) {
          MainActivity.timeSinceNeedsLightsOff=0;
        }
      }
      else if (MainActivity.icon_list[MainActivity.icon_number]=="Game" && MainActivity.isAlive) {
        if (!MainActivity.sleeping) {
          if (!MainActivity.sick && !MainActivity.needsDiscipline) {
            MainActivity.state="game intro screen";
            MainActivity.animation_counter=6;
            Utils.generateGameNumbers();
            MainActivity.score=0;
            MainActivity.round=0;
            MainActivity.myRunnable.k = -1;
            MainActivity.myRunnable.j = 0;
          }
          else {
            MainActivity.state="saying no";
            MainActivity.animation_counter=8;
            MainActivity.oldState="idle";
          }
        }
      }
      else if (MainActivity.icon_list[MainActivity.icon_number]=="Medicine" && MainActivity.isAlive) {
        if (!MainActivity.sleeping) {
          if (MainActivity.sick||MainActivity.needsDiscipline) {
            MainActivity.sick=false;
            MainActivity.timeSinceSick=0;
            MainActivity.state="happy";
          }
          else {
            MainActivity.state="saying no";      
          }
          MainActivity.oldState="idle";
          MainActivity.animation_counter=8;
          MainActivity.even=0;
        }
      }
      else if (MainActivity.icon_list[MainActivity.icon_number]=="Toilet" && MainActivity.isAlive) {
        if (!MainActivity.sleeping) {
          MainActivity.state="washing";
          MainActivity.animation_counter=4;
          MainActivity.myRunnable.k=0;
        }
      }
      else if (MainActivity.icon_list[MainActivity.icon_number]=="Status") {
        MainActivity.state="StatScreen1";
      }
      else if (MainActivity.icon_list[MainActivity.icon_number]=="Discipline" && MainActivity.isAlive) {
        if (MainActivity.needsDiscipline) {
          MainActivity.discipline=Math.min(MainActivity.discipline+1,4);
          MainActivity.needsDiscipline=false;
          MainActivity.timeSinceNeedsDiscipline=0;
          //MainActivity.tv.setText("Discipline:"+String.valueOf(MainActivity.discipline));
          MainActivity.disciplineSound.start();
          MainActivity.state="scolded";
        }
        else {
          MainActivity.happy=Math.max(MainActivity.happy-1,0);
          MainActivity.state="unhappy";
        }
        MainActivity.oldState="idle";
        MainActivity.animation_counter=8;
        MainActivity.even=0;
      }
      else if (MainActivity.icon_list[MainActivity.icon_number]=="Menu") {
        //Menu:
        if (MainActivity.menu_index==0) {
          MainActivity.state="Menu";
          MainActivity.menu_index+=1;
          MainActivity.tv.setText(MainActivity.menu_list[MainActivity.menu_index]);
        }
      }
      else
        MainActivity.state="clock";
    }
    else if (MainActivity.state.equals("food choice")) {
      if (MainActivity.food_index==0) {
        MainActivity.myRunnable.j=0;
        if (MainActivity.stomach<4) {
          MainActivity.animation_counter=7;
          MainActivity.state = "eating";
          MainActivity.stomach=Math.min(MainActivity.stomach+1,4);
          if (!MainActivity.character.equals("Babytchi"))
            MainActivity.weight=Math.min(MainActivity.weight+1,99);
          MainActivity.timeSinceHungryChanged=0;
          MainActivity.timeSinceHungry=0;
        }
        else {
          MainActivity.state = "saying no food";
          MainActivity.animation_counter=7;
        }
      }
      else {
        MainActivity.animation_counter=7;
        MainActivity.state = "eating";
        MainActivity.happy=Math.min(MainActivity.happy+1,4);
        if (!MainActivity.character.equals("Babytchi"))
            MainActivity.weight=Math.min(MainActivity.weight+2,99);
        MainActivity.timeSinceBored=0;
        MainActivity.timeSinceHappyChanged=0;
        MainActivity.cakesEaten+=1;
      }
      MainActivity.even=0;
    }
    else if (MainActivity.state.equals("eating")) {
      MainActivity.animation_counter=0;
      MainActivity.state="food choice";
    }
    else if (MainActivity.state.equals("saying no food")) {
      MainActivity.animation_counter=0;
      MainActivity.state="food choice";
    }
    else if (MainActivity.state.equals("happy") ||
             MainActivity.state.equals("unhappy")||
             MainActivity.state.equals("saying no")) {
      MainActivity.animation_counter = 0;
      MainActivity.myRunnable.j = 0;
      MainActivity.myRunnable.k = -1;
      MainActivity.state = MainActivity.oldState;
      MainActivity.animation_counter = MainActivity.oldAnimationCounter;
    }
    else if (MainActivity.state.equals("playing")) {
      MainActivity.answer="higher";
      MainActivity.animation_counter=2;
      MainActivity.state="show game result";
      MainActivity.myRunnable.j=0;
    }
    else if (MainActivity.state.equals("StatScreen1"))
      MainActivity.state="StatScreen2";
    else if (MainActivity.state.equals("StatScreen2"))
      MainActivity.state="StatScreen3";
    else if (MainActivity.state.equals("StatScreen3"))
      MainActivity.state="StatScreen4";
    else if (MainActivity.state.equals("StatScreen4"))
      MainActivity.state="StatScreen1";
    else if (MainActivity.state.equals("Menu")) {
      MainActivity.tv.setText("we are in the menu");
      if (MainActivity.menu_index==1) {
        Utils.reset();
        /*Intent myIntent = new Intent(MainActivity.mainActivityContext,TamaSelectionActivity.class);
        myIntent.putExtra("key",1);
        MainActivity.mainActivityContext.startActivity(myIntent);*/
      }
      else if (MainActivity.menu_index==2)
        MainActivity.displayVariables=!MainActivity.displayVariables;
    }
    else if (MainActivity.state.equals("dead2"))
      Utils.reset();
    else if (MainActivity.state.equals("clock"))
      MainActivity.state="idle";
    else if (MainActivity.state.equals("scolded")) {
      MainActivity.state="idle";
      MainActivity.myRunnable.j=0;
      MainActivity.animation_counter=0;
    }
  }
}