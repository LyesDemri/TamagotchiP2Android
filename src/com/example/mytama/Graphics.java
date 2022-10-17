package com.example.mytama;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.content.Context;
import android.content.res.Resources;

public class Graphics 
{
  Context c;
  public Bitmap[] idle;
  public Bitmap happy;
  public Bitmap[] eat;
  public Bitmap[] no;
  public Bitmap[] arrows;
  public Bitmap[] hamburger_bmp;
  public Bitmap[] cake_bmp;
  public Bitmap[][] foods;
  public Bitmap face_icon;
  public Bitmap yr;
  public Bitmap scale;
  public Bitmap lb;
  public Bitmap[] nums;
  public Bitmap[] smallNums;
  public Bitmap disciplineScreen;
  public Bitmap happyWord;
  public Bitmap hungryWord;
  public Bitmap emptyHeart;
  public Bitmap fullHeart;
  public Bitmap skull;
  public Bitmap[] sick;
  public Bitmap[] poop;
  public Bitmap sun;
  public Bitmap[] cloud;
  public Bitmap[] unhappy;
  public Bitmap[] play;
  public Bitmap right;
  public Bitmap left;
  public Bitmap VS;
  public Bitmap[] sleep;
  public Bitmap[] Z;
  public Bitmap[] Zdark;
  public Bitmap[] egg;
  public Bitmap hatching;
  public Bitmap ufo;
  public Bitmap star1;
  public Bitmap star2;
  public Bitmap star3;
  public Bitmap bg;
  public Bitmap p2bg;
  public Bitmap foodIcon;
  public Bitmap lightsIcon;
  public Bitmap gameIcon;
  public Bitmap medicineIcon;
  public Bitmap toiletIcon;
  public Bitmap statusIcon;
  public Bitmap disciplineIcon;
  public Bitmap attentionIcon;
  public Bitmap black_tile;
  public Bitmap shower_tile;
  public Bitmap Pclock;
  public Bitmap Aclock;
  public Bitmap Mclock;
  public Bitmap emptyArrow;
  public Bitmap fullArrow;

  public Graphics(Context c)
  {
    this.c=c;
    //chargement des graphismes
    idle=new Bitmap[2];
    eat=new Bitmap[2];
    no=new Bitmap[2];
    arrows=new Bitmap[4];
    hamburger_bmp=new Bitmap[3];
    cake_bmp=new Bitmap[3];
    foods=new Bitmap[2][3];
    nums=new Bitmap[10];
    smallNums=new Bitmap[10];
    sick=new Bitmap[2];
    poop=new Bitmap[2];
    cloud=new Bitmap[2];
    unhappy=new Bitmap[2];
    play=new Bitmap[2];
    sleep=new Bitmap[2];
    Z=new Bitmap[2];
    Zdark=new Bitmap[2];
    egg=new Bitmap[2];
    
    arrows[0]=BitmapFactory.decodeResource(c.getResources(),R.drawable.Up_arrow);
    arrows[1]=BitmapFactory.decodeResource(c.getResources(),R.drawable.Down_arrow);
    arrows[2]=BitmapFactory.decodeResource(c.getResources(),R.drawable.Left_arrow);
    arrows[3]=BitmapFactory.decodeResource(c.getResources(),R.drawable.Right_arrow);
    hamburger_bmp[0]=BitmapFactory.decodeResource(c.getResources(),R.drawable.Hamburger_1);
    hamburger_bmp[1]=BitmapFactory.decodeResource(c.getResources(),R.drawable.Hamburger_2);
    hamburger_bmp[2]=BitmapFactory.decodeResource(c.getResources(),R.drawable.Hamburger_3);
    cake_bmp[0]=BitmapFactory.decodeResource(c.getResources(),R.drawable.Cake_1);
    cake_bmp[1]=BitmapFactory.decodeResource(c.getResources(),R.drawable.Cake_2);
    cake_bmp[2]=BitmapFactory.decodeResource(c.getResources(),R.drawable.Cake_3);
    foods[0]=hamburger_bmp;
    foods[1]=cake_bmp;
    face_icon=BitmapFactory.decodeResource(c.getResources(),R.drawable.face_icon);
    yr=BitmapFactory.decodeResource(c.getResources(),R.drawable.yr);
    scale=BitmapFactory.decodeResource(c.getResources(),R.drawable.scale_icon);
    lb=BitmapFactory.decodeResource(c.getResources(),R.drawable.lb);
    nums[0]=BitmapFactory.decodeResource(c.getResources(),R.drawable.num0);
    nums[1]=BitmapFactory.decodeResource(c.getResources(),R.drawable.num1);
    nums[2]=BitmapFactory.decodeResource(c.getResources(),R.drawable.num2);
    nums[3]=BitmapFactory.decodeResource(c.getResources(),R.drawable.num3);
    nums[4]=BitmapFactory.decodeResource(c.getResources(),R.drawable.num4);
    nums[5]=BitmapFactory.decodeResource(c.getResources(),R.drawable.num5);
    nums[6]=BitmapFactory.decodeResource(c.getResources(),R.drawable.num6);
    nums[7]=BitmapFactory.decodeResource(c.getResources(),R.drawable.num7);
    nums[8]=BitmapFactory.decodeResource(c.getResources(),R.drawable.num8);
    nums[9]=BitmapFactory.decodeResource(c.getResources(),R.drawable.num9);
    smallNums[0]=BitmapFactory.decodeResource(c.getResources(),R.drawable.num0small);
    smallNums[1]=BitmapFactory.decodeResource(c.getResources(),R.drawable.num1small);
    smallNums[2]=BitmapFactory.decodeResource(c.getResources(),R.drawable.num2small);
    smallNums[3]=BitmapFactory.decodeResource(c.getResources(),R.drawable.num3small);
    smallNums[4]=BitmapFactory.decodeResource(c.getResources(),R.drawable.num4small);
    smallNums[5]=BitmapFactory.decodeResource(c.getResources(),R.drawable.num5small);
    smallNums[6]=BitmapFactory.decodeResource(c.getResources(),R.drawable.num6small);
    smallNums[7]=BitmapFactory.decodeResource(c.getResources(),R.drawable.num7small);
    smallNums[8]=BitmapFactory.decodeResource(c.getResources(),R.drawable.num8small);
    smallNums[9]=BitmapFactory.decodeResource(c.getResources(),R.drawable.num9small);

    disciplineScreen=BitmapFactory.decodeResource(c.getResources(),R.drawable.Discipline_screen);
    happyWord=BitmapFactory.decodeResource(c.getResources(),R.drawable.Happy_word);
    hungryWord=BitmapFactory.decodeResource(c.getResources(),R.drawable.Hungry_word);
    emptyHeart=BitmapFactory.decodeResource(c.getResources(),R.drawable.Empty_heart);
    fullHeart=BitmapFactory.decodeResource(c.getResources(),R.drawable.Full_heart);
    skull=BitmapFactory.decodeResource(c.getResources(),R.drawable.Skull);
    poop[0]=BitmapFactory.decodeResource(c.getResources(),R.drawable.poop_1);
    poop[1]=BitmapFactory.decodeResource(c.getResources(),R.drawable.poop_2);
    sun=BitmapFactory.decodeResource(c.getResources(),R.drawable.Happy_sun);
    cloud[0]=BitmapFactory.decodeResource(c.getResources(),R.drawable.Unhappy_cloud_1);
    cloud[1]=BitmapFactory.decodeResource(c.getResources(),R.drawable.Unhappy_cloud_2);
    VS=BitmapFactory.decodeResource(c.getResources(),R.drawable.VS);
    Z[0]=BitmapFactory.decodeResource(c.getResources(),R.drawable.Z1);
    Z[1]=BitmapFactory.decodeResource(c.getResources(),R.drawable.Z2);
    Zdark[0]=BitmapFactory.decodeResource(c.getResources(),R.drawable.Z1Dark);
    Zdark[1]=BitmapFactory.decodeResource(c.getResources(),R.drawable.Z2Dark);
    egg[0]=BitmapFactory.decodeResource(c.getResources(),R.drawable.Egg_idle_1);
    egg[1]=BitmapFactory.decodeResource(c.getResources(),R.drawable.Egg_idle_2);
    hatching=BitmapFactory.decodeResource(c.getResources(),R.drawable.Hatching);
    ufo=BitmapFactory.decodeResource(c.getResources(),R.drawable.UFO);
    star1=BitmapFactory.decodeResource(c.getResources(),R.drawable.Star1);
    star2=BitmapFactory.decodeResource(c.getResources(),R.drawable.Star2);
    star3=BitmapFactory.decodeResource(c.getResources(),R.drawable.Star3);
    p2bg=BitmapFactory.decodeResource(c.getResources(),R.drawable.p2bg);
    bg=BitmapFactory.decodeResource(c.getResources(),R.drawable.mytama3);
    shower_tile = BitmapFactory.decodeResource(c.getResources(),R.drawable.shower);
    MainActivity.bgimgW=bg.getWidth()/30;
    MainActivity.bgimgH=bg.getHeight()/30;

    foodIcon=BitmapFactory.decodeResource(c.getResources(),R.drawable.Food_icon);
    lightsIcon=BitmapFactory.decodeResource(c.getResources(),R.drawable.Lights_icon);
    gameIcon=BitmapFactory.decodeResource(c.getResources(),R.drawable.Game_icon);
    medicineIcon=BitmapFactory.decodeResource(c.getResources(),R.drawable.Medicine_icon);
    toiletIcon=BitmapFactory.decodeResource(c.getResources(),R.drawable.Toilet_icon);
    statusIcon=BitmapFactory.decodeResource(c.getResources(),R.drawable.Stats_icon);
    disciplineIcon=BitmapFactory.decodeResource(c.getResources(),R.drawable.Training_icon);
    attentionIcon=BitmapFactory.decodeResource(c.getResources(),R.drawable.Attention_icon);

    black_tile = BitmapFactory.decodeResource(c.getResources(),R.drawable.black_tile);
    emptyArrow = BitmapFactory.decodeResource(c.getResources(),R.drawable.emptyArrow);
    fullArrow = BitmapFactory.decodeResource(c.getResources(),R.drawable.fullArrow);
    Pclock = BitmapFactory.decodeResource(c.getResources(),R.drawable.Pclock);
    Aclock = BitmapFactory.decodeResource(c.getResources(),R.drawable.Aclock);
    Mclock = BitmapFactory.decodeResource(c.getResources(),R.drawable.Mclock);
  }

  public static void loadCharacterGraphics(Context c, String character)
  {
    Resources r=c.getResources();
    String pkg=c.getPackageName();
    MainActivity.graphics.idle[0]=BitmapFactory.decodeResource(c.getResources(),r.getIdentifier(character+"_idle_1","drawable",pkg));
    //MainActivity.tv.setText("Height="+MainActivity.graphics.idle[0].getHeight());
    MainActivity.graphics.idle[1]=BitmapFactory.decodeResource(c.getResources(),r.getIdentifier(character+"_idle_2","drawable",pkg));
    MainActivity.graphics.eat[0]=BitmapFactory.decodeResource(c.getResources(),r.getIdentifier(character+"_eat_1","drawable",pkg));
    MainActivity.graphics.eat[1]=BitmapFactory.decodeResource(c.getResources(),r.getIdentifier(character+"_eat_2","drawable",pkg));
    MainActivity.graphics.no[0]=BitmapFactory.decodeResource(c.getResources(),r.getIdentifier(character+"_no_1","drawable",pkg));
    MainActivity.graphics.no[1]=BitmapFactory.decodeResource(c.getResources(),r.getIdentifier(character+"_no_2","drawable",pkg));
    MainActivity.graphics.sick[0]=BitmapFactory.decodeResource(c.getResources(),r.getIdentifier(character+"_sick_1","drawable",pkg));
    MainActivity.graphics.sick[1]=BitmapFactory.decodeResource(c.getResources(),r.getIdentifier(character+"_sick_2","drawable",pkg));
    MainActivity.graphics.happy=BitmapFactory.decodeResource(c.getResources(),r.getIdentifier(character+"_happy","drawable",pkg));
    MainActivity.graphics.unhappy[0]=BitmapFactory.decodeResource(c.getResources(),r.getIdentifier(character+"_unhappy_1","drawable",pkg));
    MainActivity.graphics.unhappy[1]=BitmapFactory.decodeResource(c.getResources(),r.getIdentifier(character+"_unhappy_2","drawable",pkg));
    MainActivity.graphics.play[0]=BitmapFactory.decodeResource(c.getResources(),r.getIdentifier(character+"_play_1","drawable",pkg));
    MainActivity.graphics.play[1]=BitmapFactory.decodeResource(c.getResources(),r.getIdentifier(character+"_play_2","drawable",pkg));
    MainActivity.graphics.right=BitmapFactory.decodeResource(c.getResources(),r.getIdentifier(character+"_right","drawable",pkg));
    MainActivity.graphics.left=BitmapFactory.decodeResource(c.getResources(),r.getIdentifier(character+"_left","drawable",pkg));
    MainActivity.graphics.sleep[0]=BitmapFactory.decodeResource(c.getResources(),r.getIdentifier(character+"_sleep_1","drawable",pkg));
    MainActivity.graphics.sleep[1]=BitmapFactory.decodeResource(c.getResources(),r.getIdentifier(character+"_sleep_2","drawable",pkg));
    
    MainActivity.W=MainActivity.graphics.idle[0].getWidth()/30;
    MainActivity.H=MainActivity.graphics.idle[0].getHeight()/30;
    if (MainActivity.character.equals("Babytchi")) {
      MainActivity.W=8;
      MainActivity.H=8;
    }
    else if (MainActivity.character.equals("Tonmarutchi")) {
      MainActivity.W=12;
      MainActivity.H=12;
    }
    else {
      MainActivity.W=16;
      MainActivity.H=16;
    }
  }
}