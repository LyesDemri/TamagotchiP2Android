package com.example.mytama;

import com.example.mytama.MainActivity;
import java.util.Date;

public class TimeWizard {
  public static void computeSleepWakeTimes(int sleepingHour, int wakingHour) {
    long t1970birth = Tama.t1970birth;
    //on calcule l'instant actuel dans le referentiel 1970
    long t1970now=new Date().getTime();
    //on passe en 1970 pour savoir quelle date on est
    Date sleepingDate = new Date(t1970now);
    //on regle l'heure sur aujourd'hui a sleepingHour:00
    sleepingDate.setHours(sleepingHour);
    sleepingDate.setMinutes(0);
    sleepingDate.setSeconds(0);
    //on calcule l'instant d'endormissement d'aujourd'hui
    double timeToSleep = (double)(sleepingDate.getTime() - t1970now + Tama.t*1000)/1000;
    //si on etait apres sleepingHour:00 timeToSleep sera negatif
    if (timeToSleep < Tama.t)
      timeToSleep += 24*3600;
    Tama.timeToSleep = Math.round(timeToSleep);

    //on calcule l'instant de reveil:
    //c'est aujourd'hui a wakingHour
    Date wakingDate = new Date((long)(t1970now));
    wakingDate.setHours(wakingHour);
    wakingDate.setMinutes(0);
    wakingDate.setSeconds(0);
    double timeToWake = (double)(wakingDate.getTime()-t1970now + Tama.t*1000)/1000;
    //si on etait apres 9h timeToWake sera negatif
    if (timeToWake < Tama.t)
      timeToWake+=24*3600;
    Tama.timeToWake = Math.round(timeToWake);

    if ((timeToWake<timeToSleep) && (!Tama.character.equals("Babytchi")))
      Tama.sleeping=true;
  }
  
  public static long getTime(){
    return new Date().getTime();
  }
}