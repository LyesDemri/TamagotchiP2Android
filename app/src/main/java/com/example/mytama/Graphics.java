package com.example.mytama;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.content.Context;
import android.content.res.Resources;
import java.util.HashMap;

public class Graphics {
  Context c;
  
  public static HashMap<String, Bitmap> hashMap;
  
  public static void loadGeneralGraphics(Context c) {
    if (MainActivity.version.equals("P2")){
      P2Graphics.loadGeneralGraphics(c);
    } else if (MainActivity.version.equals("Santa")) {
      SantaGraphics.loadGeneralGraphics(c);
    }   
  }
  
  public static void loadCharacterGraphics(Context c, String character) {
    if (MainActivity.version.equals("P2")){
      P2Graphics.loadCharacterGraphics(c, character);
    } else if (MainActivity.version.equals("Santa")) {
      SantaGraphics.loadCharacterGraphics(c, character);
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