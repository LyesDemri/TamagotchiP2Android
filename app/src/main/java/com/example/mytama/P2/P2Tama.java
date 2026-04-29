package com.example.mytama;

public class P2Tama extends Tama{
  public static int discipline;
  static public double timeSinceNeedsDiscipline;
  static public int pp; //poop period
  static public double tfdc; //time for discipline call
  static public double tslp; //time since last pooped
  static public double dcp; //discipline call period;
  static public double tsd; //time since dirty
  static public double ttgsfa; //time to get sick from age
  static public double timeSinceSick;
  static public double timeSinceNeedsLightsOff;
  static public int idealWeight;
  
    
  static public boolean lightsOn, needsDiscipline;
  static public boolean dirty = false, sick = false, superTeen;
  static public double timeToAge;
  
  static public int careMisses, cakesEaten, disciplineMistakes;
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
    //get current millisecond relative to 1970
    t1970birth = TimeWizard.getTime();
    TimeWizard.computeSleepWakeTimes(20,9);
    pp = 0;
    careMisses = 0;
    disciplineMistakes = 0;
    sleeping = false;
    isAlive = true;
    lightsOn = true;
    dirty = false;
    sick = false;
    needsDiscipline = false;
    superTeen = false;
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
    Graphics.loadCharacterGraphics(MainActivity.context, "babytchi");
    ttgsfa = 42*3600;
    Sounds.playSound("reset_sound");
  }
  
  public static void displayVars(){
    if (MainActivity.debugMode == 1){
      Printer.print("Name:" + name + 
          "\nCurrent time: "+ t +
          "\n" + age + "yr, " + discipline + "dspln, " + weight + "oz, " + stomach + "hg, " + happy + "hp" + 
          "\nTSHgC=" + timeSinceHungryChanged + "/" + hghlp+
          "\nTSH=" + timeSinceHungry +
          "\nTSHpC=" + timeSinceHappyChanged+"/" + hphlp+
          "\nTSB=" + timeSinceBored+
          "\nTSLP=" + tslp + "/" + pp +
          "\nTSD=" + tsd + 
          "\nTSS=" + timeSinceSick + " TTGSFA=" + ttgsfa+
          "\nTSND=" + timeSinceNeedsDiscipline+", TFDC=" + tfdc+", DCP=" + dcp+
          "\nTSNLO=" + timeSinceNeedsLightsOff+
          "\nTTS=" + timeToSleep +
          "\nTTW=" + timeToWake +
          "\nlights=" + lightsOn+
          "\nalive=" + isAlive+
          "\nstate=" + MainActivity.state+
          "\nbirth date: " + TimeWizard.getBirthDate()+
          "\nsleeping time: " + TimeWizard.getSleepingTime()+
          "\nwaking time: " + TimeWizard.getWakingTime()+
          "\nCMs=" + careMisses +
          "\nDMs=" +disciplineMistakes,
          false);
    }
  }
}
