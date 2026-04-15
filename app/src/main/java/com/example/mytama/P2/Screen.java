package com.example.mytama;

import android.content.Context;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.widget.LinearLayout;
import android.view.SurfaceView;
import android.view.View;
import com.example.mytama.MainActivity;
import android.graphics.PixelFormat;
import java.io.File;

class Screen extends SurfaceView implements SurfaceHolder.Callback {
  Context context;
  
  public static Screen screen;
  static public int surfW;
  static public int surfH;
  static public int bgx, bgy, bgW, bgH;
  static public int bgimgW;
  static public int bgimgH;
  public static int offsetX;
  public static int offsetY;
  static public int canvasWidth;
  static public int canvasHeight;
  
  //Screen class constructor:
  public Screen(Context context) {
    super(context);
    this.context = context;
    getHolder().addCallback(this);
    this.setOnTouchListener(new View.OnTouchListener(){
      @Override public boolean onTouch(View v, MotionEvent motionEvent) {
        if (motionEvent.getActionMasked()==MotionEvent.ACTION_DOWN) {
          handleTouch(motionEvent.getX(),motionEvent.getY());
        }
        return true;
      };
    });
  }
  
  //Init Screen
  public static void initScreen(Context c) {
    surfW=(int)(649*1.1);
    surfH=(int)(747*1.1)+20;
    offsetX=(int)(Screen.surfW/2-320/2);
    offsetY=(int)(Screen.surfH/2-160/2);
    screen = new Screen(c);
    screen.setLayoutParams(new LinearLayout.LayoutParams(surfW, surfH));
    screen.setZOrderOnTop(true);
    SurfaceHolder screenSurfaceHolder = screen.getHolder();
    screenSurfaceHolder.setFormat(PixelFormat.TRANSLUCENT);
  }

  public void handleTouch(float x, float y) {
    if (MainActivity.state.equals("reset_screen")){
      ResetScreen.handle(x,y);
    } else if (MainActivity.state.equals("tama_select_screen")) {
      TamaSelectScreen.handle(x,y);
    } else if (MainActivity.state.equals("version_select_screen")){
      VersionSelectScreen.handle(y);
    } else {
      if (y > 700 && y < 800) {
        if (x > 200 && x < 300)
          ButtonA.handle();
        else if (x > 300 && x < 400) 
          ButtonB.handle();
        else if (x > 400 && x < 600) 
          ButtonC.handle();
      }
    }
  }
  
  //These methods MUST be implemented
  public void surfaceCreated(SurfaceHolder surfaceHolder) {}
  public void surfaceDestroyed(SurfaceHolder surfaceHolder) {}
  public void surfaceChanged(SurfaceHolder surfaceHolder, int x, int y, int z){}
}