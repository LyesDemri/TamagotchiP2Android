package com.example.mytama;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.Context;
import android.widget.Toast;
import android.app.AlarmManager;
import android.os.SystemClock;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;

public class AlarmReceiver extends BroadcastReceiver {
  @Override public void onReceive(Context context, Intent intent)
  {
    if (!MainActivity.isOpen)
    {
      //On charge les données
      Utils.loadData(MainActivity.mainActivityContext);
      for (int i=0; i<MainActivity.timeSinceLeft*2; i++)
      {
        Updater.update();
      }
      Toast.makeText(context, "Tamagotchi has been updated", Toast.LENGTH_LONG).show();
      Utils.saveData(MainActivity.mainActivityContext);
    }
  }
}