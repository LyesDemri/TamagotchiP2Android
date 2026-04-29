package com.example.mytama;

import java.lang.Math;

public class SantaGame {
  public static String result;
  
  public static void generateResult(){
    double x = Math.random();
    if (x > 0.5){
      if (x > 0.75) {
        result = "object";
        Printer.print("received object", false);
      } else {
        result = "tama";
        SantaGamePainter.sleepingTamaIndex = (int)(Math.floor(Math.random()*3));
        Printer.print("found sleeping tama", false);
      }
    } else {
      result = "soot";
      Printer.print("God dammit!", false);
    }
  }
}
