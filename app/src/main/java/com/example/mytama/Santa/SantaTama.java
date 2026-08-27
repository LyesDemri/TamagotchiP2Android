package com.example.mytama;

public class SantaTama extends Tama {
  static public double timeSinceLeft;
  public static int idealWeight;
  public static double weight;
  
  static public boolean isCalling;
  static public boolean left;
  static public boolean sulking;
  public static boolean endingPlayed;
  public static boolean arrivedOnTime;
  public static int[] adventVisits;
  public static int[] santaObjects;
  static public double timeToAge;
  static public int stats_index = 0;
  static public int food = 0;
  static public int snacks = 0;
  static public String[] objects = {"yarn", "paper","chisel", "santa_egg", "perfume", "poison"};
  static public String companion;
  static public int[] bag;
  static public int timeSinceSulking;
  static public int tier;
  static public int ttlhungryh; //time to lose hungry heart
  static public int ttlhappyh;
  static public int timeToEat;
  static public int timeToSnack;
  public static int santaness;
  public static int distance;
  static public void reset() {
    t = 0;
    character = "cabin";
    MainActivity.state = "Cabin";
    stomach = 0;
    happy = 0;
    inputName = "";
    timeSinceHungryChanged = 0;
    timeSinceHungry = 0;
    timeSinceSulking = 0;
    hghlp = 3600;
    hphlp = 3600; 
    happy = 0; 
    timeSinceHappyChanged = 0;
    food = 0; 
    snacks = 0; 
    timeSinceBored = 0;
    //get current millisecond relative to 1970 
    t1970birth = TimeWizard.getTime();
    adventVisits = new int[]{0};
    tier = 0;
    ttlhungryh = 3600;
    ttlhappyh = 3600;
    timeToEat = 3600;
    timeToSnack = 3600;
    distance = 0;
    
    TimeWizard.computeSleepWakeTimes(20,9);
    sleeping = false;
    sulking = false;
    left = false;
    isAlive = true;
    endingPlayed = false;
    arrivedOnTime = false;
    
    bag = new int[]{10, 10, 10, 10, 10, 10};
    santaObjects = new int[]{0, 0, 0, 0};
    companion = "";
    santaness = 0;
    
    MainActivity.menu_index = 0;
    weight = 100;
    idealWeight = 5;
    age = 100;
    x = 8;
    y = 0;
    xIncrement = 1;
    SantaGraphics.loadCharacterGraphics();
    Sounds.playSound("reset_sound");
  }
  
  public static void displayVars(){
    if (MainActivity.debugMode == 1){
      Printer.print("Name:" + name+
          "\nCurrent time: " + t +
          "\n" + age+"yr, " + weight + "oz, " + stomach + "hg, " + happy + "hp" + 
          "\nTSHgC=" + timeSinceHungryChanged+"/" + hghlp +
          "\nTSH=" + timeSinceHungry +
          "\nTSHpC=" + timeSinceHappyChanged + "/" + hphlp +
          "\nTSB=" + timeSinceBored +
          "\nTTS=" + timeToSleep +
          "\nTTW=" + timeToWake +
          "\nalive=" + isAlive +
          "\nstate=" + MainActivity.state +
          "\nbirth date: " + TimeWizard.getBirthDate()+
          "\nsleeping time: " + TimeWizard.getSleepingTime()+
          "\nwaking time: " + TimeWizard.getWakingTime(),
          true);
    }
  }
  
  public static void die(){
    SantaDyingPainter.prepareAnimation();
    MainActivity.state = "dying";
  }
  
  public static void runEnding() {
    if (SantaTama.distance >= 14) {
      SantaEndingPainter.prepareAnimation();
      MainActivity.state = "ending";
    }
  }
  
  public static void receiveObject(int receivedObjectIndex) {
    if (bag[receivedObjectIndex] != -1) {
      bag[receivedObjectIndex] = Math.min(bag[receivedObjectIndex] + 1, 7);
    } else {
      bag[receivedObjectIndex] = 1;
    }
  }
}
