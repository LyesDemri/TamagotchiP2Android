package com.example.mytama;

import android.graphics.Rect;

public class SantaGamePainter extends Painter {
  static int arrowPos = 0;
  static int sleepingTamaIndex = 0;
  static int offsetX = (int)(Screen.surfW/2 - 320/2);
  static int offsetY = (int)(Screen.surfH/2 - 160/2);
  static double delayDuration = 0.25;
  static double scrollDuration = 4;
  static double targetSpeed = 32/scrollDuration; // 32 = tamagotchi screen width
  static double speed = targetSpeed/2.5; //2.5 = fps/pixelSize
  static double delay = delayDuration*25*speed;
  static String[] sleepingTamas = {"sleeping_anon", "sleeping_mametchi", "sleeping_bunbuntchi"};
  
  public static void showGameResult() {
    String result = SantaGame.result;
    if (result.equals("soot")) {
      drawSpriteAt(Tama.character+"_idle_1", 8, 0);
    } else if (result.equals("tama")) {
      //drawSpriteAt(sleepingTamas[sleepingTamaIndex], 0, 0);
      drawSpriteAt("scale_icon", 4, 4);
      drawSpriteAt(Tama.character+"_idle_2", 16, 0);
    } else if (result.equals("object")) {
      drawSpriteAt("meal_pie_1", 0, 0);
      drawSpriteAt(Tama.character+"_idle_2", 16, 0);
    }
    if (Animations.decreaseAnimationCounter()) {
      if (result.equals("soot")) {
        MainActivity.state = "unhappy";
      } else if (result.equals("tama")) {
        MainActivity.state = "happy";
      } else if (result.equals("object")) {
        MainActivity.state = "storing";
      }
      MainActivity.oldState = "playing";
    }
  }
  
  public static void showPlaying() {
    drawSpriteAt("down_arrow", arrowPos*10 + 2, 0);
    for (int i = 0; i < 3; i++){
      drawSpriteAt("chimney_small", i*10, 6);
    }
  }
  
  public static void showGettingInChimney() {
    int k = 3 - ((Animations.animation_counter % 2) + 1); //the 3 - is only there so that the animation starts with the right graphic
    Printer.print(Tama.character+"_chimney_" + k, false);
    if (Animations.animation_counter > 1)
      drawSpriteAt(Tama.character + "_chimney_" + k, 8, 0);
    else
      drawSpriteAt("chimney_large", 8, 0);
    if (Animations.decreaseAnimationCounter())
      MainActivity.state = "show game result";
  }

  public static void showGameIntroScreen() {    
    int k = (int)(MainActivity.myRunnable.k*speed - delay);
    if (k < 0) k = 0;
    int pixelSize = 10;
    k = (int)Math.floor(k/pixelSize);
    k = Math.min(k,32);
    for (int i = 0; i < 4; i++)
      drawSpriteAt("bell_empty",Math.max(i*8 - k, -8), (i%2)*8);
    for (int i = 0; i < 3; i++)
    drawSpriteAt("chimney_small", Math.min(10*i + 32 - k, 32), 6);
    if (k == 32)
      MainActivity.state = "playing";
  }
}