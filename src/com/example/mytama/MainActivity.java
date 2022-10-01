package com.example.mytama;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.TextView;
import android.widget.ImageView;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.widget.LinearLayout;
import android.os.Handler;
import android.view.View;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.util.*;
import android.content.Context;
import android.app.NotificationManager;
import android.os.Vibrator;
import android.view.SurfaceHolder;
import android.graphics.PixelFormat;
import android.view.WindowManager;
import android.media.MediaPlayer;

public class MainActivity extends Activity {
  static public TextView tv;
  ImageView iv;
  static public int even = 0;
  static public Canvas canvas;
  static public Screen screen;
  static public Paint paint;
  static public Paint whitePaint;
  static public Graphics graphics;
  static public MyRunnable myRunnable;
  static public FileOutputStream fos;
  static public DataOutputStream dos;
  static public FileInputStream fis;
  static public DataInputStream dis;
  static public AlarmManager alarmMgr;
  static public NotificationManager notificationManager;
  static public PendingIntent alarmIntent;
  public static PendingIntent notificationIntent;
  static public Context mainActivityContext;

  static public Handler myHandler;
  static public String[] icon_list;
  static public String[] menu_list;
  static public List<String> eventsList;
  static public List<Double> eventsTimesList;
  static public int icon_number;
  static public int menu_index;
  static public int sleepingHour;
  static public int wakingHour;
  static public int idealWeight;
  static public int food_index=0;
  static public int secretNumber;
  static public int givenNumber;
  static public int round=0;
  static public int score=0;
  static public int careMisses;
  static public int x=8;
  static public int y;
  static public int W;
  static public int H;
  static public int xIncrement;
  static public int stomach;
  static public int happy;
  static public int weight;
  static public int age;
  static public int discipline;
  static public double timeSinceNeedsDiscipline;
  static public int hghlp;
  static public int hphlp;
  static public int pp;
  static public int[] eatAnim;
  static public int[] foodAnim;
  static public double t;
  static public double timeSinceHungryChanged;
  static public double timeSinceHungry;
  static public double timeSinceHappyChanged;
  static public double timeSinceBored;
  static public double tfdc; //time for discipline call
  static public double tslp;
  static public double tsd;
  static public double ttgsfa;
  static public double timeSinceLeft;
  static public double timeSinceSick;
  static public double timeSinceNeedsLightsOff;
  static public String state;
  static public String oldState;
  static public String character;
  static public String answer;
  static public boolean lightsOn;
  static public boolean isAlive;
  static public boolean isOpen=true;
  static public boolean displayVariables=false;
  static public boolean sleeping;
  static public boolean needsDiscipline;
  static public boolean dirty=false;
  static public boolean sick=false;
  static public boolean isCalling;
  static public double timeToAge;
  static public long t1970birth;
  static public double timeToSleep;
  static public double timeToWake;
  static public Vibrator vibrator;
  static public int bgx,bgy,bgW,bgH;
  static public int surfW=(int)(649*1.1);
  static public int surfH=(int)(747*1.1)+20;
  static public int bgimgW;
  static public int bgimgH;
  int offsetX=(int)(surfW/2-320/2);
  int offsetY=(int)(surfH/2-160/2);
  static public int cakesEaten;
  static public double dcp; //discipline call period;
  
  //Sounds related stuff
  static public MediaPlayer callSound;
  static public MediaPlayer badSound;
  static public MediaPlayer disciplineSound;
  static public MediaPlayer displayResultsSound;
  static public MediaPlayer evolveSound;
  static public MediaPlayer flipSound;
  static public MediaPlayer gameBegin;
  static public MediaPlayer goodSound;
  static public MediaPlayer hatchingSound;
  static public MediaPlayer resetSound;
  static public MediaPlayer smallBeep;

  //Animation related stuff
  static public Rect[] foodAnimPositions;
  static public Rect[] noFoodAnimPositions;
  static public int animation_counter=0;
  static public int oldAnimationCounter=0;


  @Override
  public void onCreate(Bundle savedInstanceState) {
    // on appelle le onCreate du parent
    super.onCreate(savedInstanceState);
    //Toast.makeText(this, "Mytama created", Toast.LENGTH_SHORT).show();
    //On récupère le contexte
    mainActivityContext = this;
    //On inflate le layout XML
    setContentView(R.layout.activity_main);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    // On récupère une instance du layout principal dans une variable
    LinearLayout layout = findViewById(R.id.tama);
    //On récupère une instance du TextView du layout
    tv=(TextView) findViewById(R.id.TV);
    tv.setText("");
    tv.setBackgroundResource(R.color.white);
    //Elements du menu
    icon_list=new String[]{"","Food","Lights","Game","Medicine","Toilet","Status","Discipline","Menu"};
    menu_list=new String[]{"","Reset","Display Variables"};
    eventsList= new ArrayList();
    eventsTimesList = new ArrayList();
    //Variable representant le l'element actuel du menu
    icon_number = 0;
    menu_index=0;
    //Reglage de la peinture
    paint=new Paint();
    paint.setDither(false);
    paint.setAntiAlias(false);
    paint.setFilterBitmap(false);
    whitePaint=new Paint();
    whitePaint.setDither(false);
    whitePaint.setAntiAlias(false);
    whitePaint.setFilterBitmap(false);
    whitePaint.setARGB(128,225,225,225);

    graphics=new Graphics(this);
    myRunnable=new MyRunnable(this);
  
    screen = new Screen(this);
    screen.setLayoutParams(new LinearLayout.LayoutParams(surfW,surfH));
    screen.setZOrderOnTop(true);
    SurfaceHolder screenSurfaceHolder = screen.getHolder();
    screenSurfaceHolder.setFormat(PixelFormat.TRANSLUCENT);
    
    state = "idle";
    stomach=0;
    happy=0;
    t=0;
    layout.addView(screen,0);
    myHandler=new Handler();
    
    //Animation related stuff:
    foodAnimPositions=new Rect[7];
    foodAnimPositions[0]=new Rect(0+offsetX,0+offsetY,80+offsetX,80+offsetY);
    foodAnimPositions[1]=new Rect(0+offsetX,80+offsetY,80+offsetX,160+offsetY);
    foodAnimPositions[2]=new Rect(0+offsetX,80+offsetY,80+offsetX,160+offsetY);
    foodAnimPositions[3]=new Rect(0+offsetX,80+offsetY,80+offsetX,160+offsetY);
    foodAnimPositions[4]=new Rect(0+offsetX,80+offsetY,80+offsetX,160+offsetY);
    foodAnimPositions[5]=new Rect(0+offsetX,80+offsetY,80+offsetX,160+offsetY);
    foodAnimPositions[6]=new Rect(500+offsetX,500+offsetY,500+offsetX,500+offsetY);
    
    noFoodAnimPositions=new Rect[7];
    noFoodAnimPositions[0]=new Rect(0+offsetX,0+offsetY,80+offsetX,80+offsetY);
    noFoodAnimPositions[1]=new Rect(0+offsetX,80+offsetY,80+offsetX,160+offsetY);
    noFoodAnimPositions[2]=new Rect(0+offsetX,80+offsetY,80+offsetX,160+offsetY);
    noFoodAnimPositions[3]=new Rect(0+offsetX,80+offsetY,80+offsetX,160+offsetY);
    noFoodAnimPositions[4]=new Rect(0+offsetX,80+offsetY,80+offsetX,160+offsetY);
    noFoodAnimPositions[5]=new Rect(0+offsetX,80+offsetY,80+offsetX,160+offsetY);
    noFoodAnimPositions[6]=new Rect(0+offsetX,80+offsetY,80+offsetX,160+offsetY);
    
    eatAnim=new int[]{0,0,1,0,1,0,1};
    foodAnim=new int[]{0,0,1,1,2,2,2};

    //code pour l'alarme
    alarmMgr = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
    Intent intent = new Intent("UPDATETAMAGOTCHI");
    alarmIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
    
    //code pour les notifications
    notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
    intent = new Intent(this, MainActivity.class);  //intent est une variable temporaire
    notificationIntent = PendingIntent.getActivity(this, 0, intent, 0);
    
    vibrator= (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
    //graphics.loadCharacterGraphics(this,"Babytchi");
    MainActivity.isOpen=true;
    
    callSound = MediaPlayer.create(this,R.raw.call);
    badSound = MediaPlayer.create(this,R.raw.bad_sound);
    disciplineSound = MediaPlayer.create(this,R.raw.discipline_sound);
    displayResultsSound = MediaPlayer.create(this,R.raw.display_results_sounds);
    evolveSound = MediaPlayer.create(this,R.raw.evolve_sound);
    flipSound = MediaPlayer.create(this,R.raw.flip_sound);
    gameBegin = MediaPlayer.create(this,R.raw.game_begin);
    goodSound = MediaPlayer.create(this,R.raw.good_sound);
    hatchingSound = MediaPlayer.create(this,R.raw.hatching_sound);
    resetSound = MediaPlayer.create(this,R.raw.reset_sound);
    smallBeep = MediaPlayer.create(this,R.raw.small_beep);
  }

  @Override public void onPause() {
    super.onPause();
    MainActivity.tv.setText("");
    myHandler.removeCallbacks(myRunnable.runnable);
    if (isAlive)
    {
        Utils.saveData(this);
        alarmMgr.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                     SystemClock.elapsedRealtime() + 60*1000,
                     60*1000,
                     alarmIntent);
    }
    MainActivity.isOpen=false;
  }

  @Override public void onResume() {
    super.onResume();
    notificationManager.cancelAll();
    Utils.loadData(this);
    if (timeSinceLeft<=3*24*3600)
      if (isAlive)
        for (int i=0; i<timeSinceLeft*2 ;i++)
          Updater.update();
    else
      isAlive=false;
    myHandler.postDelayed(myRunnable.runnable,40);
    alarmMgr.cancel(alarmIntent);
    MainActivity.isOpen=true;
    Utils.computeSleepWakeTimes(MainActivity.sleepingHour,MainActivity.wakingHour);
    if (!MainActivity.state.equals("Egg"))
      MainActivity.state="idle";
    if (!MainActivity.isAlive)
      MainActivity.state="dead1";
    MainActivity.displayVariables=false;
    //MainActivity.tv.setText(MainActivity.state);
  }
}