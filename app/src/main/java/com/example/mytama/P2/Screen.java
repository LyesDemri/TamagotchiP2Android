package com.example.mytama;

import android.content.Context;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.widget.LinearLayout;
import android.view.SurfaceView;
import android.view.View;
import com.example.mytama.MainActivity;
import android.graphics.PixelFormat;
import java.io.File;

class Screen extends SurfaceView implements SurfaceHolder.Callback {
  Context context;
  
  public static Screen screen;
  static public int surfW;
  static public int surfH;
  static public int bgx, bgy, bgW, bgH;
  static public int bgimgW;
  static public int bgimgH;
  public static int offsetX;
  public static int offsetY;
  static public int canvasWidth;
  static public int canvasHeight;
  
  //Screen class constructor:
  public Screen(Context context) {
    super(context);
    this.context = context;
    getHolder().addCallback(this);
    this.setOnTouchListener(new View.OnTouchListener(){
      @Override public boolean onTouch(View v, MotionEvent motionEvent) {
        if (motionEvent.getActionMasked()==MotionEvent.ACTION_DOWN) {
          handleTouch(motionEvent.getX(),motionEvent.getY());
        }
        return true;
      };
    });
  }
  
  //Init Screen
  public static void initScreen(Context c) {
    surfW=(int)(649*1.1);
    surfH=(int)(747*1.1)+20;
    offsetX=(int)(Screen.surfW/2-320/2);
    offsetY=(int)(Screen.surfH/2-160/2);
    screen = new Screen(c);
    screen.setLayoutParams(new LinearLayout.LayoutParams(surfW,surfH));
    screen.setZOrderOnTop(true);
    SurfaceHolder screenSurfaceHolder = screen.getHolder();
    screenSurfaceHolder.setFormat(PixelFormat.TRANSLUCENT);
  }

  public void handleTouch(float x, float y) {
    if (!MainActivity.state.equals("reset_screen") && !MainActivity.state.equals("tama_select_screen")) {
      if (y>700 && y<800) {
        if (x>200 && x<300)
          ButtonA.handle();
        else if (x>300 && x<400) 
          ButtonB.handle();
        else if (x>400 && x<600) 
          ButtonC.handle();
      }
    }
    else if (MainActivity.state.equals("reset_screen")){
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
              Printer.print("Please name your first tama!");
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
            Printer.print("You haven't entered a name!");
          }
        }
      }
    }
    else if (MainActivity.state.equals("tama_select_screen")) {
      MainActivity.vibrator.vibrate(50);
      int selectedLine = (int)(y/(Screen.canvasHeight/10));
      String[] tamaList = TamaSelectPainter.tamaList;
      if (x < Screen.canvasWidth * 0.9) {
        if (selectedLine < tamaList.length) {
          Tama.name = tamaList[selectedLine];
          try {
            DataSaverLoader.loadData();
          } catch (Exception e) {
            Printer.append("Screen: Error loading tama data: " + e.getMessage());
          }
          MainActivity.menu_index = 0;
          Printer.append(Tama.name);
        } else {
          MainActivity.state = "reset_screen";
        }
      } else {
        File file = new File(MainActivity.context.getFilesDir() + "/" + tamaList[selectedLine] + ".txt");

        if (!Tama.name.equals(tamaList[selectedLine])) {
          try{
            file.delete();
          } catch (Exception e) {
            Printer.append("Screen: Error erasing file: " + e.getMessage());
          }
          MainActivity.tv.setText("Deleted " + String.valueOf(file));
        } else {
         Printer.print("Can't delete this one"); 
         Printer.append("You're currently playing with it");
         Printer.append("Switch to another Tama first");
        }
      }   
    }
  }
  
  //These methods MUST be implemented
  public void surfaceCreated(SurfaceHolder surfaceHolder) {}
  public void surfaceDestroyed(SurfaceHolder surfaceHolder) {}
  public void surfaceChanged(SurfaceHolder surfaceHolder, int x, int y, int z){}
}