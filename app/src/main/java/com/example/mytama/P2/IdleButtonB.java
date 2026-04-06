package com.example.mytama;

public class IdleButtonB {
  public static void handle() {
    if (MainActivity.icon_list[MainActivity.icon_number]=="Food" && Tama.isAlive && Tama.lightsOn) {
      if (!Tama.sleeping) {
        if (!Tama.sick && !Tama.needsDiscipline) {
          MainActivity.state = "food choice";
          MainActivity.food_index = 0;
          MainActivity.myRunnable.j = 0;
        } else {
          MainActivity.state="saying no";
          MainActivity.oldState="idle";
          Animations.animation_counter = 8;
          MainActivity.myRunnable.j = 0;
        }
      }
    } else if (MainActivity.icon_list[MainActivity.icon_number]=="Lights" && Tama.isAlive) {
      Tama.lightsOn = !Tama.lightsOn;
      if (Tama.sleeping) {
        Tama.timeSinceNeedsLightsOff = 0;
      }
    } else if (MainActivity.icon_list[MainActivity.icon_number]=="Game" && Tama.isAlive && Tama.lightsOn) {
      if (!Tama.sleeping) {
        if (!Tama.sick && !Tama.needsDiscipline) {
          MainActivity.state = "game intro screen";
          Animations.animation_counter = 6;
          Utils.generateGameNumbers();
          P2Game.score = 0;
          P2Game.round = 0;
          MainActivity.myRunnable.k = -1;
          MainActivity.myRunnable.j = 0;
        } else {
          MainActivity.state = "saying no";
          Animations.animation_counter = 8;
          MainActivity.oldState = "idle";
        }
      }
    } else if (MainActivity.icon_list[MainActivity.icon_number]=="Medicine" && Tama.isAlive && Tama.lightsOn) {
      if (!Tama.sleeping) {
        if (Tama.sick) {
          Sounds.playSound("good_sound");
          Tama.sick = false;
          Tama.timeSinceSick = 0;
          MainActivity.state = "happy";
        } else {
          MainActivity.state = "saying no";      
        }
        MainActivity.oldState = "idle";
        Animations.animation_counter = 8;
        MainActivity.even = 0;
      }
    } else if (MainActivity.icon_list[MainActivity.icon_number]=="Toilet" && Tama.isAlive && Tama.lightsOn) {
      if (!Tama.sleeping) {
        MainActivity.state="washing";
        Animations.animation_counter=4;
        MainActivity.myRunnable.k=0;
      }
    } else if (MainActivity.icon_list[MainActivity.icon_number]=="Status") {
      MainActivity.state="StatScreen1";
    } else if (MainActivity.icon_list[MainActivity.icon_number]=="Discipline" && Tama.isAlive && Tama.lightsOn) {
      if (Tama.needsDiscipline) {
        Tama.discipline=Math.min(Tama.discipline+1,4);
        Tama.needsDiscipline=false;
        Tama.timeSinceNeedsDiscipline=0;
        Sounds.playSound("discipline_sound");
        MainActivity.state="scolded";
      } else {
        Tama.happy=Math.max(Tama.happy-1,0);
        MainActivity.state="unhappy";
      }
      MainActivity.oldState="idle";
      Animations.animation_counter=8;
      MainActivity.even=0;
    } else if (MainActivity.icon_list[MainActivity.icon_number]=="Menu") {
      //Menu:
      if (MainActivity.menu_index==0) {
        MainActivity.state="Menu";
        MainActivity.menu_index+=1;
        MainActivity.tv.setText(MainActivity.menu_list[MainActivity.menu_index]);
      }
    } else if (MainActivity.icon_number == 0)
      MainActivity.state="clock";
  }
}
