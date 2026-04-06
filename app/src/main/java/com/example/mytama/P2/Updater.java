package com.example.mytama;

public class Updater  {
  public static void update() {
    //update
    Tama.t++;
    if (Tama.character.equals("egg")) {
      P2Egg.update();
    }
    else {
      if (!Tama.sleeping) {
        HungryUpdater.update();
        HappyUpdater.update();
      }
      PoopUpdater.update();
      SicknessUpdater.update();
      SleepUpdater.update();
      DisciplineUpdater.update();
      //Evolution Stuff
      if (Tama.t == (65*60) || Tama.t == 2*24*3600 || Tama.t == 5*24*3600||
        ((Tama.t == 8*24*3600)&&(Tama.character.equals("Zuccitchi")))) {
        Darwin.evolve();
      }
      //Death stuff
      if (Tama.t == 25*24*3600 || Tama.careMisses >= 50) {
        MainActivity.state = "dead1";
        Tama.isAlive = false;
      }
    }
    
    if (MainActivity.displayVariables)
      Printer.displayVars();
    if (!MainActivity.catchingUp);
      DataSaverLoader.saveData();
  }
}