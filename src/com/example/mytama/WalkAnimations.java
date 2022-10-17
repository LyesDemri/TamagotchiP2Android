package com.example.mytama;

public class WalkAnimations {
  public static int i;
  public static int[] getStep(String character) {
    if (character.equals("BabyMothra")) {
      int[] x =     {10,9,8,7,6,5,4};
      int[] y =     { 1,0,0,0,0,0,0};
      int[] sprite ={ 0,1,2,0,1,2,0};
      int[] dir =   { 0,0,0,0,1,1,1};
      int[] values = {x[i],y[i],sprite[i]};
      i = (i+1)%7;
      return values;
    }
    else if (character.equals("Babytchi")){
      int[] x =     {10,9,8,7,6,5,4};
      int[] y =     { 1,0,0,0,0,0,0};
      int[] sprite ={ 0,1,2,0,1,2,0};
      int[] dir =   { 0,0,0,0,1,1,1};
      int[] values = {x[i],y[i],sprite[i]};
      i = (i+1)%7;
      return values;
    }
    else
      return new int[3];      
  }
}