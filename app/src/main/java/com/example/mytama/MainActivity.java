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

public class MainActivity extends Activity {
  static public TextView tv;
  static public int even = 0;
  static public Graphics graphics;
  static public MyRunnable myRunnable;
  static public AlarmManager alarmMgr;
  static public NotificationManager notificationManager;
  static public PendingIntent alarmIntent;
  public static PendingIntent notificationIntent;
  static public Context context;
  static public boolean displayVariables = false;
  static public Handler myHandler;
  static public String[] icon_list;
  static public String[] menu_list;
  static public int icon_number;
  static public int menu_index;
  static public int food_index = 0;
  static public boolean isOpen = true;
  static public boolean catchingUp = false;
  static public String state;
  static public String oldState;
  static public Vibrator vibrator;

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
    tv=(TextView) findViewById(R.id.TV);
    tv.setBackgroundResource(R.color.white);
    
    //Elements du menu
    icon_list = new String[]{"", "Food","Lights","Game","Medicine","Toilet","Status","Discipline","Menu"};
    menu_list = new String[]{"", "Reset", "Create new tama","Switch tama","Display Variables","Skip 5 minutes"};
    
    //Variable representant l'element actuel du menu
    icon_number = 0;
    menu_index = 0;

    graphics = new Graphics(this);
    myRunnable = new MyRunnable(this);

    Screen.initScreen(this);
    
    layout.addView(Screen.screen,0);
    myHandler = new Handler();

    //code pour l'alarme
    alarmMgr = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
    Intent intent = new Intent("UPDATETAMAGOTCHI");
    alarmIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
    
    //code pour les notifications
    notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
    intent = new Intent(this, MainActivity.class);  //intent est une variable temporaire
    notificationIntent = PendingIntent.getActivity(this, 0, intent, 0);
    
    vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
    MainActivity.isOpen = true;
    
    Sounds.loadSounds(this);
    Animations.loadAnimations();
    } catch (Exception e) {
      Printer.print("Error in MainActivity.onCreate()");
      Printer.append(e);
    }
  }

  @Override public void onPause() {
    super.onPause();
    myHandler.removeCallbacks(myRunnable.runnable);
    if (Tama.isAlive) {
      DataSaverLoader.saveData();
      alarmMgr.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                                   SystemClock.elapsedRealtime() + 60*1000,
                                   60*1000,
                                   alarmIntent);
    }
    isOpen = false;
  }

  @Override public void onResume() {
    super.onResume();
    notificationManager.cancelAll();
    if (DataSaverLoader.getSaveFiles().length > 0)
      state = "tama_select_screen";
    else
      state = "reset_screen";
    
    isOpen = true;
    myHandler.postDelayed(myRunnable.runnable,40);
    alarmMgr.cancel(alarmIntent);
  }
}