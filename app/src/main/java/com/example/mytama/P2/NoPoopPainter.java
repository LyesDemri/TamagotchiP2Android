package com.example.mytama;

import android.graphics.Rect;

public class NoPoopPainter extends Painter {
  public static void draw(){
    if (MainActivity.state.equals("saying no")) {
      canvas.drawBitmap(P2Graphics.hashMap.get(Tama.character+"_no_"+(MainActivity.even+1)),null, new Rect((16-W/2)*10+Screen.offsetX,y*10+Screen.offsetY,(16+W/2)*10+Screen.offsetX,(y+H)*10+Screen.offsetY),paint);
      if (MainActivity.myRunnable.j==0 || MainActivity.myRunnable.j==13)
        Animations.animation_counter=Math.max(Animations.animation_counter-1,0);
      if (Animations.animation_counter==0) {
        MainActivity.state = MainActivity.oldState;
        Animations.animation_counter = Animations.oldAnimationCounter;
        Tama.x = 8;
      }
    }
    else if (MainActivity.state.equals("pooping")) {
      if (Animations.animation_counter > 8)
        drawSpriteAt(P2Graphics.hashMap.get(Tama.character+"_no_1"), (16-W/2+MainActivity.even), y);
      else {
        //draw tamagotchi happy with poop
        drawSpriteAt(P2Graphics.hashMap.get(Tama.character+"_happy"), (16-W/2), y);
        drawSpriteAt(P2Graphics.hashMap.get("poop_"+(MainActivity.even+1)), 24, 8);
      }
      if (MainActivity.myRunnable.j==0 || MainActivity.myRunnable.j==13)
        Animations.animation_counter=Math.max(Animations.animation_counter-1,0);
      if (Animations.animation_counter<=0) {
        MainActivity.state = "idle";
        Tama.x = 8;
      }
    }
  }
}
