package com.example.mytama;

public class P2HungryUpdater extends HungryUpdater {
  public static void update() {
    if (Tama.stomach == 0) {
      Tama.timeSinceHungry++;
      if (Tama.timeSinceHungry == 900) {
        P2Tama.careMisses++;
      }
      if (Tama.timeSinceHungry == 24*3600) {
        MainActivity.state = "dead1";
        Tama.isAlive = false;
        Utils.notifyUser();
      }
    }
    Tama.timeSinceHungryChanged++;
    if (Tama.timeSinceHungryChanged == Tama.hghlp) {
      Tama.stomach = Math.max(Tama.stomach-1, 0);
      Tama.timeSinceHungryChanged = 0;
      if (Tama.stomach == 0) {
        Tama.isCalling = true;
        Utils.notifyUser();
      }
    }
  }
}
