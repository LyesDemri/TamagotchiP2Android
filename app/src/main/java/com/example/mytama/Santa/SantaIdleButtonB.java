package com.example.mytama;

public class SantaIdleButtonB extends IdleButtonB {
  public static void handle() {
    if (MainActivity.icon_list[MainActivity.icon_number]=="Status") {
      MainActivity.state = "StatScreen0";
    } else if (MainActivity.icon_list[MainActivity.icon_number]=="Food" && Tama.isAlive) {
      if (!Tama.sleeping) {
        if (!SantaTama.sulking && !SantaTama.left) {
          MainActivity.state = "food choice";
          MainActivity.food_index = 0;
          MainActivity.myRunnable.j = 0;
        }
      }
    } else if (MainActivity.icon_list[MainActivity.icon_number]=="Lights" && Tama.isAlive) {
      P2Tama.lightsOn = !P2Tama.lightsOn;
      if (Tama.sleeping) {
        P2Tama.timeSinceNeedsLightsOff = 0;
      }
    } else if (MainActivity.icon_list[MainActivity.icon_number]=="Game" && Tama.isAlive) {
      if (!Tama.sleeping) {
        if (!SantaTama.sulking && !SantaTama.left) {
          MainActivity.state = "game intro screen";
          Sounds.playSound("game_begin");
          MainActivity.myRunnable.k = 0;
        }
      }
    } else if (MainActivity.icon_list[MainActivity.icon_number]=="Medicine" && Tama.isAlive && P2Tama.lightsOn) {
      if (!Tama.sleeping) {
        if (P2Tama.sick) {
          Sounds.playSound("good_sound");
          P2Tama.sick = false;
          P2Tama.timeSinceSick = 0;
          MainActivity.state = "happy";
        } else {
          MainActivity.state = "saying no";      
        }
        MainActivity.oldState = "idle";
        Animations.animation_counter = 8;
        MainActivity.even = 0;
      }
    } else if (MainActivity.icon_list[MainActivity.icon_number]=="Toilet" && Tama.isAlive && P2Tama.lightsOn) {
      if (!Tama.sleeping) {
        MainActivity.state = "washing";
        Animations.animation_counter = 4;
        MainActivity.myRunnable.k = 0;
      }
    } else if (MainActivity.icon_list[MainActivity.icon_number]=="Discipline" && Tama.isAlive && P2Tama.lightsOn) {
      if (P2Tama.needsDiscipline) {
        P2Tama.discipline = Math.min(P2Tama.discipline+1,4);
        P2Tama.needsDiscipline = false;
        P2Tama.timeSinceNeedsDiscipline = 0;
        Sounds.playSound("discipline_sound");
        MainActivity.state = "scolded";
      } else {
        Tama.happy = Math.max(Tama.happy-1,0);
        MainActivity.state = "unhappy";
      }
      MainActivity.oldState = "idle";
      Animations.animation_counter=8;
      MainActivity.even=0;
    } else if (MainActivity.icon_list[MainActivity.icon_number]=="Menu") {
      //Menu:
      if (MainActivity.menu_index==0) {
        MainActivity.state = "Menu";
        MainActivity.menu_index += 1;
        MainActivity.tv.setText(MainActivity.menu_list[MainActivity.menu_index]);
      }
    } else if (MainActivity.icon_number == 0)
      MainActivity.state = "clock";
  }
}
