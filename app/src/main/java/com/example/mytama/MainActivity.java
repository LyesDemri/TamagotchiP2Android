package com.example.mytama;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.os.Handler;
import android.content.Context;
import android.app.NotificationManager;
import android.os.Vibrator;
import android.view.WindowManager;
import android.view.View;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkRequest;
import java.util.concurrent.TimeUnit;
import androidx.work.WorkManager;
import androidx.work.*;

public class MainActivity extends Activity {
  static public TextView tv;
  static public int even = 0;
  static public MyRunnable myRunnable;
  static public AlarmManager alarmMgr;
  static public NotificationManager notificationManager;
  static public PendingIntent alarmIntent;
  public static PendingIntent notificationIntent;
  static public Context context;
  static public boolean displayVariables = false;
  static public int debugMode = 0;
  static public Handler myHandler;
  static public String[] icon_list;
  static public String[] menu_list;
  static public int icon_number = 0, menu_index = 0, food_index = 0, debugCounter = 0;
  static public String version = "P2";
  static public boolean isOpen = true;
  static public boolean catchingUp = false;
  static public String state;
  static public String oldState;
  static public Vibrator vibrator;
  public static WorkRequest workRequest;
  public static MyForegroundService mfs;
  @Override
  public void onCreate(Bundle savedInstanceState) {
    try {
      // on appelle le onCreate du parent
      super.onCreate(savedInstanceState);
      
      //On récupère le contexte
      context = this;
      
      //On inflate le layout XML
      setContentView(R.layout.activity_main);
      getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                           WindowManager.LayoutParams.FLAG_FULLSCREEN);
      
      // On récupère une instance du layout principal dans une variable
      LinearLayout layout = findViewById(R.id.tama);
    
      //On récupère une instance du TextView du layout
      tv = (TextView)findViewById(R.id.TV);
      tv.setBackgroundResource(R.color.white);
      //Elements du menu
      menu_list = new String[]{"", "Reset", "Create new tama","Switch tama","Display Variables","Skip 1 minute", "Skip 5 minutes","Skip 1 hour"};
      MyRunnable.initialize();
      
      Screen.initScreen(this);
      
      layout.addView(Screen.screen,0);
      myHandler = new Handler();
      
      //code pour l'alarme
      alarmMgr = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
      Intent intent = new Intent(this, AlarmReceiver.class);
      alarmIntent = PendingIntent.getBroadcast(this, 0, intent, (PendingIntent.FLAG_IMMUTABLE));
      workRequest = new PeriodicWorkRequest.Builder(MyWorker.class, 15, TimeUnit.MINUTES, 7, TimeUnit.MINUTES).build();
      
      //code pour les notifications
      notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
      intent = new Intent(this, MainActivity.class);  //intent est une variable temporaire
      notificationIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
      
      vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
      MainActivity.isOpen = true;
      if (checkSelfPermission("android.permission.POST_NOTIFICATIONS") != android.content.pm.PackageManager.PERMISSION_GRANTED) {
        requestPermissions(new String[]{"android.permission.POST_NOTIFICATIONS"}, 101);
      }
      Intent serviceIntent = new Intent(this, MyForegroundService.class);
      this.startForegroundService(serviceIntent);
      
      Animations.loadAnimations();
    } catch (Exception e) {
      Printer.append("Error in MainActivity.onCreate()", true);
      Printer.append("\n" + e.getMessage());
    }
  }
  
  public void onClickA(View v) {ButtonA.handle();}
  public void onClickB(View v) {ButtonB.handle();}
  public void onClickC(View v) {ButtonC.handle();}
  
  @Override public void onResume() {
    try {
      super.onResume();
      if (DataSaverLoader.getSaveFiles().length > 0)
      state = "tama_select_screen";
      else {
        state = "version_select_screen";
      }
      isOpen = true;
      WorkManager.getInstance(this).cancelAllWork();
    } catch (Exception e) {
      Printer.print("Error in MainActivity.onResume(): " + e.getMessage());
    }
  }
  
  @Override public void onPause() {
    super.onPause();
    //if (Tama.isAlive) {
      DataSaverLoader.saveData();
      /*
      alarmMgr.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                                   SystemClock.elapsedRealtime() + 600*1000,
                                   600*1000,
                                   alarmIntent);
      */
    //}
    isOpen = false;
  }
  
  @Override public void onDestroy() {
    super.onDestroy();
    Utils.notifyUser("MyTama process was killed. Click here to relaunch it", "");
  }
  
  public static void fillIconList() {
    try {
      if (MainActivity.version.equals("P2")){
        MainActivity.icon_list = new String[]{"", "Food","Lights","Game","Medicine","Toilet","Status","Discipline","Menu"};
      } else {
        MainActivity.icon_list = new String[]{"", "Status","Food","Game","Present","Super Kuchipatchi","Advent Calendar","Tama TV","Menu"};
      }
    } catch (Exception e) {
      Printer.print("Error filling icon list: " + e.getMessage());
    }
  }
}
