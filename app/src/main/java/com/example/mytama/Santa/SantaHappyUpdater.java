package com.example.mytama;

public class SantaHappyUpdater extends HappyUpdater {
  public static void update(){
    if (Tama.happy == 0) {
       Tama.timeSinceBored++;
      if ( Tama.timeSinceBored == 900) {
         P2Tama.careMisses++;
      }
      if ( Tama.timeSinceBored == 24*3600) {
        MainActivity.state = "dead1";
         Tama.isAlive = false;
        Utils.notifyUser();
      }
    }
     Tama.timeSinceHappyChanged++;
    if ( Tama.timeSinceHappyChanged ==  Tama.hphlp) {
       Tama.happy=Math.max(Tama.happy-1,0);
       Tama.timeSinceHappyChanged = 0;
      if ( Tama.happy == 0) {
         Tama.isCalling = true;
        Utils.notifyUser();
      }
    }
  }
}
