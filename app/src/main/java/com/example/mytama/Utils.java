package com.example.mytama;

import java.lang.Math;
import android.app.Notification;
import android.app.NotificationChannel;


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
    P2Game.secretNumber=(int)(Math.random()*10);
    P2Game.givenNumber=(int)(Math.random()*10);
    while (P2Game.givenNumber==P2Game.secretNumber) {
      P2Game.givenNumber = (int)(Math.random()*10);
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
  
  public static void notifyUser() {
    if (!MainActivity.isOpen) {
      Notification.Builder notiBuilder = new Notification.Builder(MainActivity.context, NotificationChannel.DEFAULT_CHANNEL_ID);
      notiBuilder.setContentTitle("Tamagotchi");
      notiBuilder.setContentText("Tamagotchi is calling!");
      notiBuilder.setSmallIcon(R.drawable.face_icon);
      notiBuilder.setAutoCancel(true);
      notiBuilder.setDefaults(Notification.DEFAULT_ALL);
      notiBuilder.setPriority(Notification.PRIORITY_MAX);
      notiBuilder.setOnlyAlertOnce(true);
      notiBuilder.setContentIntent(MainActivity.notificationIntent);
      Notification noti = notiBuilder.build();
      MainActivity.notificationManager.notify(0, noti);
      /*
      Printer.print("notiBuilder = " + notiBuilder, true);
      Printer.append("noti = " + noti, true);
      Printer.append("notificationManager = " + MainActivity.notificationManager, true);
      */
      Sounds.playSound("call");
    }
  }
}