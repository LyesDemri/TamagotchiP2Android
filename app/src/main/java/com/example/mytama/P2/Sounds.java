package com.example.mytama;

import android.media.MediaPlayer;
import android.content.Context;
import android.content.res.Resources;
import java.util.HashMap;

public class Sounds {
  public static HashMap<String, MediaPlayer> hashMap;
  
  public static void loadSounds(Context c){
    try {
      Resources r = c.getResources();
      String pkg = c.getPackageName();
    
      String[] soundList = {"bad_sound", "call", 
      "discipline_sound", "display_results_sound",
      "evolve_sound", "flip_sound", "game_begin",
      "good_sound", "hatching_sound", "reset_sound",
      "small_beep"};
    
      hashMap = new HashMap<String, MediaPlayer>();
      MediaPlayer mp = null;
      for (int i = 0; i < soundList.length; i++) {
        mp = MediaPlayer.create(c, r.getIdentifier(soundList[i], "raw", pkg));
        hashMap.put(soundList[i], mp);
      }
    } catch (Exception e) {
      Printer.append("Error loading sounds");
      Printer.append("Error message: " + e.getMessage());
    }
  }
  
  public static void playSound(String soundName) {
    MediaPlayer mp = null;
    try {
      mp = hashMap.get(soundName);
      if (!MainActivity.catchingUp) {
        mp.start();
      }
    } catch (Exception e) {
      Printer.print("Error playing sound");
      Printer.append("Sound " + soundName + " does not exist");
    }
  }
}
