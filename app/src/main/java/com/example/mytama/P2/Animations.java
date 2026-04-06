package com.example.mytama;

public class Animations {
  static public int[] eatAnim;
  static public int[] foodAnim;
  static public int[][] foodAnimPositions;
  static public int[][] noFoodAnimPositions;
  static public int animation_counter=0;
  static public int oldAnimationCounter=0;

  
  public static void loadAnimations(){
   //Animation related stuff:
    foodAnimPositions=new int[7][2];
    foodAnimPositions[0]=new int[]{  0, 0};
    foodAnimPositions[1]=new int[]{  0, 8};
    foodAnimPositions[2]=new int[]{  0, 8};
    foodAnimPositions[3]=new int[]{  0, 8};
    foodAnimPositions[4]=new int[]{  0, 8};
    foodAnimPositions[5]=new int[]{  0, 8};
    foodAnimPositions[6]=new int[]{500,500};
    
    noFoodAnimPositions=new int[7][2];
    noFoodAnimPositions[0]=new int[]{0, 0};
    noFoodAnimPositions[1]=new int[]{0,8};
    noFoodAnimPositions[2]=new int[]{0,8};
    noFoodAnimPositions[3]=new int[]{0,8};
    noFoodAnimPositions[4]=new int[]{0,8};
    noFoodAnimPositions[5]=new int[]{0,8};
    noFoodAnimPositions[6]=new int[]{0,8};
    
    eatAnim=new int[]{1,1,2,1,2,1,2};
    foodAnim=new int[]{0,0,1,1,2,2,2};
  }
}
