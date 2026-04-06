package com.example.mytama;

import android.content.Context;
import java.lang.Runnable;
import java.lang.Thread;
import java.util.Date;

public class MyRunnable extends Thread {
  Runnable runnable;
  public int i; //i va de 0 à 29
  public int j; //pour les animations. j peut etre remis a 0 contrairement a i
  public int k; // k augmente sans etre reinitialise
  //k est utile pour des animations qui defilent dans le temps comme game intro screen
  Context c;
  public MyRunnable(Context c) {
    this.c=c;
    i=0;
    j=0;
    k=0;
    runnable = new Runnable() {
      @Override public void run() {
        try{
          long elapsedTime = new Date().getTime();
          i = (i+1)%25;
          j = (j+1)%25;
          k = (k+1)%1000000000;
          if (i==0 || i == 13) {  //On inverse even 2x ppar seconde
            MainActivity.even = 1 - MainActivity.even;
            if (Tama.isAlive && i == 0) { // On update 1x par seconde si le tamagotchi est vivant
              try {
                Updater.update();
              } catch (Exception e) {
                  Printer.append("Error updating game: " + e.getMessage());
              }
            }
          }
          try {
            Painter.draw(); // On dessine 25x par seconde
          } catch (Exception e1) {
            Printer.append("Error while drawing screen" + e1.getMessage());
          }
          elapsedTime = (new Date().getTime()) - elapsedTime;
          MainActivity.myHandler.postDelayed(this, 40 - elapsedTime);
        } catch (Exception e2) {
          Printer.append("Error while running main runnable" + e2.getMessage());
        }
      }
    };
  }
}