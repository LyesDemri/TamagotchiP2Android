package com.example.mytama;

public class VersionSelectScreen {
  public static void handle(float y){
    try {
      MainActivity.vibrator.vibrate(50);
      int selectedLine = (int)(y/(Screen.canvasHeight/10));
      String[] versionList = VersionSelectPainter.versionList;
      if (selectedLine < versionList.length) {
        MainActivity.version = versionList[selectedLine];
        MainActivity.oldState = MainActivity.state;
        MainActivity.state = "reset_screen";
        Printer.print("Selected version = " + MainActivity.version);
        MainActivity.fillIconList();
        Sounds.loadSounds(MainActivity.context);
      }
    } catch (Exception e) {
      Printer.print("Error in VersionSelectScreen: " + e.getMessage());
    }
  }
}
