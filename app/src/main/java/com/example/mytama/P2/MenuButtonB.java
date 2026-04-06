package com.example.mytama;

public class MenuButtonB {
  public static void handle() {
    if (MainActivity.menu_index==1) {
      Tama.inputName = Tama.name;
      Tama.reset();
    }
    if (MainActivity.menu_index==2) {
      MainActivity.state = "reset_screen";
      Printer.print("State was set to 'reset_screen'");
    } else if (MainActivity.menu_index==3)
      MainActivity.state = "tama_select_screen";
    else if (MainActivity.menu_index==4)
      MainActivity.displayVariables=!MainActivity.displayVariables;
    else if (MainActivity.menu_index==5) {
      for (int i = 0; i < 300; i++) {
        Printer.print("Fast forwarding");
        Updater.update();
        Printer.print("Done fast forwarding");
        Printer.append("\nt =" + Tama.t);
      }
    }
  }
}
