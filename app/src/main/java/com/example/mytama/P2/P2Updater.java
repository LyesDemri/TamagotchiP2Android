package com.example.mytama;

public class P2Updater extends Updater {
  public static void update() {
    //update
    Tama.t++;
    if (Tama.character.equals("egg")) {
      P2Egg.update();
    }
    else {
      if (!Tama.sleeping) {
        P2HungryUpdater.update();
        P2HappyUpdater.update();
      }
      PoopUpdater.update();
      SicknessUpdater.update();
      P2SleepUpdater.update();
      DisciplineUpdater.update();
      //Evolution Stuff
      if (Tama.t == (65*60) || Tama.t == 2*24*3600 || Tama.t == 6*24*3600||
        ((Tama.t == 9*24*3600)&&(Tama.character.equals("zuccitchi")))) {
        Darwin2.evolve();
      }
      //Death stuff
      if (Tama.t == 25*24*3600 || P2Tama.careMisses >= 50) {
        MainActivity.state = "dead1";
        Tama.isAlive = false;
      }
    }
  }
  
  public static void skipDuration(int dur) {
    MainActivity.catchingUp = true;
    for (int i = 0; i < dur; i++) {
      Updater.update();
    }
    MainActivity.catchingUp = false;
    Printer.print("Done fast forwarding", false);
    Printer.append("t =" + Tama.t, false);
    Printer.append("Tama time: " + TimeWizard.getTamagotchiTime(), false);
  }
}