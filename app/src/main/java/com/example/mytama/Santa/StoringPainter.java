package com.example.mytama;

public class StoringPainter extends Painter {
  static String[] alt;
  static String[] suffixes = new String[]{"_happy", "_idle_2"};

  public static String object = "";
  public static String disappear = "";
  static int j; 
  
  static void startStoringAnimation() {
    Animations.animation_counter = 9;
    StoringPainter.object = SantaGraphics.foods[MainActivity.food_index] + "1";
    StoringPainter.disappear = "disappear";
    MainActivity.state = "storing";
    MainActivity.oldState = "food choice";
  }
  
  public static void draw() {
    Printer.log("Storing:" + object + ", " + disappear);
    i = (Animations.animation_counter + 1) % 2;
    alt = new String[]{object, disappear}; 
    j = (int)MainActivity.myRunnable.j / 3;
    j = j % 2;
    int pos;
    pos = disappear.equals("disappear_big") ? 0 : 8;
    if (Animations.animation_counter > 1) {
      drawSpriteAt(alt[j], pos, pos);
      drawSpriteAt(Tama.character + suffixes[i], 16, 0);
      if (MainActivity.myRunnable.j == 13) Sounds.playSound("santa_happy");
    } else {
      drawSpriteAt(disappear, pos, pos);
      drawSpriteAt(Tama.character + "_idle_2", 16, 0);
    }
    if (Animations.decreaseAnimationCounter())
      MainActivity.state = MainActivity.oldState;
  }
  
  
}
