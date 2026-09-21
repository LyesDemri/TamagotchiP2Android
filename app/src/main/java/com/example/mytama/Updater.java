package com.example.mytama;

public class Updater  {
  public static void update() {
    if (MainActivity.version.equals("P2")) {
      P2Updater.update();
    } else if (MainActivity.version.equals("Santa")) {
      SantaUpdater.update();
    }
    
    if (MainActivity.displayVariables)
      Printer.displayVars();
    if (!MainActivity.catchingUp)
      DataSaverLoader.saveData();
  }
  
  public static void skipDuration(int dur) {
    MainActivity.catchingUp = true;
    for (int i = 0; i < dur; i++) {
      Updater.update();
    }
    MainActivity.catchingUp = false;
    Printer.print("t =" + Tama.t, false);
    Printer.append("Tama time: " + TimeWizard.getTamagotchiTime(), false);
  }
  
  public static void updateAllTamas(){
    String[] files = DataSaverLoader.getSaveFiles();
    for (int i = 0; i < files.length; i++) {
      Tama.name = files[i];
      DataSaverLoader.loadData();
      Tama.updatesWhileAbsent++;
      DataSaverLoader.saveData();
    }
  }
}