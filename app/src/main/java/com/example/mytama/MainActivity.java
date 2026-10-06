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
import java.util.concurrent.TimeUnit;
import android.os.Build;
import android.content.pm.PackageManager;
import android.Manifest;
import android.Manifest.permission.*;
import androidx.work.*;
import androidx.core.app.ActivityCompat;

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
  public static boolean permissionsObtained;
  public static boolean economyMode = false;
  
  private static final int NOTIFICATION_PERMISSION_CODE = 201;
  
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
      tv.setTextColor(R.color.black);
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
      
      vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
      
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        if (checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
          permissionsObtained = false;
          requestNotificationPermission();
        } else {
          permissionsObtained = true;
          setupNotifications();
        }
      } else {
        permissionsObtained = true;
        setupNotifications();
      }
      
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
      if (DataSaverLoader.getSaveFiles().length > 0) state = "tama_select_screen";
      else state = "version_select_screen";
      isOpen = true;
      
      if (permissionsObtained) {
        Intent serviceIntent = new Intent(this, MyForegroundService.class);
        //this.stopService(serviceIntent);
        myHandler.removeCallbacks(MyRunnable.runnable);
        //MyForegroundService.isRunning = false;
        this.startForegroundService(serviceIntent);
        myHandler.postDelayed(MyRunnable.runnable, 40);
      } else {
        myHandler.removeCallbacks(MyRunnable.runnable);
        myHandler.postDelayed(MyRunnable.runnable, 40);
      }
    } catch (Exception e) {
      Printer.print("Error in MainActivity.onResume(): " + e.getMessage());
    }
    isOpen = true;
  }
  
  @Override public void onPause() {
    super.onPause();
    if (!(Tama.name == null)) DataSaverLoader.saveData();
    isOpen = false;
  }
  
  @Override public void onDestroy() {
    super.onDestroy();
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
  
  @Override
  public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
    try {
      super.onRequestPermissionsResult(requestCode, permissions, grantResults);
      if (requestCode == NOTIFICATION_PERMISSION_CODE) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
          permissionsObtained = true;
          setupNotifications();
        } else {
          permissionsObtained = false;
        }
      }
    } catch (Exception e) {
      Printer.print("Error in onRequestPermissionResult: " + e.getMessage());
    }
    
  }
  
  private void requestNotificationPermission() {
    try {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        requestPermissions(new String[]{Manifest.permission.POST_NOTIFICATIONS}, NOTIFICATION_PERMISSION_CODE);
      }
    } catch (Exception e) {
      Printer.print("Error in requestNotificationPermission: " + e.getMessage());
    }  
  }
  
  public void setupNotifications() {
    notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
    Intent intent = new Intent(MainActivity.context, MainActivity.class);  //intent est une variable temporaire
    notificationIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
  }
}
