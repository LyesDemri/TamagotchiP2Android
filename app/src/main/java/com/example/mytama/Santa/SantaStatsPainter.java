package com.example.mytama;

import android.graphics.Rect;

public class SantaStatsPainter extends StatsPainter {
  public static void draw() {
    if (MainActivity.state.equals("StatScreen0")) {
      drawSpriteAt("stats_menu_choice_1", 8, 0);
      drawSpriteAt("stats_menu_choice_2", 8, 8);
      drawSpriteAt("right_arrow", 0, SantaTama.stats_index*8);
    } else if (MainActivity.state.equals("StatScreen1")) {
      int[] digits = Utils.numDecomposition(Tama.age - 100);
      drawSpriteAt("small_santa_face_icon", 0, 0);
      drawSpriteAt("num1", 9, 0);
      drawSpriteAt("num" + digits[0], 14, 0);
      drawSpriteAt("num" + digits[1], 19, 0);
      drawSpriteAt("yr_jp", 24, 0);
      drawSpriteAt("scale_icon", 0, 8);
      digits = Utils.numDecomposition(Tama.weight - 100);
      drawSpriteAt("num1", 9, 8);
      drawSpriteAt("num" + digits[0], 14, 8);
      drawSpriteAt("num" + digits[1], 19, 8);
      drawSpriteAt("weight_jp", 24, 8);
    } else if (MainActivity.state.equals("StatScreen2")) {
      drawSpriteAt("distance_jp", 0, 0);
      drawSpriteAt("discipline_bar", 0, 8);
    } else if (MainActivity.state.equals("StatScreen3")) {
      drawSpriteAt("santarashisa_jp", 0, 0);
      for (int j = 0; j < 4; j++) {
        drawSpriteAt("bell_empty",j*8,8);
      }
    }
    else if (MainActivity.state.equals("StatScreen4")) {
      drawHeartScreen("hungry_jp", Tama.stomach, "empty_heart", "full_heart");
    } else if (MainActivity.state.equals("StatScreen5")) {
      drawHeartScreen("happy_jp", Tama.happy, "empty_heart", "full_heart");
    } else if (MainActivity.state.equals("Pantry1")) {
      drawHeartScreen("food_jp", SantaTama.food, null, "meal_pie_1");
    } else if (MainActivity.state.equals("Pantry2")) {
      drawHeartScreen("snack_jp", SantaTama.snacks, null, "santa_cake_1");
    }
  }
}
