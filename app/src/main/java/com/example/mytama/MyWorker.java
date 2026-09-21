package com.example.mytama;

import androidx.work.Worker;
import androidx.work.WorkerParameters;
import android.content.Context;

public class MyWorker extends Worker{
  public MyWorker(
       Context context,
       WorkerParameters params) {
       super(context, params);
   }
  
   public Result doWork() {
     String[] files = DataSaverLoader.getSaveFiles();
     Utils.notifyUser(TimeWizard.getTime() + "Worker is updating the tamas", "");
     for (int i = 0; i < files.length; i++) {
       Tama.name = files[i];
       if (!MainActivity.isOpen || true) {
         DataSaverLoader.loadData();
         Tama.updatesWhileAbsent++;
         DataSaverLoader.saveData();
       }
    }
    MainActivity.isOpen = false;
     
     return Result.success();
   }
}