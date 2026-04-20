package com.example.mytama;

import java.io.File;

public class TamaSelectScreen {
  public static void handle(float x, float y) {
    MainActivity.vibrator.vibrate(50);
    int selectedLine = (int)(y/(Screen.canvasHeight/10));
    String[] tamaList = TamaSelectPainter.tamaList;
    if (x < Screen.canvasWidth * 0.9) {
      if (selectedLine < tamaList.length) {
        Tama.name = tamaList[selectedLine];
        try {
          DataSaverLoader.loadData();
        } catch (Exception e) {
          Printer.append("Screen: Error loading tama data: " + e.getMessage());
        }
        MainActivity.menu_index = 0;
        MainActivity.fillIconList();
        Sounds.loadSounds(MainActivity.context);
        Printer.print(Tama.name);
        } else {
        MainActivity.state = "reset_screen";
      }
    } else {
      File file = new File(MainActivity.context.getFilesDir() + "/" + tamaList[selectedLine] + ".txt");
      if (!Tama.name.equals(tamaList[selectedLine])) {
        try {
          file.delete();
        } catch (Exception e) {
          Printer.append("Screen: Error erasing file: " + e.getMessage());
        }
        Printer.print("Deleted " + String.valueOf(file));
      } else {
       Printer.print("Can't delete this one"); 
       Printer.append("You're currently playing with it");
       Printer.append("Switch to another Tama first");
      }
    }   
  }
}
