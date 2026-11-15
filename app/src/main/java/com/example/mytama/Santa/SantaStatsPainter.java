package com.example.mytama;

import java.lang.Math;

public class SantaStatsPainter extends StatsPainter {
  public static void draw() {
    if (MainActivity.state.equals("StatScreen0")) {
      drawSpriteAt("status_menu_" + SantaTama.language, 8, 0);
      drawSpriteAt("stock_menu_" + SantaTama.language, 8, 8);
      drawSpriteAt("right_arrow", 0, SantaTama.stats_index*8);
    } else if (MainActivity.state.equals("StatScreen1")) {
      int[] digits = Utils.numDecomposition(Tama.age - 100);
      drawSpriteAt("small_santa_face_icon", 0, 0);
      drawSpriteAt("num1", 9, 0);
      drawSpriteAt("num" + digits[0], 14, 0);
      drawSpriteAt("num" + digits[1], 19, 0);
      drawSpriteAt("yr_" + SantaTama.language, 24, 0);
      drawSpriteAt("scale_icon", 0, 8);
      digits = Utils.numDecomposition((int)Math.round(SantaTama.weight - 100));
      drawSpriteAt("num1", 9, 8);
      drawSpriteAt("num" + digits[0], 14, 8);
      drawSpriteAt("num" + digits[1], 19, 8);
      drawSpriteAt("lb_" + SantaTama.language, 24, 8);
    } else if (MainActivity.state.equals("StatScreen2")) {
      drawSpriteAt("distance_" + SantaTama.language, 0, 0);
      drawSpriteAt("discipline_bar", 0, 8);
      for (int i = 0; i < SantaTama.distance; i++) {
        drawDotAt(i*2 + 3, 11);
        drawDotAt(i*2 + 3, 12);
      }
      Printer.logPrint("Steps = " + SantaTama.steps);
      Printer.logAppend("\nCharacter Speed = " + SantaTama.characterSpeed);
      Printer.logAppend("\nEnding Played = " + SantaTama.endingPlayed);
      
    } else if (MainActivity.state.equals("StatScreen3")) {
      drawSpriteAt("santaness_" + SantaTama.language, 0, 0);
      drawHeartScreen("santaness_" + SantaTama.language, SantaTama.santaness, "bell_empty", "bell_full");
    }
    else if (MainActivity.state.equals("StatScreen4")) {
      drawHeartScreen("hungry_" + SantaTama.language, Tama.stomach, "empty_heart", "full_heart");
      Printer.logPrint("Hungry = "  + SantaTama.stomach);
      Printer.logAppend("ttlhungrygh = " + SantaTama.ttlhungryh);
    } else if (MainActivity.state.equals("StatScreen5")) {
      drawHeartScreen("happy_" + SantaTama.language, Tama.happy, "empty_heart", "full_heart");
    } else if (MainActivity.state.equals("Pantry1")) {
      drawHeartScreen("meat_pie_" + SantaTama.language, SantaTama.food, null, "meal_pie_1");
    } else if (MainActivity.state.equals("Pantry2")) {
      drawHeartScreen("cake_" + SantaTama.language, SantaTama.snacks, null, "santa_cake_1");
    }
  }
}
