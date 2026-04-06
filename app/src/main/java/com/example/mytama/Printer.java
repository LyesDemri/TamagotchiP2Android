package com.example.mytama;

import java.io.File;

public class Printer {
  static int msgCounter = 0;
  public static void print(Object text) {
    MainActivity.tv.setText("(" + msgCounter + ")" + String.valueOf(text));
    msgCounter++;
  }
  
  public static void append(Object text) {
    MainActivity.tv.append("\n(" + msgCounter + ") " + String.valueOf(text));
    msgCounter++;
  }
  
  public static void displayVars() {
    print("Name:"+Tama.name+
          "\nCurrent time: "+Tama.t+
          "\n"+Tama.age+"yr, "+Tama.discipline+"dspln, " + Tama.weight + "oz, " + Tama.stomach + "hg, " + Tama.happy + "hp" + 
          "\nTSHgC=" + Tama.timeSinceHungryChanged+"/"+Tama.hghlp+
          "\nTSH="+Tama.timeSinceHungry+
          "\nTSHpC="+Tama.timeSinceHappyChanged+"/"+Tama.hphlp+
          "\nTSB="+Tama.timeSinceBored+
          "\nTSLP="+Tama.tslp+"/"+Tama.pp+
          "\nTSD="+Tama.tsd+
          "\nTSS="+Tama.timeSinceSick+" TTGSFA="+Tama.ttgsfa+
          "\nTSND="+Tama.timeSinceNeedsDiscipline+", TFDC="+Tama.tfdc+", DCP="+Tama.dcp+
          "\nTSNLO="+Tama.timeSinceNeedsLightsOff+
          "\nTTS="+Tama.timeToSleep+
          "\nTTW="+Tama.timeToWake+
          "\nlights="+Tama.lightsOn+
          "\nalive="+Tama.isAlive+
          "\nstate="+MainActivity.state+
          "\nCMs="+Tama.careMisses);
  }
}
