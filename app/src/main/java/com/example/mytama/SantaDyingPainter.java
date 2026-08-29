package com.example.mytama;

public class SantaDyingPainter extends SantaPainter {
  static int j = 0;
  static int phase = 0;
  static int[] phaseDurations;
  
  public static void prepareAnimation(){
    j = 0;
    phase = 0;
    phaseDurations = new int[]{100, 50, 25, 25};
  }
  
  public static void draw() {
    if (phase == 0) {
      int k = ((int)((j%25)/12)) == 0 ? 1 : 2;
      drawSpriteAt(Tama.character + "_unhappy_" + k, 8, 0);
      drawSpriteAt("unhappy_cloud_" + k, 24, 0);
    } else if (phase == 1) {
      drawSpriteAt(Tama.character + "_sulking", 8, 0);
      Printer.print("missing sound");
    } else if (phase == 2) {
      drawSpriteAt("cabin_open_door", 8, 0);
    } else if (phase == 3) {
      Printer.print("missing sound");
      drawSpriteAt("cabin_idle_2", 8, 0);
    }
    
    if (j++ == phaseDurations[phase]){
      j = 0;
      if (phase < 3) phase++;
      else {
        SantaTama.isAlive = false;
        MainActivity.state = "dead";
      }
    }
  }
  
  public static void drawDead() {
    int k = ((int)(j / 25)) == 0 ? 1 : 2;
    drawSpriteAt("cabin_idle_" + k, 8, 0);
    Printer.print("Press B to restart");
    Printer.append("\nisAlive = " + SantaTama.isAlive);
    j = (j + 1) % 50;
  }
}
