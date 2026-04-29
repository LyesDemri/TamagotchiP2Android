package com.example.mytama;

public class StatsPainter extends Painter {
  public static void draw() {
    if (MainActivity.version.equals("P2")){
      P2StatsPainter.draw();
    } else if (MainActivity.version.equals("Santa")) {
      SantaStatsPainter.draw();
    }
  }
  
  public static void drawHearts(int hearts) {
    for (int j = 0; j < 4; j++) {
      if (j < hearts)
        drawSpriteAt("full_heart", j*8, 8);
      else
        drawSpriteAt("empty_heart", j*8, 8);
    }
  }
  
  public static void drawHeartScreen(String screenTitle, int variable, String emptySprite, String fullSprite) {
    drawSpriteAt(screenTitle, 0, 0);
    for (int i = 0; i < 4; i++) {
      if (i < variable)
        drawSpriteAt(fullSprite, i*8, 8);
      else {
        if (emptySprite == null)
          drawSquareAt(i*8+3, 11, 2);
        else
          drawSpriteAt(emptySprite, i*8, 8);
      }
    }
  }
}
