package com.example.mytama;

public class Tama {
  static public int stomach, happy, weight, age;
  static public int hghlp; //hungry heart loss period
  static public int hphlp; //happy heart loss period
  static public int sleepingHour, wakingHour;
  public static int xIncrement; // for walking animations
  
  public static boolean isAlive, sleeping, isCalling;
  static public long t1970birth;
  public static double t, timeSinceHungryChanged, timeSinceHungry;;
  static public double timeSinceHappyChanged, timeSinceBored;
  public static double timeSinceLeft, timeToSleep, timeToWake;
  static public int x = 8, y;
  static public int W, H;
  static public String character, name, inputName;

  static public void reset() {
    if (MainActivity.version.equals("P2")){
      P2Tama.reset();
    } else if (MainActivity.version.equals("Santa")){
      SantaTama.reset();
    }
  }
  
  public static void displayVars(){
    if (MainActivity.version.equals("P2")){
      P2Tama.displayVars();
    } else if (MainActivity.version.equals("Santa")){
      SantaTama.displayVars();
    }
  }
}
