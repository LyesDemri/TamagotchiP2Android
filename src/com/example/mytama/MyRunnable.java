package com.example.mytama;

import java.lang.Runnable;
import android.content.Context;
import android.graphics.Rect;
import java.util.Date;

public class MyRunnable extends Thread
{
  Runnable runnable;
  public int i; //i va de 0 à 29
  public int j; //pour les animations. j peut etre remis a 0 contrairement a i
  public int k; // k augmente sans etre reinitialise
  //k est utile pour des animations qui defilent dans le temps comme game intro screen
  Context c;
  public MyRunnable(Context c)
  {
    this.c=c;
    i=0;
    j=0;
    k=0;
    runnable = new Runnable() {
      @Override public void run() {
        long elapsedTime = new Date().getTime();
        i=(i+1)%25;
        j=(j+1)%25;
        k=(k+1)%1000000000;
        if (i==0 || i==13) {  //On update 2x par seconde
          MainActivity.even=1-MainActivity.even;    //On flippe even
          if (MainActivity.isAlive) // On update si le tamagotchi est vivant
            Updater.update();
        }
            
        Painter.draw(); // On dessine 25x par seconde

        elapsedTime=(new Date().getTime())-elapsedTime;
        MainActivity.myHandler.postDelayed(this,40-elapsedTime);
      }
    };
  }
}