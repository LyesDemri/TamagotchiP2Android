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
    MainActivity.alarmMgr.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                                       SystemClock.elapsedRealtime() + 600*1000,
                                       600*1000,
                                       MainActivity.alarmIntent);
  }
}