package com.example.mytama;

public class MenuButtonB {
  public static void handle() {
    if (MainActivity.menu_index==1) {
      Tama.inputName = Tama.name;
      Tama.reset();
    }
    if (MainActivity.menu_index==2) {
      MainActivity.state = "version_select_screen";
    } else if (MainActivity.menu_index==3)
      MainActivity.state = "tama_select_screen";
    else if (MainActivity.menu_index==4)
      MainActivity.displayVariables=!MainActivity.displayVariables;
    else if (MainActivity.menu_index == 5) {
      Updater.skipDuration(60);
    } else if (MainActivity.menu_index == 6) {
      Updater.skipDuration(300);
    } else if (MainActivity.menu_index == 7) {
      Updater.skipDuration(3600);
    }
  }
}
