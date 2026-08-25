package com.example.mytama;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.content.Context;
import android.content.res.Resources;
import java.util.HashMap;

public class Graphics {
  Context c;
  
  public static HashMap<String, Bitmap> hashMap;
  
  public static void loadGeneralGraphics() {
    if (MainActivity.version.equals("P2")){
      P2Graphics.loadGeneralGraphics();
    } else if (MainActivity.version.equals("Santa")) {
      SantaGraphics.loadGeneralGraphics();
    }   
  }
  
  public static void loadCharacterGraphics(String character) {
    if (MainActivity.version.equals("P2")){
      P2Graphics.loadCharacterGraphics(character);
    } else if (MainActivity.version.equals("Santa")) {
      SantaGraphics.loadCharacterGraphics(character);
    }
  }
  
  public static void clearCharacterGraphics() {
    if (MainActivity.version.equals("P2")){
      P2Graphics.clearCharacterGraphics();
    } else if (MainActivity.version.equals("Santa")) {
      SantaGraphics.clearCharacterGraphics();
    }   
  }
}