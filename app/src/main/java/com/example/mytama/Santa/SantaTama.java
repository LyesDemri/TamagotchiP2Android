package com.example.mytama;

public class SantaTama extends Tama {
  static public double timeSinceLeft;
  public static int idealWeight;
  
  static public boolean lightsOn;
  static public boolean isCalling;
  static public double timeToAge;
  
  static public void reset() {
    t = 0;
    character = "cabin";
    MainActivity.state = "Cabin";
    stomach = 0;
    inputName = "";
    timeSinceHungryChanged = 0;
    timeSinceHungry = 0;
    hghlp = 180;
    hphlp = 240;
    happy = 0;
    timeSinceHappyChanged = 0;
    timeSinceBored = 0;
    //get current millisecond relative to 1970
    t1970birth = TimeWizard.getTime();
    TimeWizard.computeSleepWakeTimes(20,9);
    sleeping = false;
    isAlive = true;
    lightsOn = true;
    
    MainActivity.menu_index = 0;
    weight = 100;
    idealWeight = 5;
    age = 100;
    x = 8;
    y = 0;
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
          "\nwaking time: " + TimeWizard.getWakingTime());
    }
  }
}
