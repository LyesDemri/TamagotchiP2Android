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
            //Eat random amount
            eatRandomAmount();
          } else {
            Utils.notifyUser(Tama.character + " is hungry", "");
          }
        }
      }
    }
    if (SantaTama.ttlhungryh == -900) {
      SantaTama.tier--;
    } else if (SantaTama.ttlhungryh == -12*3600) {
      SantaTama.isAlive = false;
    }
    if (SantaTama.timeToEat == 0) {
      eatRandomAmount();
      SantaTama.timeToEat = 3600;
      SantaTama.ttlhungryh = 3600;//find a better value and whether to keep this
    }
  }
  
  public static void eatRandomAmount() {
    int max = Math.min(SantaTama.food, 4 - SantaTama.stomach)+1;
    int amount = (int)(Math.floor(Math.random()*max));
    if (amount > 0) {
      MainActivity.food_index = 0;
      MainActivity.oldState = "idle";
      MainActivity.state = "eating";
    }
    SantaTama.stomach += amount;
    SantaTama.food -= amount;
    SantaTama.weight += amount;
  }
}
