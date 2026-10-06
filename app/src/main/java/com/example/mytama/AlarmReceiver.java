package com.example.mytama;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.Context;
import android.widget.Toast;
import android.app.AlarmManager;
import android.content.Intent;
import android.os.SystemClock;

public class AlarmReceiver extends BroadcastReceiver {
  @Override public void onReceive(Context context, Intent intent) {
    Updater.updateAllTamas();
    MainActivity.isOpen = false;
    Double d = new Double(Updater.timeForNextCall);
    int duration = d.intValue();
    Utils.notifyUser("AlarmReceiver: Calling you back in " + duration + "s","");
    MainActivity.alarmMgr.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + duration*1000, MainActivity.alarmIntent);
  }
}