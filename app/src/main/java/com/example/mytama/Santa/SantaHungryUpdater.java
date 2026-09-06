package com.example.mytama;

import java.lang.Math;

public class SantaHungryUpdater extends HungryUpdater {
  public static void update() {
    if (!Tama.sleeping) {
      SantaTama.ttlhungryh--;
      SantaTama.timeToEat--;
    }
    if (SantaTama.ttlhungryh == 0) {
      if (SantaTama.stomach > 0) {
        SantaTama.stomach--;
        SantaTama.ttlhungryh = SantaTama.hghlp;
        if (SantaTama.stomach == 0) {
          if (SantaTama.food > 0) {
            eatRandomAmount();
          } else {
            Utils.notifyUser(Tama.name + " is hungry", "");
            SantaTama.call();
          }
        }
      }
    }
    if ((((SantaTama.ttlhungryh == (-6*3600)))  // first time around, sulk after 6 hrs
          || ((SantaTama.ttlhungryh < -6*3600) && ((SantaTama.ttlhungryh%(-3600)) == 0))) //after that, sulk after 1 hr
        && !Tama.sleeping) {
      SantaTama.sulking = true;
      SantaTama.call();
      Utils.notifyUser(Tama.name + " is sulking", "");
    }
    if (SantaTama.timeToEat == 0 && !Tama.sleeping) {
      eatRandomAmount();
      SantaTama.timeToEat = 3600;
    }
  }
  
  public static void eatRandomAmount() {
    int max = Math.min(SantaTama.food, 4 - SantaTama.stomach)+1;
    int amount = (int)(Math.floor(Math.random()*max));
    if (amount > 0) {
      MainActivity.food_index = 0;
      MainActivity.oldState = MainActivity.state; // not too sure about this
      Animations.animation_counter = 7;
      MainActivity.state = "eating";
      SantaTama.ttlhungryh = 3600; //find a better value and whether to keep this
      Utils.notifyUser(Tama.name + " is eating", "");
    }
    SantaTama.stomach += amount;
    SantaTama.food -= amount;
    SantaTama.weight += amount;
  }
}
