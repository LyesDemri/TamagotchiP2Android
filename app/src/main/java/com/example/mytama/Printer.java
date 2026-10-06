package com.example.mytama;

import android.app.AlertDialog;

public class Printer {
  static int msgCounter = 0;
  
  public static void print(Object text) {
    Printer.print(text, false);
  }
  
  public static void print(Object text, boolean count) {
    MainActivity.tv.setText("");
    if (count) {
      MainActivity.tv.setText("(" + msgCounter + ") ");
    }
    MainActivity.tv.append(String.valueOf(text));
    msgCounter++;
  }
  
  public static void append(Object text) {
    Printer.append(text,false);
  }
  
  public static void append(Object text, boolean count) {
    if (MainActivity.tv == null) {
      print(text);
      return;
    }
    if (count) {
      MainActivity.tv.append("(" + msgCounter + ") ");
    }
    MainActivity.tv.append(String.valueOf(text));
    msgCounter++;
  }
  
  public static void displayVars() {
    Tama.displayVars();
  }
  
  public static void log(Object text) {
    if (MainActivity.debugMode == 1 || Tama.name != null && Tama.name.startsWith("DEBUG")) {
      append(text, true);
    }
  }
  
  public static void logPrint(Object text) {
    if (MainActivity.debugMode == 1 || Tama.name != null && Tama.name.startsWith("DEBUG")) {
      print(text, false);
    }
  }
  
  public static void logAppend(Object text) {
    if (MainActivity.debugMode == 1 || Tama.name != null && Tama.name.startsWith("DEBUG")) {
      append(text, false);
    }
  }
  
  public static void alert(String txt) {
    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.context);
    builder.setMessage(txt).setTitle("MyTama error");
    AlertDialog dialog = builder.create();
    dialog.show();
  }
}
