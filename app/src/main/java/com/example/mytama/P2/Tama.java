package com.example.mytama;

public class Tama {
  static public int stomach, happy, weight, age, discipline;
  static public double timeSinceNeedsDiscipline;
  static public int hghlp; //hungry heart loss period
  static public int hphlp; //happy heart loss period
  static public int pp; //poop period
  static public double t, timeSinceHungryChanged, timeSinceHungry;
  static public double timeSinceHappyChanged, timeSinceBored;
  static public double tfdc; //time for discipline call
  static public double tslp; //time since last pooped
  static public double dcp; //discipline call period;
  static public double tsd; //time since dirty
  static public double ttgsfa; //time to get sick from age
  static public double timeSinceLeft, timeSinceSick;
  static public double timeSinceNeedsLightsOff;
  static public int sleepingHour, wakingHour, idealWeight;
  
  static public String character, name, inputName;
  
  static public boolean lightsOn, isAlive, sleeping, needsDiscipline;
  static public boolean dirty = false, sick = false, isCalling;
  static public long t1970birth;
  static public double timeToAge, timeToSleep, timeToWake;
  
  static public int careMisses, cakesEaten;
  
  static public int x=8, y;
  static public int W, H;
  static public int xIncrement;
  static public boolean walks;
  
  static public void reset() {
    t = 0;
    character = "egg";
    MainActivity.state = "Egg";
    stomach = 0;
    inputName = "";
    timeSinceHungryChanged = 0;
    timeSinceHungry = 0;
    hghlp = 180;
    hphlp = 240;
    happy = 0;
    timeSinceHappyChanged = 0;
    timeSinceBored = 0;
    tsd = 0;
    tslp = 0;
    //on determine l'instant actuel depuis 1970
    t1970birth = TimeWizard.getTime();
    TimeWizard.computeSleepWakeTimes(20,9);
    pp = 0;
    careMisses = 0;
    sleeping = false;
    isAlive = true;
    lightsOn = true;
    dirty = false;
    sick = false;
    needsDiscipline = false;
    walks = true;
    discipline = 0;
    timeSinceNeedsLightsOff = 0;
    timeSinceSick = 0;
    MainActivity.menu_index = 0;
    weight = 5;
    idealWeight = 5;
    age = 0;
    x = 8;
    y = 0;
    tfdc = 0;
    cakesEaten = 0;
    dcp = 5.5*3600;
    MainActivity.graphics.loadCharacterGraphics(MainActivity.context, "babytchi");
    ttgsfa = 42*3600;
    Sounds.playSound("reset_sound");
  }
}
