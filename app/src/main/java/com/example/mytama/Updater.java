package com.example.mytama;

public class Updater  {
  public static boolean allSleeping = false;
  public static double timeForNextCall;
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
    allSleeping = true;
    String[] files = DataSaverLoader.getSaveFiles();
    double[] callTimes = new double[files.length];
    for (int i = 0; i < files.length; i++) {
      Tama.name = files[i];
      DataSaverLoader.loadData();
      if (!Tama.sleeping) allSleeping = false;
      if (MainActivity.economyMode) {
        if (MainActivity.version.equals("P2")) callTimes[i] = (double)P2FuturePredictor.calculateNextCallTime();
        else callTimes[i] = 25*60*24; //infinty
      }
      DataSaverLoader.saveData();
      timeForNextCall = Utils.min(callTimes);
    }
  }
}