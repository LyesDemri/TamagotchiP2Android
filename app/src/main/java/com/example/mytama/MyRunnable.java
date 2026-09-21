package com.example.mytama;

import android.content.Context;
import java.lang.Runnable;
import java.lang.Thread;
import java.util.Date;

public class MyRunnable extends Thread {
  public static Runnable runnable;
  public static int i; //i counts from 0 to 24
  public static int j; //for animations. j can be reset contrary to i
  public static int k; // k increses without being reset
  //k is useful for sliding animations such as the game intro screen
  public static int numTamas;
  public static long  elapsedTime;
  public static long frameDuration;
  public static long oldTime = new Date().getTime();
  public static long currentFrameTime = new Date().getTime();
  public static boolean runnableExists = false;
  public static void initialize() {
    i = 0;
    j = 0;
    k = 0;
    elapsedTime = 0;
    frameDuration = 0;
    oldTime = new Date().getTime();
    currentFrameTime = new Date().getTime();
    if (runnableExists) return;
    runnable = new Runnable() {
      @Override public void run() {
        try {
          currentFrameTime = new Date().getTime();
          frameDuration = currentFrameTime - oldTime;
          oldTime = currentFrameTime;
          //Printer.print("Frame duration = " + frameDuration + " (" +elapsedTime+")");
          //Printer.append("\nrunnable inetance: " + System.identityHashCode(this));
          elapsedTime = currentFrameTime;
          if (MainActivity.isOpen) {
            i = (i + 1) % 25;
            j = (j + 1) % 25;
            k = (k + 1) % 250; //(longest animation in seconds x 10)
            if (i == 0 || i == 13) {  //even is flipped twice per second
              MainActivity.even = 1 - MainActivity.even;
              if (Tama.isAlive && i == 0) { // update once per second if tama is alive and we're playing'
                if (!MainActivity.state.equals("reset_screen") && !MainActivity.state.equals("tama_select_screen") && !MainActivity.state.equals("version_select_screen")){
                  try {
                    Updater.update();
                    //The app increasingly lags behind as it's being used
                    //this fixes it but it would be better to find the cause of the lag
                    long delta = TimeWizard.getTime() - TimeWizard.getTamagotchiLongTime();
                    if (delta > 1000)
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
            elapsedTime = new Date().getTime() - elapsedTime;
            MainActivity.myHandler.postDelayed(this, 40L-elapsedTime*1 + 1*(40L-frameDuration));
          } else {
            Updater.updateAllTamas();
            MainActivity.isOpen = false;
            numTamas = DataSaverLoader.getSaveFiles().length;
            elapsedTime = (new Date().getTime()) - elapsedTime;
            MainActivity.myHandler.postDelayed(this, numTamas*1000 - elapsedTime);
            //Utils.notifyUser("Updated all tamas at " + (new Date().getTime()) + " in " + elapsedTime, "");
          }
        } catch (Exception e2) {
          Printer.log("Error while running main runnable" + e2.getMessage());
        }
      }
    };
    runnableExists = true;
  }
}