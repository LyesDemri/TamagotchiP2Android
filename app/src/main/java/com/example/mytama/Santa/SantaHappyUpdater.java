package com.example.mytama;

public class SantaHappyUpdater extends HappyUpdater {
  public static void update() {
    if (!Tama.sleeping) {
      SantaTama.ttlhappyh--;
      SantaTama.timeToSnack--;
    }
    if (SantaTama.ttlhappyh == 0) {
      if (SantaTama.happy > 0) {
        SantaTama.happy--;
        SantaTama.ttlhappyh = SantaTama.hphlp;
        if (SantaTama.happy == 0) {
          if (SantaTama.snacks > 0) {
            eatRandomAmount();
          } else {
            Utils.notifyUser(Tama.character + " is bored", "");
          }
        }
      }
    }
    if ((((SantaTama.ttlhappyh == (-6*3600)))  // first time around, sulk after 6 hrs
          || ((SantaTama.ttlhappyh < -6*3600) && ((SantaTama.ttlhappyh%(-3600)) == 0))) //after that, sulk after 1 hr
        && !Tama.sleeping) {
      SantaTama.sulking = true;
    }
    if (SantaTama.timeToSnack == 0) {
      eatRandomAmount();
      SantaTama.timeToSnack = 3600;
    }
  }
  
  public static void eatRandomAmount() {
    int max = Math.min(SantaTama.snacks, 4 - SantaTama.happy) + 1;
    int amount = (int)(Math.floor(Math.random()*max));
    if (amount > 0) {
      MainActivity.food_index = 1;
      MainActivity.state = "eating";
      SantaTama.ttlhappyh = 3600;//find a better value and whether to keep this

    }
    SantaTama.happy += amount;
    SantaTama.snacks -= amount;
    SantaTama.weight += 2*amount;
  }
}
