package com.example.mytama;

public class P2FuturePredictor {
  
  String[] events = {"timeToBeHungry", "timeToBeBored", "timeToBeSickFromAge", "timeToBeSickFromDirt", "timeToBeSickFromWeight", 
      "timeToSleep", "timeToDiscipline", "timeToEvolve", "timeToDieFromHungerCM", "timeToDieFromHappyCM", 
      "timeToDieFromLightsCM", "timeToDieFromSicknessCM", "timeToDieFromDisciplineCM", "timeToDieFromSickness", "timeToDieFromAge",
    "timeToWake", "timeToDieFromHunger", "timeToDieFromBoredom"};
  
  public static double calculateNextCallTime(){
    //Events that cause a beep:
    double timeToBeHungry = (Tama.stomach > 0 && !Tama.sleeping) ? (Tama.stomach-1)*Tama.hghlp + (Tama.hghlp-Tama.timeSinceHungryChanged) : Double.POSITIVE_INFINITY;
    double timeToBeBored = (Tama.happy > 0 && !Tama.sleeping) ? (Tama.happy-1)*Tama.hphlp + (Tama.hphlp-Tama.timeSinceHappyChanged) : Double.POSITIVE_INFINITY;
    double timeToBeSickFromAge = P2Tama.ttgsfa - Tama.t;
    double timeToBeSickFromDirt = (P2Tama.dirty) ? (12*3600 - P2Tama.tsd) : (P2Tama.pp - P2Tama.tslp + 12*3600);
    if (!P2Tama.dirty && P2Tama.sleeping) timeToBeSickFromDirt += P2Tama.timeToWake;
    if (Tama.character.equals("babytchi")) timeToBeSickFromDirt = Double.POSITIVE_INFINITY;
    
    double diff = Tama.weight - P2Tama.idealWeight;
    double timeToBeSickFromWeight;
    if (diff > 0) timeToBeSickFromWeight = (int)(Math.random()*(100 - diff)*3600);
    else timeToBeSickFromWeight = Double.POSITIVE_INFINITY;
    
    double timeToSleep;
    if (Tama.t < 2400) timeToSleep = 2400 - Tama.t;
    else if (Tama.t < 65*60) timeToSleep = Double.POSITIVE_INFINITY;
    else timeToSleep = (!Tama.sleeping) ? Tama.timeToSleep - Tama.t : Double.POSITIVE_INFINITY;
    
    double timeToWake;
    if (Tama.t < 2400) timeToWake = 2700 - Tama.t;
    else if (Tama.t < 65*60) timeToWake = Double.POSITIVE_INFINITY;
    else timeToWake = (Tama.sleeping) ? Tama.timeToWake - Tama.t : Double.POSITIVE_INFINITY;
    
    double timeToDiscipline = P2Tama.tfdc - Tama.t;
    if (Tama.t < 65*60) timeToDiscipline = Double.POSITIVE_INFINITY;
    
    double timeToEvolve;
    if (Tama.t < 65*60) timeToEvolve = 65*60 - Tama.t;
    else if (Tama.t < 2*24*3600) timeToEvolve = 2*24*3600 - Tama.t;
    else if (Tama.t < 5*24*3600) timeToEvolve = 5*24*3600 - Tama.t;
    else if (Tama.character.equals("zuccitchi") && Tama.t < 10*24*3600) timeToEvolve = 10*24*3600 - Tama.t;
    else {timeToEvolve = Double.POSITIVE_INFINITY;}
    
    //Events that cause death
    //1/ Having the final care Mistake
    double timeToDieFromHungerCM = Double.POSITIVE_INFINITY;
    double timeToDieFromHappyCM = Double.POSITIVE_INFINITY;
    double timeToDieFromLightsCM = Double.POSITIVE_INFINITY;
    double timeToDieFromSicknessCM = Double.POSITIVE_INFINITY;
    double timeToDieFromDisciplineCM = Double.POSITIVE_INFINITY;
        
    if (P2Tama.careMisses == 9)  {
      if (P2Tama.stomach == 0 && P2Tama.timeSinceHungry < 900 && !Tama.sleeping)
        timeToDieFromHungerCM = 900 - P2Tama.timeSinceHungry;
      if (P2Tama.happy == 0 && P2Tama.timeSinceBored < 900 && !Tama.sleeping)
        timeToDieFromHappyCM = 900 - P2Tama.timeSinceBored;
      if (P2Tama.sleeping && P2Tama.timeSinceNeedsLightsOff < 900)
        timeToDieFromLightsCM = 900 - P2Tama.timeSinceNeedsLightsOff;
      if (P2Tama.sick && P2Tama.timeSinceSick < 900 && !Tama.sleeping)
        timeToDieFromSicknessCM = 900 - P2Tama.timeSinceSick;
    }
    
    //2/ Having the final disciplineMistake
    if (P2Tama.disciplineMistakes == 9) {
      if (P2Tama.needsDiscipline && P2Tama.timeSinceNeedsDiscipline < 900 && !Tama.sleeping)
        timeToDieFromDisciplineCM = 900 - P2Tama.timeSinceSick;
    }
    
    //3/ Being sick for too long
    double timeToDieFromSickness = Double.POSITIVE_INFINITY;
    if (P2Tama.sick && !Tama.sleeping) timeToDieFromSickness = 12*3600 - P2Tama.timeSinceSick;
    //4/ Old age
    double timeToDieFromAge = 25*24*3600 - Tama.t;
    
    //5/ Staying hungry, or bored for too long (sick was already dealt with)
    double timeToDieFromHunger = (P2Tama.stomach == 0 && !P2Tama.sleeping) ? 12*3600 - P2Tama.timeSinceHungry : Double.POSITIVE_INFINITY;
    double timeToDieFromBoredom = (P2Tama.happy == 0 && !P2Tama.sleeping) ? 12*3600 - P2Tama.timeSinceBored : Double.POSITIVE_INFINITY;
        
    double[] values = {timeToBeHungry, timeToBeBored, timeToBeSickFromAge, timeToBeSickFromDirt, timeToBeSickFromWeight, 
      timeToSleep, timeToDiscipline, timeToEvolve, timeToDieFromHungerCM, timeToDieFromHappyCM, 
      timeToDieFromLightsCM, timeToDieFromSicknessCM, timeToDieFromDisciplineCM, timeToDieFromSickness, timeToDieFromAge,
    timeToWake, timeToDieFromHunger, timeToDieFromBoredom};
        
    double min = Utils.min(values);
    //int  argmin = Double.intValue(j);
    Printer.print("Next Event in: " + min);

    return min;
  }
}