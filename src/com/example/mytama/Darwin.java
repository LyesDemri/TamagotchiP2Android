package com.example.mytama;

public class Darwin 
{
  static public void evolve()
  {
    int DIFFICULTY=5;
    if (MainActivity.character.equals("Babytchi"))
    {
      MainActivity.character="Tonmarutchi";
      MainActivity.hghlp=45*60;
      MainActivity.hphlp=45*60;
      MainActivity.pp=3*3600;
      MainActivity.dcp=5.5*3600;
      MainActivity.sleepingHour=20;
      MainActivity.wakingHour=9;
      MainActivity.y=0;
      MainActivity.idealWeight=10;
      MainActivity.weight=Math.max(MainActivity.weight,MainActivity.idealWeight);
    }
    else if (MainActivity.character.equals("Tonmarutchi"))
    {
      if (MainActivity.careMisses<3*DIFFICULTY)
      {
        MainActivity.character="Tongaritchi";
        MainActivity.hghlp=50*60;
        MainActivity.hphlp=50*60;
        MainActivity.pp=3*3600;
        MainActivity.tslp=0;
        MainActivity.dcp=6*3600;
        MainActivity.sleepingHour=21;
        MainActivity.wakingHour=9;
        MainActivity.y=0;
        MainActivity.idealWeight=20;
      }
      else
      {
        MainActivity.character="Hashitamatchi";
        MainActivity.hghlp=45*60;
        MainActivity.hphlp=45*60;
        MainActivity.pp=3*3600;
        MainActivity.tslp=0;
        MainActivity.dcp=6*3600;
        MainActivity.sleepingHour=21;
        MainActivity.wakingHour=9;
        MainActivity.y=0;
        MainActivity.idealWeight=20;
      }
    }
    else if (MainActivity.character.equals("Tongaritchi"))
    {
      if (MainActivity.careMisses<DIFFICULTY)
      {
        MainActivity.character="Mimitchi";
        MainActivity.hghlp=60*60;
        MainActivity.hphlp=60*60;
        MainActivity.pp=6*3600;
        MainActivity.tslp=0;
        MainActivity.dcp=24*3600;
        MainActivity.sleepingHour=22;
        MainActivity.wakingHour=8;
        MainActivity.y=0;
        MainActivity.idealWeight=30;
      }
      else if (MainActivity.careMisses<2*DIFFICULTY)
      {
        MainActivity.character="Pochitchi";
        MainActivity.hghlp=60*60;
        MainActivity.hphlp=55*60;
        MainActivity.pp=5*3600;
        MainActivity.tslp=0;
        MainActivity.dcp=18*3600;
        MainActivity.sleepingHour=22;
        MainActivity.wakingHour=8;
        MainActivity.y=0;
        MainActivity.idealWeight=30;
      }
      else if (MainActivity.careMisses<3*DIFFICULTY)
      {
        MainActivity.character="Zuccitchi";
        MainActivity.hghlp=50*60;
        MainActivity.hphlp=50*60;
        MainActivity.pp=5*3600;
        MainActivity.tslp=0;
        MainActivity.dcp=6*3600;
        MainActivity.sleepingHour=23;
        MainActivity.wakingHour=11;
        MainActivity.y=0;
        MainActivity.idealWeight=30;
      }
      else if (MainActivity.careMisses<4*DIFFICULTY)
      {
        MainActivity.character="Hashizotchi";
        MainActivity.hghlp=50*60;
        MainActivity.hphlp=50*60;
        MainActivity.pp=3*3600;
        MainActivity.tslp=0;
        MainActivity.dcp=6*3600;
        MainActivity.sleepingHour=22;
        MainActivity.wakingHour=10;
        MainActivity.y=0;
        MainActivity.idealWeight=30;
      }
      else if (MainActivity.careMisses<5*DIFFICULTY)
      {
        MainActivity.character="Takotchi";
        MainActivity.hghlp=45*60;
        MainActivity.hphlp=45*60;
        MainActivity.pp=3*3600;
        MainActivity.tslp=0;
        MainActivity.dcp=5.5*3600;
        MainActivity.sleepingHour=21;
        MainActivity.wakingHour=10;
        MainActivity.y=0;
        MainActivity.idealWeight=30;
      }
      else
      {
        MainActivity.character="Kusatchi";
        MainActivity.hghlp=40*60;
        MainActivity.hphlp=40*60;
        MainActivity.pp=(int)(1.5*3600);
        MainActivity.tslp=0;
        MainActivity.dcp=5.5*3600;
        MainActivity.sleepingHour=20;
        MainActivity.wakingHour=10;
        MainActivity.y=0;
        MainActivity.idealWeight=20;
      }
    }
    else if (MainActivity.character.equals("Hashitamatchi"))
    {
      if (MainActivity.careMisses<4*DIFFICULTY)
      {
        MainActivity.character="Hashizoutchi";
        MainActivity.hghlp=50*60;
        MainActivity.hphlp=50*60;
        MainActivity.pp=3*3600;
        MainActivity.tslp=0;
        MainActivity.dcp=6*3600;
        MainActivity.sleepingHour=22;
        MainActivity.wakingHour=10;
        MainActivity.y=0;
        MainActivity.idealWeight=30;
      }
      else if (MainActivity.careMisses<5*DIFFICULTY)
      {
        MainActivity.character="Takotchi";
        MainActivity.hghlp=45*60;
        MainActivity.hphlp=45*60;
        MainActivity.pp=3*3600;
        MainActivity.tslp=0;
        MainActivity.dcp=5.5*3600;
        MainActivity.sleepingHour=21;
        MainActivity.wakingHour=10;
        MainActivity.y=0;
        MainActivity.idealWeight=30;
      }
      else
      {
        MainActivity.character="Kusatchi";
        MainActivity.hghlp=40*60;
        MainActivity.hphlp=40*60;
        MainActivity.pp=(int)(1.5*3600);
        MainActivity.tslp=0;
        MainActivity.dcp=5.5*3600;
        MainActivity.sleepingHour=20;
        MainActivity.wakingHour=10;
        MainActivity.y=0;
        MainActivity.idealWeight=20;
      }
    }
    else if (MainActivity.character.equals("Zuccitchi"))
    {
      if (MainActivity.careMisses==3*DIFFICULTY)
      {
        MainActivity.character="Zatchi";
        MainActivity.hghlp=55*60;
        MainActivity.hphlp=55*60;
        MainActivity.pp=5*3600;
        MainActivity.tslp=0;
        MainActivity.dcp=1*3600;
        MainActivity.sleepingHour=23;
        MainActivity.wakingHour=8;
        MainActivity.y=0;
        MainActivity.idealWeight=20;
      }
    }

    Utils.computeSleepWakeTimes(MainActivity.sleepingHour,MainActivity.wakingHour);
    MainActivity.timeSinceHungryChanged=0;
    MainActivity.timeSinceHappyChanged=0;
    MainActivity.tslp=0;
    MainActivity.tfdc=MainActivity.t+MainActivity.dcp;
    MainActivity.weight=Math.max(MainActivity.weight,MainActivity.idealWeight);
    MainActivity.graphics.loadCharacterGraphics(MainActivity.mainActivityContext,MainActivity.character);
    MainActivity.x=16-MainActivity.W/2;
    MainActivity.evolveSound.start();
    Utils.notifyUser();
  }
}