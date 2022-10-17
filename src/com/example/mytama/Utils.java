package com.example.mytama;

import java.lang.Math;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import android.content.Context;
import java.util.Date;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.graphics.Color;
import android.widget.Toast;

public class Utils 
{
  public static int[] numDecomposition(int n){
    int tens;
    int units;
    tens=(int)Math.floor(Double.valueOf(n)/10);
    units=n-tens*10;
    int[] results=new int[2];
    results[0]=tens;
    results[1]=units;
    return results;
  }
  
  public static void generateGameNumbers(){
    MainActivity.secretNumber=(int)(Math.random()*10);
    MainActivity.givenNumber=(int)(Math.random()*10);
    while (MainActivity.givenNumber==MainActivity.secretNumber) {
      MainActivity.givenNumber=(int)(Math.random()*10);
    }
  }

  public static void saveData(Context context) {
    try {
      MainActivity.fos=new FileOutputStream(context.getFilesDir()+"/save.txt");
      MainActivity.dos=new DataOutputStream(MainActivity.fos);
      MainActivity.dos.writeDouble(MainActivity.t);
      MainActivity.dos.writeInt(MainActivity.stomach);
      MainActivity.dos.writeInt(MainActivity.happy);
      MainActivity.dos.writeDouble(MainActivity.timeSinceHungryChanged);
      MainActivity.dos.writeDouble(MainActivity.timeSinceHappyChanged);
      MainActivity.dos.writeDouble(MainActivity.timeSinceHungry);
      MainActivity.dos.writeDouble(MainActivity.timeSinceBored);
      MainActivity.dos.writeDouble(MainActivity.tslp);
      MainActivity.dos.writeDouble(MainActivity.tsd);
      MainActivity.dos.writeInt(MainActivity.pp);
      MainActivity.dos.writeInt(MainActivity.weight);
      MainActivity.dos.writeInt(MainActivity.idealWeight);
      MainActivity.dos.writeInt(MainActivity.age);
      MainActivity.dos.writeInt(MainActivity.x);
      MainActivity.dos.writeInt(MainActivity.y);
      MainActivity.dos.writeInt(MainActivity.hghlp);
      MainActivity.dos.writeInt(MainActivity.hphlp);
      MainActivity.dos.writeInt(MainActivity.sleepingHour);
      MainActivity.dos.writeInt(MainActivity.wakingHour);
      MainActivity.dos.writeBoolean(MainActivity.sleeping);
      MainActivity.dos.writeBoolean(MainActivity.lightsOn);
      MainActivity.dos.writeBoolean(MainActivity.dirty);
      MainActivity.dos.writeBoolean(MainActivity.isAlive);
      MainActivity.dos.writeDouble(MainActivity.tfdc);
      MainActivity.dos.writeBoolean(MainActivity.needsDiscipline);
      MainActivity.dos.writeDouble(MainActivity.timeSinceNeedsDiscipline);
      MainActivity.dos.writeBoolean(MainActivity.isCalling);
      MainActivity.dos.writeBoolean(MainActivity.sick);
      MainActivity.dos.writeDouble(MainActivity.timeSinceSick);
      MainActivity.dos.writeDouble(MainActivity.timeSinceNeedsLightsOff);
      MainActivity.dos.writeInt(MainActivity.discipline);
      MainActivity.dos.writeDouble(MainActivity.timeToSleep);
      MainActivity.dos.writeDouble(MainActivity.timeToWake);
      MainActivity.dos.writeLong(MainActivity.t1970birth);
      MainActivity.dos.writeInt(MainActivity.careMisses);
      MainActivity.dos.writeInt(MainActivity.cakesEaten);
      MainActivity.dos.writeDouble(MainActivity.dcp);
      MainActivity.dos.writeDouble(MainActivity.ttgsfa);

      long timeUponClosing=new Date().getTime();
      MainActivity.dos.writeLong(timeUponClosing);

      MainActivity.dos.writeUTF(MainActivity.character);
      MainActivity.dos.writeUTF(MainActivity.state);
      
      MainActivity.dos.close();
      MainActivity.fos.close();
    }
    catch (Exception e) {
      MainActivity.tv.setText(e.getMessage());
    }
  }

  public static void loadData(Context context){
    try {
      MainActivity.fis=new FileInputStream(context.getFilesDir()+"/save.txt");
      MainActivity.dis=new DataInputStream(MainActivity.fis);
      MainActivity.t=MainActivity.dis.readDouble();
      MainActivity.stomach=MainActivity.dis.readInt();
      MainActivity.happy=MainActivity.dis.readInt();
      MainActivity.timeSinceHungryChanged=MainActivity.dis.readDouble();
      MainActivity.timeSinceHappyChanged=MainActivity.dis.readDouble();
      MainActivity.timeSinceHungry=MainActivity.dis.readDouble();
      MainActivity.timeSinceBored=MainActivity.dis.readDouble();
      MainActivity.tslp=MainActivity.dis.readDouble();
      MainActivity.tsd=MainActivity.dis.readDouble();
      MainActivity.pp=MainActivity.dis.readInt();
      MainActivity.weight=MainActivity.dis.readInt();
      MainActivity.idealWeight=MainActivity.dis.readInt();
      MainActivity.age=MainActivity.dis.readInt();
      MainActivity.x=MainActivity.dis.readInt();
      MainActivity.y=MainActivity.dis.readInt();
      MainActivity.hghlp=MainActivity.dis.readInt();
      MainActivity.hphlp=MainActivity.dis.readInt();
      MainActivity.sleepingHour=MainActivity.dis.readInt();
      MainActivity.wakingHour=MainActivity.dis.readInt();
      MainActivity.sleeping=MainActivity.dis.readBoolean();
      MainActivity.lightsOn=MainActivity.dis.readBoolean();
      MainActivity.dirty=MainActivity.dis.readBoolean();
      MainActivity.isAlive=MainActivity.dis.readBoolean();
      MainActivity.tfdc=MainActivity.dis.readDouble();
      MainActivity.needsDiscipline=MainActivity.dis.readBoolean();
      MainActivity.timeSinceNeedsDiscipline=MainActivity.dis.readDouble();
      MainActivity.isCalling=MainActivity.dis.readBoolean();
      MainActivity.sick=MainActivity.dis.readBoolean();
      MainActivity.timeSinceSick=MainActivity.dis.readDouble(); 
      MainActivity.timeSinceNeedsLightsOff=MainActivity.dis.readDouble();
      MainActivity.discipline=MainActivity.dis.readInt();
      MainActivity.timeToSleep=MainActivity.dis.readDouble();
      MainActivity.timeToWake=MainActivity.dis.readDouble();
      MainActivity.t1970birth=MainActivity.dis.readLong();
      MainActivity.careMisses=MainActivity.dis.readInt();
      MainActivity.cakesEaten=MainActivity.dis.readInt();
      MainActivity.dcp=MainActivity.dis.readDouble();
      MainActivity.ttgsfa=MainActivity.dis.readDouble();
      
      long timeUponClosing=MainActivity.dis.readLong();

      MainActivity.character=MainActivity.dis.readUTF();
      MainActivity.state=MainActivity.dis.readUTF();
      
      //Calcul du temps passé (nécessaire pour la suite)
      long currentTime=new Date().getTime();
      MainActivity.timeSinceLeft=(double)((currentTime-timeUponClosing)/1000);
      MainActivity.timeSinceLeft=Math.round(MainActivity.timeSinceLeft);

      MainActivity.dis.close();
      MainActivity.fis.close();
      
      MainActivity.graphics.loadCharacterGraphics(MainActivity.mainActivityContext,MainActivity.character);
    }
    catch (Exception e){
      Utils.reset();
      //MainActivity.tv.setText(e.getMessage());
      //Toast.makeText(context, "Load failed: "+e.getMessage(), Toast.LENGTH_LONG).show();
    }
  }
  
  public static int minValueIndex(Double[] array) {
    double minValue=array[0];
    int length=array.length;
    int index=0;
    for (int i = 0;i<length;i++) {
      if (minValue>array[i]) {
        minValue=array[i];
        index=i;
      }
    }
    return index;
  }

  public static void reset() {
    //MainActivity.tv.setText("You've just reset the Tamagotchi");
    MainActivity.t=0;
    MainActivity.character="Egg";
    MainActivity.state="Egg";
    MainActivity.stomach=0;
    MainActivity.timeSinceHungryChanged=0;
    MainActivity.timeSinceHungry=0;
    MainActivity.hghlp=180;
    MainActivity.hphlp=240;
    MainActivity.happy=0;
    MainActivity.timeSinceHappyChanged=0;
    MainActivity.timeSinceBored=0;
    MainActivity.tsd=0;
    MainActivity.tslp=0;
    //on determine l'instant actuel depuis 1970
    long t1970birth=new Date().getTime();
    MainActivity.t1970birth=t1970birth;
    computeSleepWakeTimes(20,9);
    MainActivity.pp=0;
    MainActivity.careMisses=0;
    MainActivity.sleeping=false;
    MainActivity.isAlive=true;
    MainActivity.lightsOn=true;
    MainActivity.dirty=false;
    MainActivity.sick=false;
    MainActivity.needsDiscipline=false;
    MainActivity.discipline=0;
    MainActivity.timeSinceNeedsLightsOff=0;
    MainActivity.timeSinceSick=0;
    MainActivity.menu_index=0;
    MainActivity.weight=5;
    MainActivity.idealWeight=5;
    MainActivity.age=0;
    MainActivity.x=8;
    MainActivity.y=0;
    MainActivity.tfdc=0;
    MainActivity.cakesEaten=0;
    MainActivity.dcp=5.5*3600;
    MainActivity.graphics.loadCharacterGraphics(MainActivity.mainActivityContext,"Babytchi");
    MainActivity.ttgsfa=42*3600;
    MainActivity.resetSound.start();
  }
  
  public static void notifyUser() {
    if (!MainActivity.isOpen) {
      Notification.Builder notiBuilder = new Notification.Builder(MainActivity.mainActivityContext);
      notiBuilder.setContentTitle("Tamagotchi");
      notiBuilder.setContentText("Tamagotchi is calling!");
      notiBuilder.setSmallIcon(R.drawable.face_icon);
      notiBuilder.setAutoCancel(true);
      notiBuilder.setDefaults(Notification.DEFAULT_ALL);
      notiBuilder.setPriority(Notification.PRIORITY_MAX);
      notiBuilder.setOnlyAlertOnce(true);
      notiBuilder.setContentIntent(MainActivity.notificationIntent);
      Notification noti = notiBuilder.build();
      MainActivity.notificationManager.notify(0,noti);
      MainActivity.callSound.start();
    }
  }

  public static void computeSleepWakeTimes(int sleepingHour, int wakingHour) {
    long t1970birth=MainActivity.t1970birth;
    //on calcule l'instant actuel dans le referentiel 1970
    long t1970now=new Date().getTime();
    //on passe en 1970 pour savoir quelle date on est
    Date sleepingDate = new Date(t1970now);
    //on regle l'heure sur aujourd'hui a sleepingHour:00
    sleepingDate.setHours(sleepingHour);
    sleepingDate.setMinutes(0);
    sleepingDate.setSeconds(0);
    //on calcule l'instant d'endormissement d'aujourd'hui
    double timeToSleep = (double)(sleepingDate.getTime()-t1970now+MainActivity.t*1000)/1000;
    //si on etait apres sleepingHour:00 timeToSleep sera negatif
    if (timeToSleep<MainActivity.t)
      timeToSleep+=24*3600;
    MainActivity.timeToSleep=Math.round(timeToSleep);

    //on calcule l'instant de reveil:
    //c'est aujourd'hui a wakingHour
    Date wakingDate = new Date((long)(t1970now));
    wakingDate.setHours(wakingHour);
    wakingDate.setMinutes(0);
    wakingDate.setSeconds(0);
    double timeToWake = (double)(wakingDate.getTime()-t1970now+MainActivity.t*1000)/1000;
    //si on etait apres 9h timeToWake sera negatif
    if (timeToWake<MainActivity.t)
      timeToWake+=24*3600;
    MainActivity.timeToWake=Math.round(timeToWake);

    if ((timeToWake<timeToSleep) && (!MainActivity.character.equals("Babytchi")))
      MainActivity.sleeping=true;
  }
}