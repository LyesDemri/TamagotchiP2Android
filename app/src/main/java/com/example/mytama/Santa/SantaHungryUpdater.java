package com.example.mytama;

public class SantaHungryUpdater extends HungryUpdater {
  public static void update() {
    if (Tama.stomach == 0) {
      Tama.timeSinceHungry++;
      if (Tama.timeSinceHungry == 900) {
        Printer.log("15s since hungry");
        //Tama.tier++;
        if (SantaTama.food > 0) {
          Printer.log("Food is available. Eating all of it'");
          SantaTama.stomach = SantaTama.food;
          SantaTama.food = 0;
          Tama.timeSinceHungry = 0;
        }
      }
      if (Tama.timeSinceHungry == 24*3600) {
        MainActivity.state = "cabin";
        Tama.isAlive = false;
        Utils.notifyUser();
      }
    }
    Tama.timeSinceHungryChanged++;
    if (Tama.timeSinceHungryChanged == Tama.hghlp) {
      Tama.stomach = Math.max(Tama.stomach - 1, 0);
      Tama.timeSinceHungryChanged = 0;
      if (Tama.stomach == 0) {
        Tama.isCalling = true;
        Utils.notifyUser();
      }
    }
  }
}
