package com.example.mytama;

//import com.example.mytama.MainActivity;
import java.util.Date;

public class TimeWizard {
  public static void computeSleepWakeTimes(int sleepingHour, int wakingHour) {
    long t1970birth = Tama.t1970birth;
    //on calcule l'instant actuel dans le referentiel 1970
    long t1970now = getTamagotchiTime().getTime();
    Printer.log("t1970now=" + t1970now);
    //on passe en 1970 pour savoir quelle date on est
    Date sleepingDate = new Date(t1970now);
    //on regle l'heure sur aujourd'hui a sleepingHour:00
    sleepingDate.setHours(sleepingHour);
    sleepingDate.setMinutes(0);
    sleepingDate.setSeconds(0);
    Printer.log("sleepingDate = " + sleepingDate);
    //on calcule l'instant d'endormissement d'aujourd'hui
    double timeToSleep = (double)(sleepingDate.getTime() - t1970now + Tama.t*1000)/1000;
    //si on etait apres sleepingHour:00 timeToSleep sera negatif
    if (timeToSleep < Tama.t)
      timeToSleep += 24*3600;
    Tama.timeToSleep = Math.round(timeToSleep);
    Printer.log("sleepingDate = " + sleepingDate);
    Printer.log("sleepingDate.getTime() = " + sleepingDate.getTime());
    Printer.log("t1970birth = " + t1970birth);
    Printer.log("Tama.t*1000 = " + Tama.t*1000);
    Printer.log("timeToSleep=" + timeToSleep);
    
    //on calcule l'instant de reveil:
    //c'est aujourd'hui a wakingHour
    Date wakingDate = new Date(t1970now);
    wakingDate.setHours(wakingHour);
    wakingDate.setMinutes(0);
    wakingDate.setSeconds(0);
    Printer.log("wakingDate =" + wakingDate);

    double timeToWake = (double)(wakingDate.getTime() - t1970now + Tama.t*1000)/1000;
    //si on etait apres 9h timeToWake sera negatif
    if (timeToWake < Tama.t)
      timeToWake += 24*3600;
    Tama.timeToWake = Math.round(timeToWake);
    Printer.log("timeToWake =" + timeToWake);

    if ((timeToWake<timeToSleep) && (!Tama.character.equals("babytchi"))){
      Tama.sleeping = true;
      Printer.log("timeToWake < timeToSleep; tama falls asleep now");
    }
    
    Printer.log("Confirmation: Sleeping time = "+ getSleepingTime());
    Printer.log("Waking time = " + getWakingTime());
  }
  
  public static long getTime(){
    return new Date().getTime();
  }
  
  public static Date getTamagotchiTime(){
    long currentTime = Tama.t1970birth/1000 + (long)Tama.t;
    return new Date(currentTime*1000);
  }
  
  public static String getSleepingTime() {
    long sleepingTime = (long)Tama.timeToSleep + Tama.t1970birth/1000;
    return (new Date(sleepingTime*1000)).toString();
  }
  
  public static String getWakingTime() {
    long wakingTime = (long)Tama.timeToWake + Tama.t1970birth/1000;
    return (new Date(wakingTime*1000)).toString();
  }
  
  public static String getBirthDate() {
    return (new Date(Tama.t1970birth)).toString();
  }
}