package com.example.mytama;

import android.media.MediaPlayer;
import android.content.Context;
import android.content.res.Resources;
import java.util.HashMap;

public class Sounds {
  public static HashMap<String, MediaPlayer> hashMap;
  static String[] soundList;
  public static void loadSounds(Context c){
    try {
      Resources r = c.getResources();
      String pkg = c.getPackageName();
      getSoundList();
      hashMap = new HashMap<String, MediaPlayer>();
      MediaPlayer mp = null;
      for (int i = 0; i < soundList.length; i++) {
        mp = MediaPlayer.create(c, r.getIdentifier(soundList[i], "raw", pkg));
        hashMap.put(soundList[i], mp);
      }
    } catch (Exception e) {
      Printer.log("Error loading sounds");
      Printer.log("Error message: " + e.getMessage());
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
      Printer.log("Error playing sound");
      Printer.log("Sound " + soundName + " does not exist");
    }
  }
  
  public static void getSoundList() {
    if (MainActivity.version.equals("P2")){
      soundList = new String[]{"bad_sound","call", 
      "discipline_sound", "display_results_sound",
      "evolve_sound", "flip_sound", "game_begin",
      "good_sound", "hatching_sound", "reset_sound","small_beep"};
    } else if (MainActivity.version.equals("Santa")){
      soundList = new String[]{"cabin_exit",
      "new_character", "reset_sound", "game_begin",
      "santa_call_sound", "santa_small_beep", "santa_validate_sound"};
    } else{
      Printer.log("Unknown version:" + MainActivity.version);
    }
  }
  
  public static void beep(){
    if (MainActivity.version.equals("P2")){
      playSound("small_beep");
    } else if (MainActivity.version.equals("Santa")){
      playSound("santa_small_beep");
    }
  }
  
  public static void validate(){
    if (MainActivity.version.equals("P2")){
      playSound("small_beep");
    } else if (MainActivity.version.equals("Santa")){
      playSound("santa_validate_sound");
    }
  }
}
