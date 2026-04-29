package com.example.mytama;

import java.io.File;

public class Printer {
  static int msgCounter = 0;
  public static void print(Object text, boolean count) {
    MainActivity.tv.setText("");
    if (count) {
      MainActivity.tv.setText("(" + msgCounter + ") ");
    }
    MainActivity.tv.append(String.valueOf(text));
    msgCounter++;
  }
  
  public static void append(Object text, boolean count) {
    MainActivity.tv.append("\n");
    if (count) {
      MainActivity.tv.append("(" + msgCounter + ") ");
    }
    MainActivity.tv.append(String.valueOf(text));
    msgCounter++;
  }
  
  public static void displayVars() {
    Tama.displayVars();
  }
  
  public static void log(Object text){
    if (MainActivity.debugMode == 1 || Tama.name.startsWith("DEBUG")) {
      append(text, true);
    }
  }
}
