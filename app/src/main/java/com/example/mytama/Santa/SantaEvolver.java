package com.example.mytama;

public class SantaEvolver {
  static String newCharacter = "";
  static String object = "";
  public static String oldCharacter = "";                           
  
  public static void determineEvolution(int objectIndex){
    //evolution uses an evolution matrix:
    //lines = objects, columns = tiers
    //second column is duplicated such that "tiers" 2 and 3 give the same character 
    //(see here:https://gotchi-garden.blogspot.com/p/santaclautchi-growth-character-chart.html)
    String[][] evolutionChart = {{"snowboard_santa", "magician_santa", "magician_santa", "hat_santa"},
                                 {"sailor_santa",    "penguin_santa",  "penguin_santa",  "classic_santa"},
                                 {"scholar_santa",   "skate_santa",    "skate_santa",    "sleigh_santa"},
                                 {"tonanyorotchi",   "tonatakotchi",   "tonatakotchi",   "rednosetchi"}};
    
    String[][] objectsChart = {{"bonnet",       "magician_hat", "magician_hat", "santa_hat"},
                               {"sailor_cloth", "penguin_suit", "penguin_suit", "santa_cloth"},
                               {"pencil",       "skateboard",   "skateboard",   "sleigh"}};
                            
    int[][] speeds = {{2, 2, 2, 3},    //character speeds
                      {1, 2, 2, 4},
                      {1, 3, 3, 5}};    
    
    
    int tier = Math.min(SantaTama.tier, 3);
    tier = Math.max(tier, 0);
    oldCharacter = SantaTama.character; // need it for transformation animation.
                               
    if (objectIndex <= 2) {
      //we're using a yarn ball, wrapping paper, or a chisel
      if (tier < 3) {
        //for normal transformations, just look up the tables
        SantaTama.character = evolutionChart[objectIndex][tier];
        object = objectsChart[objectIndex][tier];
        SantaTama.characterSpeed = speeds[objectIndex][tier];
      } else {
        //santa evolutions need to happen in a specific order
        if (objectIndex == 0){
          // any character can evolve into hat santa
          SantaTama.character = "hat_santa";
          object = "santa_hat";
          SantaTama.characterSpeed = 3;
          SantaTama.santaObjects[objectIndex] = 1;
        } else if (objectIndex == 1) {
          // you have to be hat santa to become classic santa
          // otherwise you revert to penguin santa
          if (oldCharacter.equals("hat_santa") || oldCharacter.equals("classic_santa") || oldCharacter.equals("sleigh_santa") || oldCharacter.equals("missy_santa")) {
            SantaTama.character = "classic_santa";
            object = "santa_cloth";
            SantaTama.characterSpeed = 4;
            SantaTama.santaObjects[objectIndex] = 1;
          } else {
            SantaTama.character = "penguin_santa";
            object = "penguin_suit";
            SantaTama.characterSpeed = 2;
          }
        } else if (objectIndex == 2) {
          // you have to be classic santa to become sleigh santa
          // otherwise you revert to skate santa
          if (oldCharacter.equals("classic_santa") || oldCharacter.equals("sleigh_santa") || oldCharacter.equals("missy_santa")) {
            SantaTama.character = "sleigh_santa";
            object = "sleigh";
            SantaTama.characterSpeed = 5;
            SantaTama.santaObjects[objectIndex] = 1;
          } else {
            SantaTama.character = "skate_santa";
            object = "skateboard";
            SantaTama.characterSpeed = 3;
          }
        }
      }
    } else if (objectIndex == 3) {
      //if we're receiving a companion, character stays the same
      //we now just have a companion
      SantaTama.character = oldCharacter;
      object = "santa_egg_hatching"; // for animation
      if (tier < 3) {
        SantaTama.companion = evolutionChart[objectIndex][tier];
        } else {
        if (oldCharacter.equals("sleigh_santa") || oldCharacter.equals("missy_santa") || oldCharacter.equals("prank_santa")) {
          SantaTama.companion = "rednosetchi";
          SantaTama.santaObjects[objectIndex] = 1;
        } else {
          SantaTama.companion = "tonatakotchi";
        }
      }
    } else if (objectIndex == 4) {
      SantaTama.character = "missy_santa";
      object = "perfume";
      SantaTama.characterSpeed = 6;
    } else if (objectIndex == 5) {
      SantaTama.character = "prank_santa";
      object = "poison";
      SantaTama.characterSpeed = 6;
    }
                               
    SantaTama.santaness = Utils.sum(SantaTama.santaObjects);
                               
  }
  
  public static void evolve() {
    SantaGraphics.clearCharacterGraphics();
    SantaGraphics.loadCharacterGraphics();
    if (!SantaTama.companion.equals("")) {
      SantaGraphics.loadCompanionGraphics();
    }
  }
}
