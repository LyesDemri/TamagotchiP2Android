package com.example.mytama;

import android.content.Context;
import java.lang.Runnable;
import java.lang.Thread;
import java.util.Date;

public class MyRunnable extends Thread {
  Runnable runnable;
  public int i; //i counts from 0 to 24
  public int j; //for animations. j can be reset contrary to i
  public int k; // k increses without being reset
  //k is useful for sliding animations such as the game intro screen
  
  Context c;
  public MyRunnable(Context c) {
    this.c = c;
    i = 0;
    j = 0;
    k = 0;
    runnable = new Runnable() {
      @Override public void run() {
        try {
          long elapsedTime = new Date().getTime();
          i = (i+1)%25;
          j = (j+1)%25;
          k = (k+1)%250; //(longest animation in seconds x 10)
          if (i==0 || i == 13) {  //even is flipped twice per second
            MainActivity.even = 1 - MainActivity.even;
            if (Tama.isAlive && i == 0) { // update once per second if tama is alive and we're playing'
              if (!MainActivity.state.equals("reset_screen") && !MainActivity.state.equals("tama_select_screen") && !MainActivity.state.equals("version_select_screen")){
                try {
                  Updater.update();
                } catch (Exception e) {
                  Printer.log("Error updating game: " + e.getMessage());
                }
              }
            }
          }
          try {
            Painter.draw(); // On dessine 25x par seconde
          } catch (Exception e1) {
            Printer.log("Error while drawing screen: " + e1.getMessage());
          }
          elapsedTime = (new Date().getTime()) - elapsedTime;
          //Printer.print("Main loop took " + elapsedTime + " ms", false);
          MainActivity.myHandler.postDelayed(this, 40 - elapsedTime);
        } catch (Exception e2) {
          Printer.log("Error while running main runnable" + e2.getMessage());
        }
      }
    };
  }
}