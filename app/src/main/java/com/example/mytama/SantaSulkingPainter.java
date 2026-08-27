package com.example.mytama;

import java.lang.Math;

public class SantaSulkingPainter extends SantaPainter {
  static int j = 0;
  static int phase = 0;
  
  public static void prepareAnimation(){
    j = 0;
    phase = 0;
  }
  
  public static void draw() {
    if (phase == 0){
      drawSpriteAt("envelope_1", 8, 0);
      if ((j++) == 25) {
        phase++;
        j = 0;
      }
    } else if (phase == 1) {
      drawSpriteAt("envelope_"+((int)Math.floor(j/2)+2), 8, 0);
      //Maths because animation would otherwise be too fast
      if ((j++) == 7) {
        phase++;
        j = 0;
      }
    } else if (phase == 2) {
      drawSpriteAt("envelope_5", 8-j, 0);
      if ((j++) == 24) {
        phase++;
        j = 0;
      }
    } else if (phase == 3) {
      drawSpriteAt("envelope_5", 0, 0);
      drawSpriteAt(Tama.character + "_happy", 16, 0);
      if ((j++) == 100) {
        Animations.animation_counter = 9;
        StoringPainter.object = "envelope_5";
        StoringPainter.disappear = "disappear_big";
        MainActivity.oldState = "idle";
        MainActivity.state = "storing";
      }
    }
  }
  
  public static void drawSulking(){
    drawSpriteAt(Tama.character + "_sulking_2",8 ,0);
    if ((j++) == 25) {
      MainActivity.state = "idle";
    }
  }
}
