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
      newCharacter = evolutionChart[objectIndex][tier];
      object = objectsChart[objectIndex][tier];
      if (tier == 3) {
        SantaTama.santaObjects[objectIndex] = 1;
      }
    } else if (objectIndex == 3) {
      newCharacter = oldCharacter;
      SantaTama.companion = evolutionChart[objectIndex][tier];
      object = "santa_egg_hatching";
      if (tier == 3) {
        SantaTama.santaObjects[objectIndex] = 1;
      }
    } else if (objectIndex == 4) {
      newCharacter = "missy_santa";
      object = "perfume";
    } else if (objectIndex == 5) {
      newCharacter = "prank_santa";
      object = "poison";
    }
        
    SantaTama.santaness = Utils.sum(SantaTama.santaObjects);
    Printer.print("Tier = " + tier + ", Object index = " + objectIndex);
    Printer.append("\n" + oldCharacter + " evolved into " + newCharacter);
  }
  
  public static void evolve() {
    SantaTama.character = newCharacter;
    SantaGraphics.clearCharacterGraphics();
    SantaGraphics.loadCharacterGraphics(SantaTama.character);
    if (!SantaTama.companion.equals("")) {
      SantaGraphics.loadCompanionGraphics(SantaTama.companion);
    }
  }
}
