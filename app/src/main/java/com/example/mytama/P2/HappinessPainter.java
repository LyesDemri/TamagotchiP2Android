package com.example.mytama;

public class HappinessPainter extends Painter {
  public static void draw(){
    if (MainActivity.state.equals("happy")) {
      if (MainActivity.even == 1) {
        drawSpriteAt(Graphics.hashMap.get(Tama.character+"_happy"), 16 - W/2, y);
        drawSpriteAt(Graphics.hashMap.get("happy_sun"), 16 + W/2, y);
      } else
        drawSpriteAt(Graphics.hashMap.get(Tama.character+"_idle_2"), (16-W/2), y);
      if (Animations.animation_counter == 8 && MainActivity.myRunnable.j == 0)
        Sounds.playSound("good_sound");
      if (MainActivity.myRunnable.j == 0 || MainActivity.myRunnable.j == 13)
        Animations.animation_counter = Math.max(Animations.animation_counter-1, 0);
      if (Animations.animation_counter == 0) {
        MainActivity.state = MainActivity.oldState;
        Animations.animation_counter = Animations.oldAnimationCounter;
        Tama.x = 8;
        MainActivity.myRunnable.k = -1;
      }
    } else if (MainActivity.state.equals("unhappy") ||
               MainActivity.state.equals("scolded")) {
      drawSpriteAt(Graphics.hashMap.get(Tama.character+"_unhappy_"+(MainActivity.even+1)), (16-W/2), y);
      drawSpriteAt(Graphics.hashMap.get("unhappy_cloud_"+(MainActivity.even+1)), (16+W/2), y);
      if (Animations.animation_counter == 8)
        if (MainActivity.state.equals("unhappy"))
          Sounds.playSound("bad_sound");
        else
          Sounds.playSound("discipline_sound");
      if (MainActivity.myRunnable.j == 0 || MainActivity.myRunnable.j == 13)
        Animations.animation_counter = Math.max(Animations.animation_counter-1, 0);
      if (Animations.animation_counter==0) {
        MainActivity.state = MainActivity.oldState;
        Animations.animation_counter = Animations.oldAnimationCounter;
        Tama.x = 8;
        if (MainActivity.state.equals("unhappy"))
          MainActivity.myRunnable.k = -1;
        else
          Sounds.playSound("good_sound");
      }
    }
  }
}
