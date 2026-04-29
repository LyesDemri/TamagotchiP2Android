package com.example.mytama;

public class ResetScreen {
  public static void handle(float x, float y){
    MainActivity.vibrator.vibrate(50);
    int line = (int)Math.floor((y - ResetScreenPainter.keyboardHeight)/ResetScreenPainter.letterHeight);
    int col = (int)Math.floor(x/ResetScreenPainter.letterWidth);
    int letterIndex = line*10 + col;
    if (line >= 0 && line < 4)
      Tama.inputName += String.valueOf(ResetScreenPainter.chars.charAt(letterIndex));
    else {
      //handle "Back" click
      if (line == 4 && col <= 5 ) {
        //if no name was input, go back to main screen
        if (Tama.inputName.length() == 0) {
          if (!Tama.name.equals("")) {
            MainActivity.state = "idle";
            MainActivity.menu_index = 0;
          } else {
            Printer.print("Please name your first tama!", false);
          }
        } //else, remove last letter 
        else {
          Tama.inputName = Tama.inputName.substring(0,Tama.inputName.length()-1);
        }
      }
      //handle "Done" click
      if (line == 4 && col > 5) {
        if (!Tama.inputName.equals("")) {
          MainActivity.state = "idle";
          MainActivity.menu_index = 0;
          Tama.name = Tama.inputName;
          Tama.reset();
        } else {
          Printer.print("You haven't entered a name!", false);
        }
      }
    }
  }
}
