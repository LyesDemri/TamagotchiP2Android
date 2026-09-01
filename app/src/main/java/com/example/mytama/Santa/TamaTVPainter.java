package com.example.mytama;

public class TamaTVPainter extends Painter {
  static int k = 0;
  public static void draw() {
    int phase = k < 25 ? 1 : 2;
    //didn't feel like writing smart code:
    if (Tama.age == 100) {
      drawSpriteAt("christmas_tree_1", 0, 0);
      drawSpriteAt("tv_babytchi_" + phase, 21, 8);
    } else if (Tama.age == 101) {
      drawSpriteAt("christmas_tree_2", 0, 0);
      drawSpriteAt("santaclautchi_tamatchi_" + phase, 16, 0);
    } else if (Tama.age == 102) {
      drawSpriteAt("christmas_tree_3", 0, 0);
      drawSpriteAt("tamatchi_watering_" + phase, 16, 0);
    }  else if (Tama.age == 103) {
      drawSpriteAt("christmas_tree_4", 0, 0);
      drawSpriteAt("santaclautchi_mimitchi_" + phase, 16, 0);
    }  else if (Tama.age == 104) {
      drawSpriteAt("christmas_tree_6_1", 0, 0);
      drawSpriteAt("santaclautchi_debirutchi_" + phase, 16, 0);
    }   else if (Tama.age == 105) {
      drawSpriteAt("christmas_tree_6_1", 0, 0);
      drawSpriteAt("santaclautchi_maskutchi_" + phase, 16, 0);
    }  else if (Tama.age == 106) {
      drawSpriteAt("christmas_tree_6_" + phase, 0, 0);
      drawSpriteAt("santaclautchi_angel_" + phase, 16, 0);
    } else if (Tama.age == 107) {
      drawSpriteAt("christmas_tree_5_2", 0, 0);
      drawSpriteAt("santaclautchi_ginjirotchi_" + phase, 16, 0);
    } else if (Tama.age >= 108 && Tama.age < 111) {
      drawSpriteAt("christmas_tree_5_" + phase, 0, 0);
      drawSpriteAt("santaclautchi_mametchi_" + (phase+2), 16, 0);
    } else if (Tama.age == 111 && !SantaTama.endingPlayed) {
      //12th day but santa hasn't arrived'
      drawSpriteAt("christmas_tree_5_" + phase, 0, 0);
      drawSpriteAt("santaclautchi_mametchi_" + (phase+2), 16, 0);
    } else if (Tama.age == 111 && SantaTama.endingPlayed) {
      //12th day and santa has arrived
      drawSpriteAt("tama_tv_present", 0, 0);
      drawSpriteAt("santaclautchi_mametchi_" + (phase+2), 16, 0);
    } else if (Tama.age >= 112 && !SantaTama.endingPlayed) {
      //post-12th day but santa hasn't come
      drawSpriteAt("santaclautchi_oyajitchi_kicking_" + phase, 16, 0);
      drawSpriteAt("christmas_tree_6_1", 0, 0);
    } else if (Tama.age >= 112 && SantaTama.endingPlayed) {
      //post-12th day and santa has arrived
      drawSpriteAt("tama_tv_present", 0, 0);
      if (SantaTama.arrivedOnTime) drawSpriteAt("santaclautchi_mametchi_" + phase, 16, 0);
      else drawSpriteAt("santaclautchi_oyajitchi_" + phase, 16, 0);
    }
    Printer.logPrint("age:" + Tama.age + "\nt = " + Tama.t);
    Printer.logAppend("\n" + TimeWizard.getTamagotchiTime());
    
    k = (k+1) % 50;
  }
}
