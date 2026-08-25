package com.example.mytama;

import java.lang.Math;

public class AdventCalendar {
  static String generatedObject = "";
  
  public static void generateObject() { 
    int objectIndex = (int)(Math.random() * 4);
    generatedObject = SantaTama.objects[objectIndex];
    SantaTama.receiveObject(objectIndex);
    SantaTama.adventVisits[Tama.age - 100] = 1;
  }
}
