package com.example.mytama;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.MotionEvent;

class Screen extends SurfaceView implements SurfaceHolder.Callback 
{
  Context context;
  
  //Constructeur de la classe Screen:
  public Screen(Context context)
  {
    super(context);
    this.context = context;
    getHolder().addCallback(this);
    this.setOnTouchListener(new View.OnTouchListener(){
      @Override public boolean onTouch(View v, MotionEvent motionEvent) {
        if (motionEvent.getActionMasked()==MotionEvent.ACTION_DOWN)
          handleTouch(motionEvent.getX(),motionEvent.getY());
        return true;
      };
    });
  }

  public void handleTouch(float x, float y)
  {
    if (y>700 && y<800)
    {
      if (x>200 && x<300)
      {
        MainActivity.vibrator.vibrate(50);
        ButtonA.handle();
      }
      else if (x>300 && x<400)
      {
        MainActivity.vibrator.vibrate(50);
        ButtonB.handle();
      }
      else if (x>400 && x<600)
      {
        MainActivity.vibrator.vibrate(50);
        ButtonC.handle();
      }
    }
  }
  
  //Ces méthodes DOIVENT être implémentées:
  public void surfaceCreated(SurfaceHolder surfaceHolder) {}
  public void surfaceDestroyed(SurfaceHolder surfaceHolder) {}
  public void surfaceChanged(SurfaceHolder surfaceHolder, int x, int y, int z){}
  
  
}