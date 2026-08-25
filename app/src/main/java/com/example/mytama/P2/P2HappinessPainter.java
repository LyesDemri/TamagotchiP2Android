package com.example.mytama;

public class P2HappinessPainter extends HappinessPainter {
  public static void draw(){
    if (MainActivity.state.equals("happy")) {
      if (MainActivity.myRunnable.j > 13) {
        drawSpriteAt(Tama.character+"_happy", 16 - W/2, y);
        drawSpriteAt("happy_sun", 16 + W/2, y);
      } else
        drawSpriteAt(Tama.character+"_idle_2", (16-W/2), y);
      if (Animations.animation_counter == 8 && MainActivity.myRunnable.j == 0)
        Sounds.playSound("good_sound");
        
      if (Animations.decreaseAnimationCounter()){
        MainActivity.state = MainActivity.oldState;
        Animations.animation_counter = Animations.oldAnimationCounter;
        Tama.x = 8;
        MainActivity.myRunnable.k = -1;
      }
    } else if (MainActivity.state.equals("unhappy") ||
               MainActivity.state.equals("scolded")) {
      int k = (MainActivity.myRunnable.j > 13) ? 1 : 0;
      k++;
      drawSpriteAt(Tama.character+"_unhappy_" + k, (16 - W/2), y);
      drawSpriteAt("unhappy_cloud_" + k, (16 + W/2), y);
      if (Animations.animation_counter == 8)
        if (MainActivity.state.equals("unhappy"))
          Sounds.playSound("bad_sound");
        else
          Sounds.playSound("discipline_sound");
      if (Animations.decreaseAnimationCounter()) {
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
