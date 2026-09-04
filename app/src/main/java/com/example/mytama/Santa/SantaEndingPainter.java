package com.example.mytama;

public class SantaEndingPainter extends SantaPainter {
  public static int phase;
  public static void prepareAnimation() {
    phase = 0;
    SantaTama.endingPlayed = true;
  }
  
  public static void draw() {
    if (phase == 0) {
      Printer.print("drawing phase 0 of ending");
      if (Tama.character.equals("classic_santa")) {
        String[] suffixes = {"_happy", "_idle_1"};
        drawSpriteAt("classic_santa" + suffixes[MainActivity.even], 0, 0, true);
        drawChild();
      } else {
        drawSpriteAt("flying_in_sky_" + (MainActivity.even+1), 0, 0);
      }
    } else if (phase == 1) {
      Printer.print("drawing phase 1 of ending");
      drawSpriteAt("tama_tv_present", 0, 0);
      drawChild();
    } else if (phase == 2){
      MainActivity.state = "idle";
    }
  }
  
  static void drawChild() {
    if (Tama.age == 111) {
      drawSpriteAt("santaclautchi_mametchi_" + (MainActivity.even+1), 16, 0);
    } else if (Tama.age > 111) {
      drawSpriteAt("santaclautchi_oyajitchi_" + (MainActivity.even+1), 16, 0);
    } else {
      Printer.print("WARNING: age is < 111!");
    }
  }
}
