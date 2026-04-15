package com.example.mytama;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.Context;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {
  @Override public void onReceive(Context context, Intent intent) {
    if (!MainActivity.isOpen) {
      //On charge les données
      DataSaverLoader.loadData();
      for (int i = 0; i < P2Tama.timeSinceLeft*2; i++) {
        Updater.update();
      }
      Toast.makeText(context, "Tamagotchi has been updated", Toast.LENGTH_LONG).show();
      DataSaverLoader.saveData();
    }
  }
}