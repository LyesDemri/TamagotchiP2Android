package com.example.mytama;

import java.lang.Math;

public class SantaUpdater extends Updater  {
  public static void update() {
    //update
    Tama.t++;
    if (Tama.character.equals("cabin")) {
      Cabin.update();
    } else {
      if (!Tama.sleeping) {
        SantaHungryUpdater.update();
        SantaHappyUpdater.update();
        updateDistance();
        updateLeave();
        updateSulking();
      }
      SantaSleepUpdater.update();
    }
  }
  
  public static void updateDistance() {
    int companionSpeed; //TODO: add companionSpeed to SantaTama and just do "+ SantaTama.companionSpeed"
    if (SantaTama.companion.equals("tonanyorotchi")) companionSpeed = 1;
    else if (SantaTama.companion.equals("tonatakotchi")) companionSpeed = 2;
    else if (SantaTama.companion.equals("rednosetchi")) companionSpeed = 3;
    else companionSpeed = 0;
    Printer.logPrint("Companion speed = " + companionSpeed);
    SantaTama.steps += (Math.max(SantaTama.tier, 0) + SantaTama.santaness + SantaTama.characterSpeed + companionSpeed + 1)/SantaTama.weight;
    SantaTama.distance = (int)(SantaTama.steps*14/50544);//50544 is the number of steps to do to reach the children
    SantaTama.distance = Math.min(SantaTama.distance, 14);
    if (SantaTama.distance >= 14) {
      Utils.notifyUser(Tama.name + " is delivering the presents!", "");
      Sounds.playSound("cabin_exit");
      if (Tama.age <= 111 && !SantaTama.arrivedOnTime) {
        SantaTama.arrivedOnTime = true;
      }
      if (Tama.age >= 111 && !SantaTama.endingPlayed) {
        SantaTama.runEnding();
      }
    }
  }
  
  public static void updateLeave() {
    SantaTama.ttleave--;
    if (SantaTama.left) {
      if (SantaTama.ttleave == -900) {
        SantaTama.tier--;
        Printer.logPrint("Tier = " + SantaTama.tier);
        if (SantaTama.tier < -4){
          SantaTama.die();
        } else {
          SantaTama.left = false;
          SantaTama.x = 8;
          SantaTama.ttleave = (5 + SantaTama.tier + SantaTama.santaness)*3600;
          Printer.logPrint("Tier = " + SantaTama.tier);
          Printer.logAppend("Time to leave = " + SantaTama.ttleave);
          if (SantaTama.sulking) {
            SantaTama.timeSinceSulking = 0;
          }
        }
      }
    } else {
      if (SantaTama.ttleave == 0) {
        SantaTama.call();
        Utils.notifyUser(Tama.name + " fled!", "");
        SuperKuchipatchiPainter.initializeAnimation("leaving");
         MainActivity.state = "leaving";
        SantaTama.left = true;
      }
    }
  }
  
  public static void updateSulking(){
    if (!SantaTama.sulking) {
      //if we're not sulking, see if we should sulk
      if (!(Tama.t % 3600 == 0)) return;
      double x = Math.random();
      if (x < (0.05 + (SantaTama.weight - 100)/100)) {
        Utils.notifyUser(Tama.name + " is sulking", "");
        SantaTama.call();
        SantaTama.sulking = true;
        SantaTama.timeSinceSulking = 0;
      }
    } else {
      //if we were already sulking, see if we should lose a tier or leave.
      SantaTama.timeSinceSulking++;
      if (SantaTama.timeSinceSulking == 900) {
        //after 15 minutes of sulking, lose a tier (and maybe die)
        SantaTama.tier--;
        if (SantaTama.tier < -4) SantaTama.die();
      } else if (SantaTama.timeSinceSulking == 3600) {
        //after 1 hour
        if (SantaTama.tier > -4) {
          //leave
          Utils.notifyUser(Tama.name + " fled!", "");
          SantaTama.call();
          SantaTama.ttleave = 0;
          SuperKuchipatchiPainter.initializeAnimation("leaving");
          MainActivity.state = "leaving";
          SantaTama.left = true;
        } else SantaTama.die();
      }
    }
  }
}