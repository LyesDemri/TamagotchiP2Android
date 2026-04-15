package com.example.mytama;

import java.io.File;

public class Printer {
  static int msgCounter = 0;
  public static void print(Object text) {
    MainActivity.tv.setText("(" + msgCounter + ")" + String.valueOf(text));
    msgCounter++;
  }
  
  public static void append(Object text) {
    MainActivity.tv.append("(" + msgCounter + ") " + String.valueOf(text));
    msgCounter++;
  }
  
  public static void displayVars() {
    Tama.displayVars();
  }
  
  public static void log(Object text){
    if (MainActivity.debugMode == 1){
      append(text);
    }
  }
}
