package com.example.mytama;

public class Updater  {
  public static boolean allSleeping = false;
  public static int timeToWake;
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
  
  public static void updateAllTamas() {
    allSleeping = true;
    String[] files = DataSaverLoader.getSaveFiles();
    double[] wakeTimes = new double[files.length];
    for (int i = 0; i < files.length; i++) {
      Tama.name = files[i];
      DataSaverLoader.loadData();
      if (!Tama.sleeping) allSleeping = false;
      wakeTimes[i] = Tama.timeToWake - Tama.t;
      DataSaverLoader.saveData();
    }
    if (allSleeping) timeToWake = new Double(Utils.min(wakeTimes)).intValue();
    //Utils.notifyUser("time to wake:" + timeToWake, "");
  }
}