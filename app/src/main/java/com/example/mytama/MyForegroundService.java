package com.example.mytama;

import android.app.Service;
import android.app.Notification;
import android.app.NotificationChannel;
import androidx.core.app.NotificationCompat;
import android.app.NotificationManager;
import android.content.pm.ServiceInfo;
import androidx.core.app.ServiceCompat;
import android.os.IBinder;
import android.content.Intent;

public class MyForegroundService extends Service {
    public static boolean isRunning = false;
    
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            if (isRunning) return 0;
            //super.onStartCommand(intent, flags, startId);
            NotificationChannel channel = new NotificationChannel("tamagotchi_id", "Tamagotchi Alerts", NotificationManager.IMPORTANCE_HIGH);
            MainActivity.notificationManager.createNotificationChannel(channel);
            Notification.Builder notiBuilder = new Notification.Builder(MainActivity.context, "tamagotchi_id");
            notiBuilder.setContentTitle("MyTama");
            notiBuilder.setContentText("MyTama service is running");
            notiBuilder.setSmallIcon(R.drawable.face_icon);
            notiBuilder.setAutoCancel(true);
            //notiBuilder.setDefaults(Notification.DEFAULT_ALL);
            notiBuilder.setPriority(Notification.PRIORITY_MAX);
            notiBuilder.setOnlyAlertOnce(true);
            notiBuilder.setContentIntent(MainActivity.notificationIntent);
            Notification notification = notiBuilder.build();
            int type = ServiceInfo.FOREGROUND_SERVICE_TYPE_DATA_SYNC;
            ServiceCompat.startForeground(this, 100, notification, type);
            MainActivity.myHandler.removeCallbacks(MyRunnable.runnable);
            MainActivity.myHandler.postDelayed(MyRunnable.runnable, 40);
            isRunning = true;
        } catch (Exception e) {
            Printer.append("Error in MyForegroundService.onStartCommand(): " + e.getMessage());
        }
        return START_STICKY;
    }
    
    public void onDestroy(){
        Utils.notifyUser("Service has been destroyed (and restarted)", "");
        isRunning = false;
        Intent serviceIntent = new Intent(this, MyForegroundService.class);
        MainActivity.context.startForegroundService(serviceIntent);
    }
    
    public IBinder onBind(Intent i){
        return null;
    }
}