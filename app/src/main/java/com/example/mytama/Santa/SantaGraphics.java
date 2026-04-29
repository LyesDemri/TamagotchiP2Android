package com.example.mytama;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.content.Context;
import android.content.res.Resources;
import java.util.HashMap;

public class SantaGraphics extends Graphics {
  Context c;
  public static String[] foods = {"meal_pie_", "santa_cake_"};
  public static String[] characterGraphics = {"idle","eat","no", "chimney", "sick","play","sleep","unhappy"};

  public static void loadGeneralGraphics(Context c) {
    //chargement des graphismes
    hashMap = new HashMap<String, Bitmap>();
        
    String[] scalar_graphics_titles = {"aclock", 
    "attention_icon", "bell_empty","black_tile", 
    "cabin_idle_1",
    "cabin_idle_2", "cabin_open_door", "chimney_small", 
    "chimney_large", "disappear", "discipline_bar", 
    "distance_jp", "down_arrow",  "empty_heart", 
    "emptyarrow", "food_icon", "food_jp",
    "fullarrow","full_heart", "game_icon",
    "happy_sun", "happy_jp", "hungry_jp",
    "lb","left_arrow","lights_icon", 
    "mclock", "meal_pie_1", "meal_pie_2", "meal_pie_3",
    "medicine_icon", "mysanta","num0", 
    "num1", "num2", "num3", "num4", "num5","num6", 
    "num7", "num8", "num9", "num0small", "num1small", 
    "num2small", "num3small", "num4small", "num5small", 
    "num6small", "num7small", "num8small", "num9small", 
    "p2bg","pclock","poop_1","poop_2",
    "right_arrow", "santa_cake_1", "santa_cake_2", "santa_cake_3", 
    "small_santa_face_icon", 
    "snack_jp", 
    "santabg", "santarashisa_jp", "santatchi_hatching", 
    "scale_icon","shower",
    "skull","star1", "star2", "star3","stats_icon",
    "stats_menu_choice_1","stats_menu_choice_2",
    "toilet_icon","training_icon","ufo", 
    "unhappy_cloud_1","unhappy_cloud_2","up_arrow",
    "vs", "weight_jp", "yr_jp","z1","z2","z1dark",
    "z2dark"};
    
    try {
      Resources r = c.getResources();
      String pkg = c.getPackageName();
      for (int i = 0; i < scalar_graphics_titles.length; i++) {
        hashMap.put(scalar_graphics_titles[i], BitmapFactory.decodeResource(r, r.getIdentifier(scalar_graphics_titles[i],"drawable",pkg)));
      }
    } catch (Exception e) {
      Printer.log("Error loading graphics");
    }
    Screen.bgimgW = hashMap.get("santabg").getWidth()/30;
    Screen.bgimgH = hashMap.get("santabg").getHeight()/30;
  }
  
  public static void loadCharacterGraphics(Context c, String character) {
    loadGeneralGraphics(c);
    Resources r = c.getResources();
    String pkg = c.getPackageName();
    try {
      for (int i = 0; i < 2; i++) {
        for (int j = 0; j < characterGraphics.length; j++)
          hashMap.put(Tama.character+"_"+characterGraphics[j]+"_"+(i+1), BitmapFactory.decodeResource(r,r.getIdentifier(Tama.character+"_"+characterGraphics[j]+"_"+(i+1),"drawable",pkg)));
      }
      hashMap.put(Tama.character + "_happy", BitmapFactory.decodeResource(r, r.getIdentifier(Tama.character+"_happy","drawable",pkg)));
      hashMap.put(Tama.character + "_right", BitmapFactory.decodeResource(r, r.getIdentifier(Tama.character+"_right","drawable",pkg)));
      hashMap.put(Tama.character + "_left", BitmapFactory.decodeResource(r, r.getIdentifier(Tama.character+"_left","drawable",pkg)));
    } catch (Exception e) {
      Printer.log("Error loading Santa character graphics");
    }
    
    //All santa characters have the same width and height
    Tama.W = 16; Tama.H = 16;
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