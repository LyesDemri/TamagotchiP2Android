package com.example.mytama;

public class AdventCalendarPainter extends Painter {
  static int k = 0;
  static int phase;
  
  public static void prepareAnimation() {
    k = 0;
    phase = 1;
  }
  
  public static void drawIdleScreen(int phase) {
    Printer.logPrint("Advent Visits: ");
    for (int i = 0; i < SantaTama.adventVisits.length; i++) {
      Printer.logAppend(SantaTama.adventVisits[i] +", ");
    }
    
    int month = TimeWizard.getTamagotchiMonth();
    int day = TimeWizard.getTamagotchiDay();
    int[] monthDigits = Utils.numDecomposition(month);
    int[] dayDigits = Utils.numDecomposition(day);
    
    if (k==0 && phase == 2) Sounds.playSound("opening_advent_calendar");
    drawSpriteAt("calendar_door_" + phase, 0, 0);
    if (monthDigits[0] != 0)
      drawSpriteAt("num" + monthDigits[0], 16, -1);
    drawSpriteAt("num" + monthDigits[1], 21, -1);
    drawSpriteAt("months_jp", 26, 0);
    if (dayDigits[0] != 0)
      drawSpriteAt("num" + dayDigits[0], 16, 7);
    drawSpriteAt("num" + dayDigits[1], 21, 7);
    drawSpriteAt("days_jp", 26, 8);
  }
  
  public static void drawAnimation() {
    if (phase < 5) {
      drawIdleScreen(phase);
    } else {
      if (k == 0) Sounds.playSound("one_horse_open_sleigh");
      drawSpriteAt(AdventCalendar.generatedObject, 0, 0);
      drawSpriteAt(Tama.character + "_happy" , 16, 0);
    }
    k++;
    if (phase < 5) {
      if (k == 12) {
        k = 0;
        phase++;
      }
    } else {
      if (k == 70) {
        Animations.animation_counter = 9;
        StoringPainter.object = AdventCalendar.generatedObject;
        StoringPainter.disappear = "disappear_big";
        MainActivity.oldState = "advent calendar";
        MainActivity.state = "storing";
      }
    }
  }
}
