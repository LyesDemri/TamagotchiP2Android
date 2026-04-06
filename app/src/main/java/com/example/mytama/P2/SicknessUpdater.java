package com.example.mytama;

public class SicknessUpdater {
  public static void update() {
    if (Tama.t==1980) {
      Tama.sick = true;
      MainActivity.state = "idle";
      Utils.notifyUser();
    }
    //sickness due to age
    if (Tama.t == Tama.ttgsfa) {
      //sinon il tombe malade et perd 5 minutes de heart loss period
      //heart loss period ne peut être inférieur à 10 min
      Tama.ttgsfa = Tama.ttgsfa+72*3600;
      Tama.sick  =true;
      Utils.notifyUser();
      if (Tama.t > 5*24*3600) {
        Tama.hghlp = Math.max(Tama.hghlp-300,10*60);
        Tama.timeSinceHungryChanged = Math.min(Tama.hghlp-10,Tama.timeSinceHungryChanged);
        Tama.hphlp = Math.max(Tama.hphlp-300,10*60);
        Tama.timeSinceHappyChanged = Math.min(Tama.hphlp-10,Tama.timeSinceHungryChanged);
        Tama.pp = Math.max(Tama.pp-300,10*60);
        Tama.tslp = Math.min(Tama.pp-10,Tama.tslp);
      }
    }
    //sickness due to weight
    if ((Tama.t % 3600)==0) {
      double diff = Tama.weight - Tama.idealWeight;
      if (Math.random() < (diff/100)) {
        Tama.sick = true;
        Utils.notifyUser();
        Tama.hghlp = Math.max(Tama.hghlp - 300, 10*60);
        Tama.timeSinceHungryChanged = Math.min(Tama.hghlp-10, Tama.timeSinceHungryChanged);
        Tama.hphlp = Math.max(Tama.hphlp - 300, 10*60);
        Tama.timeSinceHappyChanged = Math.min(Tama.hphlp-10,Tama.timeSinceHungryChanged);
        Tama.pp = Math.max(Tama.pp - 300, 10*60);
        Tama.tslp = Math.min(Tama.pp - 10,Tama.tslp);
      }
    }
    //Doit on ajouter un care miss ou mourir?
    if ((Tama.sick) && (!Tama.sleeping)) {
      Tama.timeSinceSick += 1;
      if (Tama.timeSinceSick == 15*60)
        Tama.careMisses += 1;
      else if (Tama.timeSinceSick == 12*3600) {
        MainActivity.state = "dead1";
        Tama.isAlive = false;
      }
    }
  }
}
