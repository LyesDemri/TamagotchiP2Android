package com.example.mytama;

public class BagPainter extends Painter {
  public static int objectIndex = 0;
  static int k = 0;
  static int[] phaseEnds = {25, 87, 75, 50, 175, 50, 0, 0};
  static int[] curtainsDurations = {12, 25, 37, 137, 150, 162, 175};
  static int[] curtainsIndices =   { 0,  1,  2,   3,   2,   1,   0};
  static int phase = 0;
  static String[] workingStates = {"_idle_1", "_working"};
  
  public static void prepareAnimation(){
    k = 0;
    phase = 0;
  }
  
  public static void drawSelectionScreen() {
    if (MainActivity.even == 0) {
      drawSpriteAt("small_left_arrow", 0, 0);
    }
    if (SantaTama.bag[objectIndex] == -1) {
      drawSpriteAt("question_mark_box", 8, 0);
      drawSpriteAt("num0small", 24, 8);
    } else {
      drawSpriteAt(SantaTama.objects[objectIndex], 8, 0);
      drawSpriteAt("num" + SantaTama.bag[objectIndex] + "small", 24, 8);
    }
  }
  public static void drawTransformation() {
    //NOTE: For missy santa and prank santa, phases 1 & 2 are skipped and it just shows the character happy with the new object
    if (phase == 0) {
      if (((k/1.5) % 2 == 0) && (k < 21))
        drawSpriteAt(SantaTama.objects[objectIndex], 8, 0);
      drawSpriteAt("small_left_arrow", 0, 0);
    } else if (phase == 1) {
      drawSpriteAt(SantaTama.objects[objectIndex], 0, 0);
      drawSpriteAt(Tama.character + workingStates[(int)((k / 12.5) % 2)], 16, 0);
    } else if (phase == 2) {
      String sprite = (((k/1.5) % 2 == 0) && (k < 50)) ? SantaTama.objects[objectIndex] : SantaEvolver.object;
      drawSpriteAt(sprite, 0, 0);
      drawSpriteAt(Tama.character + "_working", 16, 0);
    } else if (phase == 3) {
      drawSpriteAt(SantaEvolver.object, 0, 0);
      drawSpriteAt(Tama.character + "_happy", 16, 0);
    } else if (phase == 4) {
      int curtainIndex = 0;
      for (int i = 0; i < curtainsDurations.length; i++) {
        if (k > curtainsDurations[i])
          curtainIndex++;
        else
          break;
      }
      drawSpriteAt("curtains_" + curtainsIndices[curtainIndex], 0, 0);
    } else if (phase == 5) {
      if (objectIndex != 3) {
        drawSpriteAt("tadaa_screen", 0, 0);
        drawSpriteAt(Tama.character + "_happy", 8, 0);
        SantaTama.x = 0;
      } else {
        drawSpriteAt(SantaTama.character + "_happy", 16, 0);
        drawSpriteAt(SantaTama.companion + "_idle_1", 0, 0);
        SantaTama.x = 16;
      }
    } else {
      MainActivity.state = "idle";
      SantaTama.xIncrement = -1;
    }
    k++;
    if (k == phaseEnds[phase] && phase < phaseEnds.length) {
      if (objectIndex > 3 && phase == 0) {
        phase = 3;
      } else {
        phase++;
      }
      if (phase == 4) {
        SantaEvolver.evolve();
      }
      k = 0;
    }
  }
}
