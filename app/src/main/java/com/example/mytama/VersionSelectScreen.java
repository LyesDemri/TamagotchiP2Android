package com.example.mytama;

public class VersionSelectScreen {
  public static void handle(float y){
    MainActivity.vibrator.vibrate(50);
    int selectedLine = (int)(y/(Screen.canvasHeight/10));
    String[] versionList = VersionSelectPainter.versionList;
    if (selectedLine < versionList.length) {
      MainActivity.version = versionList[selectedLine];
      MainActivity.state = "reset_screen";
      Printer.log("Selected version = " + MainActivity.version);
    }
  }
}
