package com.example.mytama;

public class SantaHappinessPainter extends HappinessPainter {
  public static void draw(){
    if (MainActivity.state.equals("happy")) {
      if (MainActivity.myRunnable.j < 13) {
        drawSpriteAt(P2Graphics.hashMap.get(Tama.character+"_happy"), 16 - W/2, y);
        drawSpriteAt(P2Graphics.hashMap.get("happy_sun"), 16 + W/2, y);
      } else
        drawSpriteAt(P2Graphics.hashMap.get(Tama.character+"_idle_2"), (16-W/2), y);
      if (MainActivity.myRunnable.j == 1) Sounds.playSound("santa_happy");
      if (Animations.decreaseAnimationCounter()) {
        MainActivity.state = MainActivity.oldState;
        Animations.animation_counter = Animations.oldAnimationCounter;
        Tama.x = 8;
        MainActivity.myRunnable.k = -1;
      }
    } else if (MainActivity.state.equals("unhappy")) {
      int i = MainActivity.myRunnable.j >= 13 ? 0 : 1;
      drawSpriteAt(P2Graphics.hashMap.get(Tama.character+"_unhappy_" + (i+1)), (16 - W/2), y);
      drawSpriteAt(P2Graphics.hashMap.get("unhappy_cloud_" + (i + 1)), (16 + W/2), y);
      if (MainActivity.myRunnable.j == 1)
        Sounds.playSound("santa_unhappy_sound");
      if (Animations.decreaseAnimationCounter()) {
        MainActivity.state = MainActivity.oldState;
        Animations.animation_counter = Animations.oldAnimationCounter;
        Tama.x = 8;
        if (MainActivity.state.equals("unhappy"))
          MainActivity.myRunnable.k = -1;
        else
          Sounds.playSound("santa_small_beep");
      }
    }
  }
}
