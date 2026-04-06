package com.example.mytama;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class ResetScreenPainter extends Painter {
  public static int keyboardHeight;
  public static int letterWidth;
  public static int letterHeight;
  public static int textSize;
  public static Rect r;
  public static String chars;

  public static void draw() {
    canvas.drawColor(Color.argb(255,255,255,255));
    
    //define paint to write with:
    Paint p=new Paint();
    //p.setTextAlign(Paint.Align.CENTER);//might be a good idea to use this
    textSize = 50;
    p.setTextSize((float)textSize);
    p.setARGB(255,0,0,0);

    //Rectangle used for getTextBounds:
    r = new Rect();
    
    //offset between canvas and screen:
    int offsetX=(int)(canvas.getClipBounds().left);
    int offsetY=(int)(canvas.getClipBounds().top);
    
    //top of "keyboard"
    keyboardHeight = canvas.getHeight()/2;
    letterWidth = canvas.getWidth()/10;
    letterHeight = (int)((canvas.getHeight() - keyboardHeight)/5);
    
    //write "name your character":
    String s = "Name your character:";
    canvas.drawText(s, offsetX, offsetY + textSize, p);
    // compute text bounds:
    p.getTextBounds(s,0,s.length()-1,r);
    canvas.drawLine(0, textSize + r.bottom, canvas.getWidth(), textSize+r.bottom, p);
    
    //write character name:
    if (Tama.inputName == null)
      Tama.inputName = "";
    else
      canvas.drawText(Tama.inputName, offsetX, offsetY + canvas.getHeight()/4, p);

    //write alphabet:
    chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ.!?-0123456789";
    canvas.drawLine(0, keyboardHeight, canvas.getWidth(), keyboardHeight,p);
    
    for (int l=0; l<4; l++){
      p.getTextBounds("Q",0,1,r);   //compute bottom position
      //draw horizontal line
      canvas.drawLine(0,
                      offsetY + l*letterHeight + keyboardHeight + textSize + r.bottom ,
                      canvas.getWidth(),
                      offsetY + l*letterHeight + keyboardHeight + textSize + r.bottom,
                      p);
      for (int c=0; c<10; c++) {
        canvas.drawText(String.valueOf(chars.charAt(l*10+c)), 
                                     offsetX + c*letterWidth, 
                                     offsetY + l*letterHeight + keyboardHeight + textSize, 
                                     p);
        if (l==3) { // it's not necessary to draw vertical lines every time
          //draw vertical line
          canvas.drawLine(offsetX + c*letterWidth,
                          keyboardHeight,
                          offsetX + c*letterWidth,
                          offsetY + l*letterHeight + keyboardHeight + textSize + r.bottom,
                          p);
        }
      }
    }
    
    //write "Back" and "done":
    p.getTextBounds("<-Back",0,1,r);   //compute left position
    canvas.drawText("<-Back", 
                    0, 
                    offsetY + 4*letterHeight + keyboardHeight + textSize, 
                    p);
    p.getTextBounds("Done->",0,1,r);   //compute left position
    canvas.drawText("Done->", 
                     offsetX + canvas.getWidth()*3/4 - r.left/2, 
                     offsetY + 4*letterHeight + keyboardHeight + textSize, 
                     p);
  }
}