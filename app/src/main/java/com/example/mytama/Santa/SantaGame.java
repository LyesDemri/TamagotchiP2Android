package com.example.mytama;

import java.lang.Math;

public class SantaGame {
  public static String result;
  public static int selectedChimney = 0;
  public static String receivedObject;
  public static int receivedObjectIndex;
  static String[] results = {"object", "tama", "soot"};
  
  public static void generateResult() {
    double x = Math.random();
    try {
    if (x > 0.5) {
      if (x > 0.95) {
        result = "object";
        receivedObjectIndex = (int)(Math.floor(Math.random()*6));
        receivedObject = SantaTama.objects[receivedObjectIndex];
        SantaTama.receiveObject(receivedObjectIndex);
      } else {
        result = "tama";
        SantaGamePainter.sleepingTamaIndex = (int)(Math.floor(Math.random()*3));
      }
      SantaTama.happy = Math.min(SantaTama.happy + 1, 4);
    } else {
      result = "soot";
    }
    selectedChimney = 0;
    } catch (Exception e) {
      Printer.print(e, false);
      Printer.append("received object index: " + receivedObjectIndex, false);
      Printer.append("received object: " + receivedObject, false);
      Printer.append("bag: " + SantaTama.bag, false);
      Printer.append("objects: " + SantaTama.objects, false);
    }
  }
}
