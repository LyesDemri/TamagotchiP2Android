package com.example.mytama;

import java.lang.Math;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class Utils 
{
  public static int counter = 0;
  public static int[] numDecomposition(int n){
    int tens;
    int units;
    tens = (int)Math.floor(Double.valueOf(n)/10);
    units = n - tens*10;
    int[] results = new int[2];
    results[0] = tens;
    results[1] = units;
    return results;
  }
  
  public static void generateGameNumbers(){
    P2Game.secretNumber = (int)(Math.random()*10);
    P2Game.givenNumber = (int)(Math.random()*10);
    while (P2Game.givenNumber == P2Game.secretNumber) {
      P2Game.givenNumber = (int)(Math.random()*10);
    }
  }
  
  public static int minValueIndex(Double[] array) {
    double minValue = array[0];
    int length = array.length;
    int index = 0;
    for (int i = 0; i < length; i++) {
      if (minValue > array[i]) {
        minValue = array[i];
        index = i;
      }
    }
    return index;
  }
  
  public static void notifyUser(String msg, String sound) {
    if (!MainActivity.isOpen||true) {
      NotificationChannel channel = new NotificationChannel("tamagotchi_id", "Tamagotchi Alerts", NotificationManager.IMPORTANCE_HIGH);
      MainActivity.notificationManager.createNotificationChannel(channel);
      Notification.Builder notiBuilder = new Notification.Builder(MainActivity.context, "tamagotchi_id");
      notiBuilder.setContentTitle("MyTama");
      notiBuilder.setContentText(msg);
      notiBuilder.setSmallIcon(R.drawable.face_icon);
      notiBuilder.setAutoCancel(true);
      //notiBuilder.setDefaults(Notification.DEFAULT_ALL);
      notiBuilder.setPriority(Notification.PRIORITY_MAX);
      notiBuilder.setOnlyAlertOnce(true);
      notiBuilder.setContentIntent(MainActivity.notificationIntent);
      Notification noti = notiBuilder.build();
      MainActivity.notificationManager.notify(counter, noti);
      counter++;
      Sounds.playSound(sound);
      Tama.notificationsSent++;
    }
  }
  
  public static int sum(int[] array){
    int sum = 0;
    for (int i = 0; i < array.length; i++){
      sum += array[i];
    }
    return sum;
  }
  
  public static int[] randperm(int length) {
    List<Integer> list = new ArrayList<Integer>();
    for (int i = 0; i < length; i++)
      list.add(i);
    Collections.shuffle(list);
    int[] array = new int[length];
    for (int i = 0; i < length; i++)
      array[i] = list.get(i);
    return array;
  }
}