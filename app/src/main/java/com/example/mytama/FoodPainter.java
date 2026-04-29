package com.example.mytama;

public class FoodPainter extends Painter {
  public static void draw() {
    if (MainActivity.version.equals("P2")) {
      P2FoodPainter.draw();
    } else if (MainActivity.version.equals("Santa")) {
      SantaFoodPainter.draw();
    } else {
      Printer.print("Error in Food Painter",true);
      Printer.append("Unknown version" + MainActivity.version,true);
    }
  }
}
