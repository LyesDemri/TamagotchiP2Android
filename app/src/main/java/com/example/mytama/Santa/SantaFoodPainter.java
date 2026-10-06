package com.example.mytama;

public class SantaFoodPainter extends FoodPainter {
  static String[] eatingStages = new String[]{"_idle_2","_eating"};
  public static void draw() {
    if (MainActivity.state.equals("food choice")) {
      drawSpriteAt("right_arrow", 0, MainActivity.food_index*8);
      drawSpriteAt("food_jp", 8, 0);
      drawSpriteAt("snack_jp", 8, 8);
    } else if (MainActivity.state.equals("eating")) {
      i = 7 - Animations.animation_counter;
      drawSpriteAt(Tama.character + eatingStages[Animations.santaEatAnim[i]], 16 - Animations.santaEatAnim[i], y);
      if (i < 6) {
        drawSpriteAt(SantaGraphics.foods[MainActivity.food_index] + (Animations.santaFoodAnim[i] + 1), 8, 8);
      }
      if (MainActivity.myRunnable.j == 0 || MainActivity.myRunnable.j == 13) {
        Animations.animation_counter = Math.max(Animations.animation_counter - 1, 0);
        if (i == 1 || i == 3 || i == 5) {
          Sounds.beep();
        }
      }
      if (Animations.animation_counter == 0)
        MainActivity.state = MainActivity.oldState;
    } else if (MainActivity.state.equals("saying no food")) {
      i = (Animations.animation_counter + 1) % 2;
      drawSpriteAt(Tama.character + "_no_" + (i + 1), 16-W/2, y);
      if (Animations.decreaseAnimationCounter())
        MainActivity.state = "food choice";
    }
  }
}
