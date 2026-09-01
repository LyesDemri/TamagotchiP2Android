package com.example.mytama;

public class SantaIdlePainter extends SantaPainter {
  static boolean goingBack = false;
  static int jumpCtr = 0;
  public static void draw() {
    int x = Tama.x, y = Tama.y;
    int W = Tama.W, H = Tama.H;
    int xIncrement = Tama.xIncrement;
    try {
      if (Tama.sleeping) {
        if ((TimeWizard.getTamagotchiDay() == 24 || TimeWizard.getTamagotchiDay() == 25) && TimeWizard.getTamagotchiMonth() == 12) {
          drawSpriteAt("flying_in_sky_" + (MainActivity.even + 1), 0, 0);
        } else {
          drawSpriteAt("santaclautchi_sleeping", 0, 0);
          drawSpriteAt("z" + (MainActivity.even + 1) + "dark", 24, 0);
        }
      } else {
        if (SantaTama.sulking) {
          drawSpriteAt(Tama.character + "_sulking", 8, 0);
          drawSpriteAt("sulking_bubble", 24 + 8*MainActivity.even, 0);
        } else if (SantaTama.companion.equals("")){
          if (MainActivity.myRunnable.i == 0 || MainActivity.myRunnable.i == 13) {
            //if santa is on the edge of the screen, turn around
            if ((x == 0 || x == 32 - W) && jumpCtr == 0) {
              goingBack = false;
              jumpCtr = 5;
            } else if ((x == 2) && !goingBack && xIncrement < 0) {
              xIncrement = -xIncrement;
              goingBack = true;
            } else if ((x == 30 - W) && !goingBack && xIncrement > 0) {
              xIncrement = -xIncrement;
              goingBack = true;
            } else if ((x == 6 && xIncrement > 0 || x == 10 && xIncrement < 0) && goingBack) {
              xIncrement = -xIncrement;
            }
            if (jumpCtr == 0)
              x = x + xIncrement;
            else {
              jumpCtr--;
              if (jumpCtr == 0){
                xIncrement = -xIncrement;
                x = x + xIncrement;
              }
            }
          }       
          if (xIncrement < 0) {
            if (jumpCtr == 4 || jumpCtr == 2)
              drawSpriteAt(SantaGraphics.hashMap.get(Tama.character+"_happy"), x, y);
            else
              drawSpriteAt(SantaGraphics.hashMap.get(Tama.character+"_idle_"+(MainActivity.even + 1)), x, y);
          } else {
            if (jumpCtr == 4 || jumpCtr == 2)
              drawSpriteAt(flip(SantaGraphics.hashMap.get(Tama.character+"_happy")), x, y);
            else
              drawSpriteAt(flip(SantaGraphics.hashMap.get(Tama.character+"_idle_"+(MainActivity.even+1))), x, y);
          }
        } else {
          // if character has a companion
          Printer.print("x = " + x);
          if (MainActivity.myRunnable.i == 0 || MainActivity.myRunnable.i == 13) {
            x--;
            if (x <= -16) {
              x = 48;
            }
          }
          drawSpriteAt(SantaTama.character + "_idle_" + (MainActivity.even + 1), x, y);
          drawSpriteAt(SantaTama.companion + "_idle_" + (MainActivity.even + 1), x-16, y);
        }
      }
    } catch (Exception e) {
      Printer.log("Error in SantaIdlePainter");
      Printer.log(e.getMessage());
    }
    Tama.x = x; Tama.y = y;
    Tama.W = W; Tama.H = H;
    Tama.xIncrement = xIncrement;
  }
  
}