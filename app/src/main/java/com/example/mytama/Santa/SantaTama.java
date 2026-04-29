package com.example.mytama;

public class SantaTama extends Tama {
  static public double timeSinceLeft;
  public static int idealWeight;
  
  static public boolean isCalling;
  static public boolean left;
  static public boolean sulking;
  static public double timeToAge;
  static public int stats_index = 0;
  static public int food = 0;
  static public int snacks = 0;
  
  static public void reset() {
    t = 0;
    character = "cabin";
    MainActivity.state = "Cabin";
    stomach = 0;
    inputName = "";
    timeSinceHungryChanged = 0;
    timeSinceHungry = 0;
    hghlp = 3600;
    hphlp = 3600;
    happy = 0;
    timeSinceHappyChanged = 0;
    food = 0;
    snacks = 0;
    timeSinceBored = 0;
    //get current millisecond relative to 1970
    t1970birth = TimeWizard.getTime();
    TimeWizard.computeSleepWakeTimes(20,9);
    sleeping = false;
    sulking = false;
    left = false;
    isAlive = true;
    
    MainActivity.menu_index = 0;
    weight = 100;
    idealWeight = 5;
    age = 100;
    x = 8;
    y = 0;
    xIncrement = 1;
    Graphics.loadCharacterGraphics(MainActivity.context, "babytchi");
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
}
