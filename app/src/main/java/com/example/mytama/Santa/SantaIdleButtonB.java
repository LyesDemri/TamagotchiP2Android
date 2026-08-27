package com.example.mytama;

public class SantaIdleButtonB extends IdleButtonB {
  public static void handle() {
    if (MainActivity.icon_list[MainActivity.icon_number] == "Status") {
      MainActivity.state = "StatScreen0";
    } else if (MainActivity.icon_list[MainActivity.icon_number] == "Food" && Tama.isAlive) {
      if (!Tama.sleeping) {
        if (SantaTama.sulking) {
          SantaSulkingPainter.prepareAnimation();
          MainActivity.state = "sulking";
        } else if (!SantaTama.left) {
          MainActivity.state = "food choice";
          MainActivity.food_index = 0;
          MainActivity.myRunnable.j = 0;
        }
      }
    } else if (MainActivity.icon_list[MainActivity.icon_number] == "Game" && Tama.isAlive) {
      if (!Tama.sleeping) {
        if (SantaTama.sulking) {
          SantaSulkingPainter.prepareAnimation();
          MainActivity.state = "sulking";
        } else if (!SantaTama.left) {
          SantaGame.selectedChimney = 0;
          MainActivity.state = "game intro screen";
          Sounds.playSound("game_begin");
          MainActivity.myRunnable.k = 0;
        }
      }
    } else if (MainActivity.icon_list[MainActivity.icon_number] == "Present" && Tama.isAlive) {
      if (!Tama.sleeping) {
        if (SantaTama.sulking) {
          SantaTama.sulking = false;
          SantaTama.timeSinceSulking = 0;
          SantaSulkingPainter.prepareAnimation();
          MainActivity.state = "letter";
        } else {
          BagPainter.objectIndex = 0;
          MainActivity.state = "bag";
        }
      }
    } else if (MainActivity.icon_list[MainActivity.icon_number] == "Super Kuchipatchi" && Tama.isAlive && !SantaTama.sulking) {
      if (!Tama.sleeping) {
        SuperKuchipatchiPainter.initializeAnimation("");
        MainActivity.state = "super kuchipatchi";
      }
    } else if (MainActivity.icon_list[MainActivity.icon_number] == "Advent Calendar") {
      MainActivity.state = "advent calendar";
    } else if (MainActivity.icon_list[MainActivity.icon_number] == "Tama TV"){
      MainActivity.state = "tama tv";
    } else if (MainActivity.icon_list[MainActivity.icon_number] == "Menu") {
      //Menu:
      if (MainActivity.menu_index==0) {
        MainActivity.state = "Menu";
        MainActivity.menu_index += 1;
        MainActivity.tv.setText(MainActivity.menu_list[MainActivity.menu_index]);
      }
    } else if (MainActivity.icon_number == 0)
      MainActivity.state = "clock";
  }
}
