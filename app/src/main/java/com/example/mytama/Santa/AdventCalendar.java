package com.example.mytama;

import java.lang.Math;

public class AdventCalendar {
  static String generatedObject = "";
  
  public static void generateObject() { 
    // on the eighth day, generate an egg, on other days generate yarn, paper or chisel before the eighth
    //day, and yarn, paper, chisel or egg after the eighth day.
    int objectIndex;
    if (SantaTama.age == 107) objectIndex = 3; // on the eighth day, generate an egg
    else {
      int k = (SantaTama.age < 107) ? 3 : 4;
      objectIndex = (int)(Math.random() * k);
    }
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
