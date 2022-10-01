package com.example.mytama;

import java.util.Date;

public class Updater 
{
  public static void update()
  {
    /*
    Babytchi will 
    poop 15 minutes(x) after being born, 
    get sick after 33 minutes (x), 
    fall asleep after 40 minutes, and 
    wake up and poop a second time after 45 minutes. 
    */

    //update
    MainActivity.t+=0.5;
    //MainActivity.tv.setText(String.valueOf(MainActivity.t));
    if (MainActivity.state.equals("Egg"))
    {
      //MainActivity.tv.setText("tmgc is an egg");
      if (MainActivity.t==5)
      {
        //MainActivity.tv.setText("tmgc starts to hatch");
        MainActivity.state="hatching";
        MainActivity.hatchingSound.start();
      }
    }
    else if (MainActivity.state.equals("hatching"))
    {
      //MainActivity.tv.setText("tmgc is hatching");
      if (MainActivity.t==10)
      {
        //MainActivity.tv.setText("tmgc became babytchi");
        MainActivity.character="Babytchi";
        MainActivity.graphics.loadCharacterGraphics(MainActivity.mainActivityContext,MainActivity.character);
        MainActivity.state="idle";
        MainActivity.x=12;
        MainActivity.y=8;
        MainActivity.callSound.start();
        Utils.notifyUser();
      }
    }
    else
    {
      if (!MainActivity.sleeping)
      {
        //-------------------------------HUNGER STUFF--------------------------
        if (MainActivity.stomach == 0)
        {
          MainActivity.timeSinceHungry = MainActivity.timeSinceHungry + 0.5;
          if (MainActivity.timeSinceHungry == 900)
          {
            MainActivity.careMisses = MainActivity.careMisses+1;
          }
          if (MainActivity.timeSinceHungry == 24*3600)
          {
            
            MainActivity.state = "dead1";
            MainActivity.isAlive = false;
            Utils.notifyUser();
          }
        }
        MainActivity.timeSinceHungryChanged+=0.5;
        if (MainActivity.timeSinceHungryChanged==MainActivity.hghlp)
        {
          MainActivity.stomach=Math.max(MainActivity.stomach-1,0);
          MainActivity.timeSinceHungryChanged=0;
          if (MainActivity.stomach==0)
          {
            MainActivity.isCalling=true;
            Utils.notifyUser();
          }
        }
    
        //-------------------------------HAPPY STUFF--------------------------
        if (MainActivity.happy == 0)
        {
          MainActivity.timeSinceBored = MainActivity.timeSinceBored + 0.5;
          if (MainActivity.timeSinceBored == 900)
          {
            MainActivity.careMisses = MainActivity.careMisses+1;
          }
          if (MainActivity.timeSinceBored == 24*3600)
          {
            MainActivity.state = "dead1";
            MainActivity.isAlive = false;
            Utils.notifyUser();
          }
        }
        MainActivity.timeSinceHappyChanged+=0.5;
        if (MainActivity.timeSinceHappyChanged==MainActivity.hphlp)
        {
          MainActivity.happy=Math.max(MainActivity.happy-1,0);
          MainActivity.timeSinceHappyChanged=0;
          if (MainActivity.happy==0)
          {
            MainActivity.isCalling=true;
            Utils.notifyUser();
          }
        }
      }
      //-------------------------------POOP STUFF--------------------------
      if (MainActivity.character.equals("Babytchi"))
      {
        if (MainActivity.t==900||MainActivity.t==2700+5)
          poop();
      }
      else
      {
        //MainActivity.tv.setText("not Babytchi");
        if (!MainActivity.sleeping&&!MainActivity.state.equals("Egg"))
        {
          //MainActivity.tv.setText("not sleeping (tslp="+MainActivity.tslp+")");
          MainActivity.tslp+=0.5;
          if (MainActivity.tslp==MainActivity.pp)
            poop();
        }
      }
      if (MainActivity.dirty)
      {
        if (!MainActivity.character.equals("Egg"))
          MainActivity.tsd+=0.5;
        if (MainActivity.tsd==15*60)
          MainActivity.careMisses+=1;
        if (MainActivity.tsd==12*60*60)
          MainActivity.sick=true;
      }

      //-------------------------------SICKNESS STUFF--------------------------
      if (MainActivity.t==1980)
      {
        MainActivity.sick=true;
        MainActivity.state="idle";
        Utils.notifyUser();
      }
      //sickness due to age
      if (MainActivity.t==MainActivity.ttgsfa)
      {
        //sinon il tombe malade et perd 5 minutes de heart loss period
        //heart loss period ne peut être inférieur à 10 min
        MainActivity.ttgsfa=MainActivity.ttgsfa+72*3600;
        MainActivity.sick=true;
        Utils.notifyUser();
        if (MainActivity.t>5*24*3600)
        {
          MainActivity.hghlp=Math.max(MainActivity.hghlp-300,10*60);
          MainActivity.timeSinceHungryChanged=Math.min(MainActivity.hghlp-10,MainActivity.timeSinceHungryChanged);
          MainActivity.hphlp=Math.max(MainActivity.hphlp-300,10*60);
          MainActivity.timeSinceHappyChanged=Math.min(MainActivity.hphlp-10,MainActivity.timeSinceHungryChanged);
          MainActivity.pp=Math.max(MainActivity.pp-300,10*60);
          MainActivity.tslp=Math.min(MainActivity.pp-10,MainActivity.tslp);
        }
      }

      //sickness due to weight
      if ((MainActivity.t%3600)==0)
      {
        double diff=MainActivity.weight-MainActivity.idealWeight;
        if (Math.random()<(diff/100))
        {
          MainActivity.sick=true;
          Utils.notifyUser();
          MainActivity.hghlp=Math.max(MainActivity.hghlp-300,10*60);
          MainActivity.timeSinceHungryChanged=Math.min(MainActivity.hghlp-10,MainActivity.timeSinceHungryChanged);
          MainActivity.hphlp=Math.max(MainActivity.hphlp-300,10*60);
          MainActivity.timeSinceHappyChanged=Math.min(MainActivity.hphlp-10,MainActivity.timeSinceHungryChanged);
          MainActivity.pp=Math.max(MainActivity.pp-300,10*60);
          MainActivity.tslp=Math.min(MainActivity.pp-10,MainActivity.tslp);
        }
      }
      //Doit on ajouter un care miss ou mourir?
      if ((MainActivity.sick)&&(!MainActivity.sleeping))
      {
        MainActivity.timeSinceSick+=0.5;
        if (MainActivity.timeSinceSick==15*60)
          MainActivity.careMisses+=1;
        else if (MainActivity.timeSinceSick==12*3600)
        {
          MainActivity.state="dead1";
          MainActivity.isAlive=false;
        }
      }
      //---------------------SLEEP STUFF---------------------------
      int currentHour=new Date().getHours();
      int currentMinute=new Date().getMinutes();
      int currentSecond=new Date().getSeconds();
      if (MainActivity.character.equals("Babytchi"))
      {
        if (MainActivity.t==2400)
        {
          MainActivity.sleeping=true;
          MainActivity.state="idle";
          Utils.notifyUser();
        }
        if (MainActivity.t==2700)
        {
          MainActivity.sleeping=false;
          MainActivity.timeSinceNeedsLightsOff=0;
          MainActivity.lightsOn=true;
          MainActivity.age+=1;
        }
      }
      else
      {
        if (MainActivity.t==MainActivity.timeToSleep)
        {
          MainActivity.sleeping=true;
          Utils.notifyUser();
          MainActivity.timeToSleep+=24*3600;
        }
        else if (MainActivity.t==MainActivity.timeToWake)
        {
          MainActivity.sleeping=false;
          MainActivity.lightsOn=true;
          MainActivity.timeToWake+=24*3600;
          MainActivity.age+=1;
        }
      }
      //Doit-on ajouter un care miss?
      if ((MainActivity.sleeping)&&(MainActivity.lightsOn))
      {
        MainActivity.timeSinceNeedsLightsOff+=0.5;
        if (MainActivity.timeSinceNeedsLightsOff==15*60)
        {
          MainActivity.careMisses+=1;
        }
      }
      //--------------------------DISCIPLINE------------------------
      //Avons nous atteint un discipline call?
      if (MainActivity.t==MainActivity.tfdc)
      {
        if (!MainActivity.sleeping)
        {
          MainActivity.needsDiscipline=true;
          MainActivity.timeSinceNeedsDiscipline=0;
          Utils.notifyUser();
        }
        MainActivity.tfdc=MainActivity.tfdc+MainActivity.dcp;
      }
      //Doit on ajouter un care miss?
      if (MainActivity.needsDiscipline)
      {
        MainActivity.timeSinceNeedsDiscipline+=0.5;
        if (MainActivity.timeSinceNeedsDiscipline==15*60)
        {
          MainActivity.careMisses+=1;
          MainActivity.timeSinceNeedsDiscipline=0;
          MainActivity.needsDiscipline=false;
        }
      }
      //------------------------EVOLUTION----------------------------
      if (MainActivity.t==65*60||
          MainActivity.t==2*24*3600||
          MainActivity.t==5*24*3600||
        ((MainActivity.t==8*24*3600)&&(MainActivity.character.equals("Zuccitchi"))))
      {
        Darwin.evolve();
      }
      //----------------------------DEATH-----------------------------
      if (MainActivity.t==25*24*3600||MainActivity.careMisses>=50)
      {
        MainActivity.state="dead1";
        MainActivity.isAlive=false;
      }
    }
    
    if (MainActivity.displayVariables)
      displayVars();
    //On sauvegarde chaque 10s
    if ((Math.round(MainActivity.t))%10==0)
      Utils.saveData(MainActivity.mainActivityContext);
  }
  
  public static void poop()
  {
    //MainActivity.tv.setText("time to poop");
    if (MainActivity.isOpen)
    {
      MainActivity.state="pooping";
      MainActivity.animation_counter=16;
    }
    Utils.notifyUser();
    MainActivity.dirty=true;
    MainActivity.tslp=0;
  }
  
  public static void displayVars()
  {
    MainActivity.tv.setText("Current time: "+MainActivity.t+
                            "\n"+MainActivity.age+"yr, "+MainActivity.discipline+"dspln, "+MainActivity.weight+"oz, "+MainActivity.stomach+"hg, "+MainActivity.happy+"hp"+
                            "\nTSHgC="+MainActivity.timeSinceHungryChanged+"/"+MainActivity.hghlp+
                            "\nTSH="+MainActivity.timeSinceHungry+
                            "\nTSHpC="+MainActivity.timeSinceHappyChanged+"/"+MainActivity.hphlp+
                            "\nTSB="+MainActivity.timeSinceBored+
                            "\nTSLP="+MainActivity.tslp+"/"+MainActivity.pp+
                            "\nTSD="+MainActivity.tsd+
                            "\nTSS="+MainActivity.timeSinceSick+" TTGSFA="+MainActivity.ttgsfa+
                            "\nTSND="+MainActivity.timeSinceNeedsDiscipline+", TFDC="+MainActivity.tfdc+", DCP="+MainActivity.dcp+
                            "\nTSNLO="+MainActivity.timeSinceNeedsLightsOff+
                            "\nTTS="+MainActivity.timeToSleep+
                            "\nTTW="+MainActivity.timeToWake+
                            "\nCMs="+MainActivity.careMisses
                            );
  }
}