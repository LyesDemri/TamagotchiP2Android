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
  
  public static boolean visitedEveryDay() {
    boolean flag = true;
    for (int i = 0; i < SantaTama.adventVisits.length; i++) {
      if (SantaTama.adventVisits[i] == 0) flag = false;
    }
    return flag;
  }
}
