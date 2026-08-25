package com.example.mytama;

public class SuperKuchipatchiPainter extends SantaPainter {
  static int phase = 0;
  static int j = 0;
  static int x = 32;
  
  public static void initializeAnimation(String animation) {
    j = 0;
    phase = 0;
    if (!animation.equals("leaving")) x = 32;
  }
    
  public static void drawLeaving() {
    Printer.print(j);
    if (phase == 0) {
      drawSpriteAt(Tama.character+"_happy", Tama.x, Tama.y);
      if ((j++) == 25){
        phase = 1;
      }
    }
    else if (phase == 1) {
      drawSpriteAt(Tama.character+"_happy", Tama.x, Tama.y);
      if ((Tama.x--) == -16) {
        MainActivity.state = "idle";
        SantaTama.left = true;
      }      
    }
  }
  
  public static void draw() {
    if (phase == 0) {
      drawSpriteAt("super_kuchipatchi_flying", x, 0);
      x--;
      if (x == -16) {
        if (SantaTama.left) {
          phase = 1;
          j=5;
        } else {
          MainActivity.state = "idle";
        }
      }
    } else if (phase == 1) {
      drawSpriteAt("super_kuchipatchi_flying", x, 0, true);
      drawSpriteAt(Tama.character + "_brought_back", x - 16, 0);
      if (x == 16) {
        j--;
        if (j == 0) {
          phase = 2;
        }
      } else {
        x++;
      }
    } else if (phase == 2){
      drawSpriteAt("super_kuchipatchi", x, 0);
      drawSpriteAt(Tama.character + "_unhappy_1", x - 16, 0);
      j++;
      if (j == 5) {
        j = 0;
        phase = 3;
      }
    } else if (phase == 3) {
      drawSpriteAt("super_kuchipatchi", 16, 0);
      drawSpriteAt(Tama.character + "_unhappy_1", 0, 0);
      j++;
      if (j == 50) {
        MainActivity.state = "idle";
        SantaTama.left = false;
        Tama.x = 8;
        SantaTama.tier++;
      }
    } else {
      Printer.log("Unexpected value for phase");
    }
  }
}
