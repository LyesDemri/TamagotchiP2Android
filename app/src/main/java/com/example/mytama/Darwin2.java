package com.example.mytama;

import com.example.mytama.MainActivity;
import java.util.HashMap;

public class Darwin2 {
  
  static HashMap<String,int[]> data;
  
  static public void evolve() {
    try {
    //y coordinate is 0 for all tamas except Babytchi
    //the y entry should be removed;
    data = new HashMap<String,int[]>();
    data.put("tonmarutchi", new int[]{55*60, 55*60, 3*3600, 0, (int)(5.5*3600), 20, 9, 0, 10, 1});
    data.put("tongaritchi", new int[]{80*60, 80*60, 3*3600, 0, 6*3600,21,9,0,20,0});
    data.put("hashitamatchi", new int[]{80*60, 80*60, 3*3600, 0, 6*3600,21,9,0,20,1});
    data.put("mimitchi", new int[]{81*60, 91*60, 6*3600, 0, 24*3600,22,8,0,30,0});
    data.put("pochitchi", new int[]{81*60, 91*60, 5*3600, 0, 18*3600,22,8,0,30,1});
    data.put("zuccitchi", new int[]{70*60, 80*60, 5*3600, 0, 6*3600,23,11,0,30,1});
    data.put("hashizotchi", new int[]{70*60, 80*60, 3*3600, 0, 6*3600,22,10,0,30,1});
    data.put("takotchi", new int[]{55*60, 55*60, 5*3600, 0, 1*3600,23,8,0,20,0});
    data.put("kusatchi", new int[]{40*60, 40*60, (int)1.5*3600, 0, (int)5.5*3600, 20, 10, 0, 20, 1});
    data.put("zatchi", new int[]{55*60, 55*60, 5*3600, 0, 1*3600, 23, 8, 0, 20, 0});
    
    int DM = P2Tama.disciplineMistakes;
    int CM = P2Tama.careMisses;
    
    if (Tama.character.equals("babytchi"))
      evolveInto("tonmarutchi");
    else if (Tama.character.equals("tonmarutchi")) {
      if (CM < 3){
        evolveInto("tongaritchi");
      } else {
        evolveInto("hashitamatchi");
      }
      if (DM < 2){
        P2Tama.discipline = 2;
        P2Tama.dcp = 18*3600;
        P2Tama.superTeen = true;
      } else {
        P2Tama.discipline = 0;
        P2Tama.dcp = 9*3600;
        P2Tama.superTeen = false;
      }
    } else if (Tama.character.equals("tongaritchi")) {
      if (P2Tama.superTeen) {
        if (CM < 3) {
          if (DM == 0) {
            evolveInto("mametchi");
          } else if (DM == 1) {
            evolveInto("pochitchi");
          } else {
            evolveInto("maskutchi");
          }
        } else {
          if (DM < 2) {
            evolveInto("hashizoutchi");
          } else if (DM < 4) {
            evolveInto("kusatchi");
          } else{
            evolveInto("takotchi");
          }
        }
      } else {
        if (CM < 4) {
          evolveInto("maskutchi");
        } else {
          if (DM <= 7) {
            evolveInto("kusatchi");
          } else {
            evolveInto("takotchi");
          }
        }
      }
    } else if (Tama.character.equals("hashitamatchi")) {
      if (P2Tama.superTeen) {
        if (DM < 2) {
          evolveInto("hashizoutchi");
        } else if (DM == 2) {
          evolveInto("kusatchi");
        } else {
          evolveInto("takotchi");
        }
      } else {
        if (DM <= 5) {
          evolveInto("kusatchi");
        } else {
          evolveInto("takotchi");
        }
      }
    } else if (Tama.character.equals("zuccitchi") && P2Tama.superTeen) {
      evolveInto("zatchi");
    } else {
      Printer.log("Error: unknown character");
    }

    TimeWizard.computeSleepWakeTimes(Tama.sleepingHour, Tama.wakingHour);
    Tama.timeSinceHungryChanged = 0;
    Tama.timeSinceHappyChanged = 0;
    P2Tama.tslp = 0;
    P2Tama.tfdc = Tama.t + P2Tama.dcp/2;
    P2Tama.weight = Math.max(Tama.weight, P2Tama.idealWeight);
    Graphics.clearCharacterGraphics();
    Graphics.loadCharacterGraphics(MainActivity.context, Tama.character);
    Tama.x = 16 - Tama.W/2;
    Sounds.playSound("evolve_sound");
    Utils.notifyUser();
    } catch (Exception e) {
      Sounds.playSound("bad_sound");
      Printer.log("Error evolving character");
      Printer.log(e);
    }
  }
  
  static void evolveInto(String character) {
    Tama.character = character;
    Tama.hghlp = data.get(character)[0];
    Tama.hphlp = data.get(character)[1];
    P2Tama.pp = data.get(character)[2];
    P2Tama.tslp = data.get(character)[3]; //useless. remove.
    P2Tama.dcp = data.get(Tama.character)[4];
    Tama.sleepingHour = data.get(character)[5];
    Tama.wakingHour = data.get(character)[6];
    Tama.y = data.get(character)[7]; //y coordinate is 0 for all tamas except Babytchi, so this should be removed
    P2Tama.idealWeight = data.get(character)[8];
    P2Tama.walks = (data.get(character)[9]==1);
  }
}