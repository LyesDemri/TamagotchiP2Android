package com.example.mytama;

public class P2FoodPainter extends FoodPainter {
  public static void draw() {
    if (MainActivity.state.equals("food choice")) {
      drawSpriteAt(P2Graphics.hashMap.get("right_arrow"), 0, MainActivity.food_index*8);
      drawSpriteAt(P2Graphics.hashMap.get("hamburger_1"), 16, 0);
      drawSpriteAt(P2Graphics.hashMap.get("cake_1"), 16, 8);
    }
    else if (MainActivity.state.equals("eating")) {
      i = 7 - Animations.animation_counter;
      foodAnimPositions = Animations.foodAnimPositions[i];
      drawSpriteAt(P2Graphics.hashMap.get(Tama.character+"_eat_"+Animations.eatAnim[i]),16-W/2,y);
      drawSpriteAt(P2Graphics.hashMap.get(P2Graphics.foods[MainActivity.food_index]+(Animations.foodAnim[i]+1)), foodAnimPositions[0], foodAnimPositions[1]);
      if (MainActivity.myRunnable.j == 0 || MainActivity.myRunnable.j == 13)
        Animations.animation_counter=Math.max(Animations.animation_counter - 1, 0);
      if (Animations.animation_counter == 0)
        MainActivity.state = "food choice";
    }
    else if (MainActivity.state.equals("saying no food")) {
      i = 7 - Animations.animation_counter;
      foodAnimPositions=Animations.noFoodAnimPositions[i];
      drawSpriteAt(P2Graphics.hashMap.get(Tama.character+"_no_"+Animations.eatAnim[i]),16-W/2,y);
      drawSpriteAt(P2Graphics.hashMap.get(P2Graphics.foods[MainActivity.food_index]+"1"), foodAnimPositions[0],foodAnimPositions[1]);
      if (MainActivity.myRunnable.j==0 || MainActivity.myRunnable.j==13)
        Animations.animation_counter=Math.max(Animations.animation_counter-1,0);
      if (Animations.animation_counter==0)
        MainActivity.state = "food choice";
    }
  }
}
