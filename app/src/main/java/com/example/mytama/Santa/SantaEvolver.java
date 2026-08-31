package com.example.mytama;

public class SantaEvolver {
  
  static String newCharacter = "";
  static String object = "";
  
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
    
    
    int tier = Math.min(SantaTama.tier, 3);
    tier = Math.max(tier, 0);
    String oldCharacter = SantaTama.character;
    if (objectIndex <= 2) {
      //we're using a yarn ball, wrapping paper, or a chisel
      newCharacter = evolutionChart[objectIndex][tier];
      object = objectsChart[objectIndex][tier];
      if (tier == 3) {
        //if we're receiving a Santa item, count it
        //also, the speed is now dependent on the received object
        SantaTama.santaObjects[objectIndex] = 1;
        SantaTama.characterSpeed = objectIndex + 3;
      } else {
        // if we're just receiving a simple object, the 
        //speed is 1 or 2
        SantaTama.characterSpeed = (tier == 0) ? 1 : 2;
      }
    } else if (objectIndex == 3) {
      //if we're receiving a companion, character stays the same
      //we now just have a companion
      newCharacter = oldCharacter;
      SantaTama.companion = evolutionChart[objectIndex][tier];
      object = "santa_egg_hatching"; // for animation
      if (tier == 3) {
        SantaTama.santaObjects[objectIndex] = 1;
      }
      //character speed isn't modified here for the companion
      //instead, the DistanceUpdater will just add 1 if we have
      //a companion.
    } else if (objectIndex == 4) {
      newCharacter = "missy_santa";
      object = "perfume";
      SantaTama.characterSpeed = 6;
    } else if (objectIndex == 5) {
      newCharacter = "prank_santa";
      object = "poison";
      SantaTama.characterSpeed = 6;
    }
        
    SantaTama.santaness = Utils.sum(SantaTama.santaObjects);
    Printer.print("Tier = " + tier + ", Object index = " + objectIndex);
    Printer.append("\n" + oldCharacter + " evolved into " + newCharacter);
  }
  
  public static void evolve() {
    SantaTama.character = newCharacter;
    SantaGraphics.clearCharacterGraphics();
    SantaGraphics.loadCharacterGraphics();
    if (!SantaTama.companion.equals("")) {
      SantaGraphics.loadCompanionGraphics();
    }
  }
}
