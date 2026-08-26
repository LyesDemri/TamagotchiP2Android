package com.example.mytama;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.content.Context;
import android.content.res.Resources;
import java.util.HashMap;

public class SantaGraphics extends Graphics {
  Context c;
  public static String[] foods = {"meal_pie_", "santa_cake_"};
  public static String[] characterGraphics = {"idle","no", "chimney", "working","play","sleep","unhappy"};

  public static void loadGeneralGraphics() {
    //chargement des graphismes
    hashMap = new HashMap<String, Bitmap>();
        
    String[] scalar_graphics_titles = {
    "aclock",
    "bell_empty", "bell_full", "black_tile", "bonnet",
    "cabin_idle_1", "cabin_idle_2", "cabin_open_door", "calendar_door_1", "calendar_door_2", "calendar_door_3", "calendar_door_4", 
    "chimney_small", "chimney_large", "chisel",
    "christmas_tree_1", "christmas_tree_2", "christmas_tree_3", "christmas_tree_4", "christmas_tree_5_1", "christmas_tree_5_2", "christmas_tree_6_1", "christmas_tree_6_2", 
    "curtains_0", "curtains_1", "curtains_2", "curtains_3",
    "days_jp", "disappear",  "disappear_big", "discipline_bar", "distance_jp", "down_arrow",  
    "empty_heart", "emptyarrow", "envelope_1", "envelope_2", "envelope_3", "envelope_4", "envelope_5",
    "flying_in_sky_1", "flying_in_sky_2", "food_jp", "fullarrow","full_heart",
    "happy_sun", "happy_jp", "hungry_jp",
    "lb","left_arrow",
    "magician_hat", "mclock", "meal_pie_1", "meal_pie_2", "meal_pie_3", "months_jp", "mysanta",
    "num0", "num1", "num2", "num3", "num4", "num5","num6", "num7", "num8", "num9", 
    "num0small", "num1small", "num2small", "num3small", "num4small", "num5small", "num6small", "num7small", "num8small", "num9small", 
    "p2bg", "paper", "pclock", "pencil", "penguin_suit", "perfume", "poison",
    "question_mark_box", 
    "right_arrow", 
    "sailor_cloth",
    "santa_cake_1", "santa_cake_2", "santa_cake_3", "santa_cloth", "santa_egg", "santa_egg_hatching", "santa_hat", "santabg", 
    "santaclautchi_angel_1", "santaclautchi_angel_2", "santaclautchi_debirutchi_1", "santaclautchi_debirutchi_2", 
    "santaclautchi_ginjirotchi_1", "santaclautchi_ginjirotchi_2", 
    "santaclautchi_mametchi_1", "santaclautchi_mametchi_2", "santaclautchi_mametchi_3", "santaclautchi_mametchi_4", 
    "santaclautchi_maskutchi_1", "santaclautchi_maskutchi_2", 
    "santaclautchi_mimitchi_1", "santaclautchi_mimitchi_2", 
    "santaclautchi_oyajitchi_1", "santaclautchi_oyajitchi_2", "santaclautchi_oyajitchi_kicking_1", "santaclautchi_oyajitchi_kicking_2", 
    "santaclautchi_sleeping", "santaclautchi_tamatchi_1", "santaclautchi_tamatchi_2", 
    "santarashisa_jp", "santatchi_hatching",
    "scale_icon","shower", "skateboard", "skull", "sleeping_anon", "sleeping_bunbuntchi", "sleeping_mametchi", "sleigh",
    "small_left_arrow", "small_santa_face_icon",
    "snack_jp",
    "star1", "star2", "star3", "stats_menu_choice_1","stats_menu_choice_2", 
    "super_kuchipatchi", "super_kuchipatchi_flying",
    "santa_scale_icon", "santa_food_icon", "santa_game_icon", "santa_bag_icon", "santa_super_kuchipatchi_icon", "santa_advent_calendar_icon", "santa_tv_icon", "santa_attention_icon",
    "sulking_bubble", "tadaa_screen", "tama_tv_present", "tamatchi_watering_1", "tamatchi_watering_2", "tv_babytchi_1", "tv_babytchi_2", 
    "ufo", "unhappy_cloud_1", "unhappy_cloud_2", "up_arrow",
    "vs", 
    "weight_jp", 
    "yarn", "yr_jp", 
    "z1dark", "z2dark"};
    
    try {
      Resources r = MainActivity.context.getResources();
      String pkg = MainActivity.context.getPackageName();
      for (int i = 0; i < scalar_graphics_titles.length; i++) {
        hashMap.put(scalar_graphics_titles[i], BitmapFactory.decodeResource(r, r.getIdentifier(scalar_graphics_titles[i],"drawable",pkg)));
      }
    } catch (Exception e) {
      Printer.log("Error loading graphics");
    }
    Screen.bgimgW = hashMap.get("santabg").getWidth()/30;
    Screen.bgimgH = hashMap.get("santabg").getHeight()/30;
  }
  
  public static void loadCharacterGraphics(String character) {
    loadGeneralGraphics();
    Resources r = MainActivity.context.getResources();
    String pkg = MainActivity.context.getPackageName();
    try {
      for (int i = 0; i < 2; i++) {
        for (int j = 0; j < characterGraphics.length; j++)
          hashMap.put(Tama.character+"_"+characterGraphics[j]+"_"+(i+1), BitmapFactory.decodeResource(r,r.getIdentifier(Tama.character+"_"+characterGraphics[j]+"_"+(i+1),"drawable",pkg)));
      }
      hashMap.put(Tama.character + "_soot", BitmapFactory.decodeResource(r, r.getIdentifier(Tama.character+"_soot","drawable",pkg)));
      hashMap.put(Tama.character + "_eating", BitmapFactory.decodeResource(r, r.getIdentifier(Tama.character+"_eating","drawable",pkg)));
      hashMap.put(Tama.character + "_happy", BitmapFactory.decodeResource(r, r.getIdentifier(Tama.character+"_happy","drawable",pkg)));
      hashMap.put(Tama.character + "_working", BitmapFactory.decodeResource(r, r.getIdentifier(Tama.character+"_working","drawable",pkg)));
      hashMap.put(Tama.character + "_left", BitmapFactory.decodeResource(r, r.getIdentifier(Tama.character+"_left","drawable",pkg)));
      hashMap.put(Tama.character + "_brought_back", BitmapFactory.decodeResource(r, r.getIdentifier(Tama.character+"_brought_back","drawable",pkg)));
      hashMap.put(Tama.character + "_sulking", BitmapFactory.decodeResource(r, r.getIdentifier(Tama.character+"_sulking","drawable",pkg)));
      hashMap.put(Tama.character + "_sulking_end", BitmapFactory.decodeResource(r, r.getIdentifier(Tama.character+"_sulking_end","drawable",pkg)));
    } catch (Exception e) {
      Printer.log("Error loading Santa character graphics");
    }
    
    if (!SantaTama.companion.equals("")) {
      loadCompanionGraphics(SantaTama.companion);
    }
    
    //All santa characters have the same width and height
    Tama.W = 16; Tama.H = 16;
  }
  
  public static void clearCharacterGraphics() {
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < characterGraphics.length; j++)
          hashMap.remove(Tama.character+"_"+characterGraphics[j]+"_"+(i+1));
    }
    hashMap.remove(Tama.character + "_soot");
    hashMap.remove(Tama.character + "_eating");
    hashMap.remove(Tama.character + "_happy");
    hashMap.remove(Tama.character + "_working"); 
    hashMap.remove(Tama.character + "_left");
    hashMap.remove(Tama.character + "_brought_back");
    hashMap.remove(Tama.character + "_sulking");
    hashMap.remove(Tama.character + "_sulking_end");

  }
  
  public static void loadCompanionGraphics(String companion) {
    Resources r = MainActivity.context.getResources();
    String pkg = MainActivity.context.getPackageName();
    hashMap.remove(SantaTama.companion + "_idle_1");
    hashMap.remove(SantaTama.companion + "_idle_2");
    hashMap.put(companion + "_idle_1", BitmapFactory.decodeResource(r, r.getIdentifier(companion + "_idle_1","drawable",pkg)));
    hashMap.put(companion + "_idle_2", BitmapFactory.decodeResource(r, r.getIdentifier(companion + "_idle_2","drawable",pkg)));
  }
}