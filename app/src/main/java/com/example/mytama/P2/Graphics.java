package com.example.mytama;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.content.Context;
import android.content.res.Resources;
import java.util.HashMap;

public class Graphics 
{
  Context c;
  public static HashMap<String, Bitmap> hashMap;
  public static String[] foods = {"hamburger_", "cake_"};
  public static String[] characterGraphics = {"idle","eat","no","sick","play","sleep","unhappy"};

  public Graphics(Context c)
  {
    this.c = c;
    //chargement des graphismes
    hashMap = new HashMap<String, Bitmap>();
        
    String[] scalar_graphics_titles = {"aclock", "attention_icon","black_tile", "cake_1", "cake_2", "cake_3", "discipline_screen", "down_arrow", "egg_idle_1","egg_idle_2", "empty_heart", "emptyarrow", "face_icon",
    "food_icon","fullarrow","full_heart","game_icon","happy_sun", "hamburger_1", "hamburger_2", "hamburger_3", "happy_word","hatching","hungry_word","lb","left_arrow",
    "lights_icon", "mclock", "medicine_icon", "mytama3",
    "num0", "num1", "num2", "num3", "num4", "num5", "num6", "num7", "num8", "num9",
    "num0small", "num1small", "num2small", "num3small", "num4small", "num5small", "num6small", "num7small", "num8small", "num9small", 
    "p2bg","pclock","poop_1","poop_2","right_arrow","scale_icon","shower","skull",
    "star1", "star2", "star3","stats_icon","toilet_icon","training_icon","ufo", 
    "unhappy_cloud_1","unhappy_cloud_2","up_arrow","vs","yr","z1","z2","z1dark","z2dark"};
    
    try{
      for (int i = 0; i < scalar_graphics_titles.length; i++)
        loadGeneralGraphics(c, scalar_graphics_titles[i]);
    } catch (Exception e) {
      Printer.print("Error loading graphics");
    }
    Screen.bgimgW = hashMap.get("p2bg").getWidth()/30;
    Screen.bgimgH = hashMap.get("p2bg").getHeight()/30;
  }
  
  public static void loadGeneralGraphics(Context c, String resourceName) {
    Resources r = c.getResources();
    String pkg = c.getPackageName();
    hashMap.put(resourceName, BitmapFactory.decodeResource(c.getResources(),r.getIdentifier(resourceName,"drawable",pkg)));
  }

  public static void loadCharacterGraphics(Context c, String character) {
    Resources r = c.getResources();
    String pkg = c.getPackageName();
    try {
      for (int i = 0; i < 2; i++) {
        for (int j = 0; j < characterGraphics.length; j++)
          hashMap.put(Tama.character+"_"+characterGraphics[j]+"_"+(i+1), BitmapFactory.decodeResource(c.getResources(),r.getIdentifier(Tama.character+"_"+characterGraphics[j]+"_"+(i+1),"drawable",pkg)));
      }
      hashMap.put(Tama.character + "_happy", BitmapFactory.decodeResource(c.getResources(),r.getIdentifier(Tama.character+"_happy","drawable",pkg)));
      hashMap.put(Tama.character + "_right", BitmapFactory.decodeResource(c.getResources(),r.getIdentifier(Tama.character+"_right","drawable",pkg)));
      hashMap.put(Tama.character + "_left", BitmapFactory.decodeResource(c.getResources(),r.getIdentifier(Tama.character+"_left","drawable",pkg)));
    } catch (Exception e) {
      Printer.print("Error loading character graphics");
    }
    
    if (Tama.character.equals("babytchi")) {
      Tama.W = 8; Tama.H = 8;
    }
    else if (Tama.character.equals("tonmarutchi")) {
      Tama.W = 12; Tama.H = 12;
    }
    else {
      Tama.W = 16; Tama.H = 16;
    }
  }
  
  public static void clearCharacterGraphics() {
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < characterGraphics.length; j++)
          hashMap.remove(Tama.character+"_"+characterGraphics[j]+"_"+(i+1));
    }
    hashMap.remove(Tama.character + "_happy");
    hashMap.remove(Tama.character + "_right"); 
    hashMap.remove(Tama.character + "_left");
  }
}