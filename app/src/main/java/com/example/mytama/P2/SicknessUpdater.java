package com.example.mytama;

public class SicknessUpdater {
  public static void update() {
    if (Tama.t==1980) {
      P2Tama.sick = true;
      MainActivity.state = "idle";
      Utils.notifyUser();
    }
    //sickness due to age
    if (Tama.t == P2Tama.ttgsfa) {
      //sinon il tombe malade et perd 5 minutes de heart loss period
      //heart loss period ne peut être inférieur à 10 min
      P2Tama.ttgsfa = P2Tama.ttgsfa+72*3600;
      P2Tama.sick  = true;
      Utils.notifyUser();
      if (Tama.t > 5*24*3600) {
        Tama.hghlp = Math.max(Tama.hghlp-300,10*60);
        Tama.timeSinceHungryChanged = Math.min(Tama.hghlp-10,Tama.timeSinceHungryChanged);
        Tama.hphlp = Math.max(Tama.hphlp-300,10*60);
        Tama.timeSinceHappyChanged = Math.min(Tama.hphlp-10,Tama.timeSinceHungryChanged);
        P2Tama.pp = Math.max(P2Tama.pp-300,10*60);
        P2Tama.tslp = Math.min(P2Tama.pp-10, P2Tama.tslp);
      }
    }
    //sickness due to weight
    if ((Tama.t % 3600)==0) {
      double diff = Tama.weight - P2Tama.idealWeight;
      if (Math.random() < (diff/100)) {
        P2Tama.sick = true;
        Utils.notifyUser();
        Tama.hghlp = Math.max(Tama.hghlp - 300, 10*60);
        Tama.timeSinceHungryChanged = Math.min(Tama.hghlp-10, Tama.timeSinceHungryChanged);
        Tama.hphlp = Math.max(Tama.hphlp - 300, 10*60);
        Tama.timeSinceHappyChanged = Math.min(Tama.hphlp-10,Tama.timeSinceHungryChanged);
        P2Tama.pp = Math.max(P2Tama.pp - 300, 10*60);
        P2Tama.tslp = Math.min(P2Tama.pp - 10, P2Tama.tslp);
      }
    }
    //Doit on ajouter un care miss ou mourir?
    if ((P2Tama.sick) && (!Tama.sleeping)) {
      P2Tama.timeSinceSick += 1;
      if (P2Tama.timeSinceSick == 15*60)
        P2Tama.careMisses += 1;
      else if (P2Tama.timeSinceSick == 12*3600) {
        MainActivity.state = "dead1";
        Tama.isAlive = false;
      }
    }
  }
}
