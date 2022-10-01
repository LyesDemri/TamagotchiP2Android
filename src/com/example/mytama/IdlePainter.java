package com.example.mytama;

import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.Matrix;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;

public class IdlePainter 
{
  public static void draw()
  {
    int x = MainActivity.x;
    int y = MainActivity.y;
    int W = MainActivity.W;
    int H = MainActivity.H;
    int offsetX=(int)(MainActivity.surfW/2-320/2);
    int offsetY=(int)(MainActivity.surfH/2-160/2);
    
    if (MainActivity.sick)
    {
      if (MainActivity.sleeping)
      {
        MainActivity.canvas.drawBitmap(MainActivity.graphics.sleep[MainActivity.even],null, new Rect((16-W/2)*10+offsetX,y*10+offsetY,(16+W/2)*10+offsetX,(y+H)*10+offsetY),MainActivity.paint);
        MainActivity.canvas.drawBitmap(MainActivity.graphics.Z[MainActivity.even],null, new Rect((16+W/2)*10+offsetX,y*10+offsetY,(16+W/2+8)*10+offsetX,(y+8)*10+offsetY),MainActivity.paint);
      }
      else
      {
        MainActivity.canvas.drawBitmap(MainActivity.graphics.sick[MainActivity.even],null, new Rect((16-W/2)*10+offsetX,y*10+offsetY,(16+W/2)*10+offsetX,(y+H)*10+offsetY),MainActivity.paint);
      }
      MainActivity.canvas.drawBitmap(MainActivity.graphics.skull,null, new Rect(240+offsetX,0+offsetY,320+offsetX,80+offsetY),MainActivity.paint);
    }
    else
    {
      if (MainActivity.lightsOn)
      {
        if (MainActivity.sleeping)
        {
          MainActivity.canvas.drawBitmap(MainActivity.graphics.sleep[MainActivity.even],null, new Rect((16-W/2)*10+offsetX,y*10+offsetY,(16+W/2)*10+offsetX,(y+H)*10+offsetY),MainActivity.paint);
          MainActivity.canvas.drawBitmap(MainActivity.graphics.Z[MainActivity.even],null, new Rect((16+W/2)*10+offsetX,y*10+offsetY,(16+W/2+8)*10+offsetX,(y+8)*10+offsetY),MainActivity.paint);
        }
        else
        {
          //si c'est un perso qui marche:
          if ((MainActivity.character.equals("Babytchi"))||
              (MainActivity.character.equals("Tonmarutchi"))||
              (MainActivity.character.equals("Hashitamatchi"))||
              (MainActivity.character.equals("Pochitchi"))||
              (MainActivity.character.equals("Zuccitchi"))||
              (MainActivity.character.equals("Hashizotchi"))||
              (MainActivity.character.equals("Takotchi"))||
              (MainActivity.character.equals("Kusatchi"))
             )
          {
            //pour changer de sprite chaque 2 top d'horloge
            double even2=Math.floor(((MainActivity.t*2)%4)/2);
            
            if (MainActivity.myRunnable.i==0 || MainActivity.myRunnable.i==15)
            {
                //si le tamagotchi est sur les bords, on le force à quitter le bord
                if (x==0)
                  MainActivity.xIncrement=2;
                else if (x==(32-W))
                  MainActivity.xIncrement=-2;
                else    //sinon, on genere un increment aleatoire a gauche ou a droite
                  MainActivity.xIncrement=(int)((Math.round(Math.random()))*4-2);
                x=x+MainActivity.xIncrement;
            }
                
            if (MainActivity.xIncrement<0)
              MainActivity.canvas.drawBitmap(MainActivity.graphics.idle[(int)even2],null, new Rect(x*10+offsetX,y*10+offsetY,(x+W)*10+offsetX,(y+H)*10+offsetY),MainActivity.paint);
            else
              MainActivity.canvas.drawBitmap(flip(MainActivity.graphics.idle[(int)even2]),null, new Rect(x*10+offsetX,y*10+offsetY,(x+W)*10+offsetX,(y+H)*10+offsetY),MainActivity.paint);
          }
          else
            MainActivity.canvas.drawBitmap(MainActivity.graphics.idle[MainActivity.even],null, new Rect(x*10+offsetX,y*10+offsetY,(x+W)*10+offsetX,(y+H)*10+offsetY),MainActivity.paint);
        }
      }
    }
    if (MainActivity.dirty)
      MainActivity.canvas.drawBitmap(MainActivity.graphics.poop[MainActivity.even],null, new Rect(240+offsetX,80+offsetY,320+offsetX,160+offsetY),MainActivity.paint);
    MainActivity.x=x;
    MainActivity.y=y;
    MainActivity.W=W;
    MainActivity.H=H;
  }

  public static Bitmap flip(Bitmap d) 
  { 
    Matrix m = new Matrix();
    m.preScale(-1, 1);
    Bitmap src = d;
    Bitmap dst = Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), m, false);
    dst.setDensity(DisplayMetrics.DENSITY_DEFAULT);
    return dst;
  }
}