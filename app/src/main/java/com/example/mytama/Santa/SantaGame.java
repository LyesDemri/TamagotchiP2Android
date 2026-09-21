package com.example.mytama;

import java.lang.Math;



public class SantaGame {
  public static String result;
  public static int selectedChimney = 0;
  public static String receivedObject;
  public static int receivedObjectIndex;
  static String[] results = {"object", "tama", "soot"};
  public static double[] chimneyLuck;
  
  public static void generateResult() {
    double x = Math.random();
    try {
    if (x > chimneyLuck[selectedChimney]) {
      if (x > 0.95 && AdventCalendar.visitedEveryDay() && SantaTama.age >= 102) {
        result = "object";
        int k;
        if (SantaTama.age < 107) k = 3;
        else k = (SantaTama.santaness == 4) ? 6 : 4;
        receivedObjectIndex = (int)(Math.floor(Math.random()*k));
        receivedObject = SantaTama.objects[receivedObjectIndex];
        SantaTama.receiveObject(receivedObjectIndex);
        determineChimneyLuck();
      } else {
        result = "tama";
        SantaGamePainter.sleepingTamaIndex = (int)(Math.floor(Math.random()*3));
      }
      SantaTama.happy = Math.min(SantaTama.happy + 1, 4);
      SantaTama.ttlhappyh = 3600;
    } else {
      result = "soot";
    }
    } catch (Exception e) {
      Printer.append(e, false);
      Printer.append("\nreceived object index: " + receivedObjectIndex, false);
      Printer.append("\nreceived object: " + receivedObject, false);
      Printer.append("\nbag: " + SantaTama.bag, false);
      Printer.append("\nobjects: " + SantaTama.objects, false);
    }
  }
  
  public static void determineChimneyLuck() {
    int[] order = Utils.randperm(3);
    double[] probabilities = {0.25, 0.5, 0.75};
    chimneyLuck = new double[3];
    for (int i = 0; i < chimneyLuck.length; i++)
      chimneyLuck[i] = probabilities[order[i]];
    Printer.logPrint("\nchimneys: " + chimneyLuck[0] + chimneyLuck[1] + chimneyLuck[2]);
  }
}
