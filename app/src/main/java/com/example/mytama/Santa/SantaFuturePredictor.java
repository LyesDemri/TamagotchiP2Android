package com.example.mytama;

public class SantaFuturePredictor {
    //this doesn't work well because Alarms aren't reliable
  public static double calculateNextCallTime() {
      //Events that cause a beep
      double timeToEat = (SantaTama.food > 0 && !SantaTama.sleeping) ? SantaTama.timeToEat : Double.POSITIVE_INFINITY;
      double timeToSnack = (SantaTama.snacks > 0 && !SantaTama.sleeping) ? SantaTama.timeToSnack : Double.POSITIVE_INFINITY;
      double timeToBeHungry = (SantaTama.stomach > 0 && !SantaTama.sleeping) ? (SantaTama.stomach - 1)*SantaTama.hghlp + SantaTama.ttlhungryh : Double.POSITIVE_INFINITY;
      double timeToBeBored = (SantaTama.happy > 0 && !SantaTama.sleeping) ? (SantaTama.happy - 1)*SantaTama.hphlp + SantaTama.ttlhappyh : Double.POSITIVE_INFINITY;
      
      
      double[] values = {timeToEat, timeToSnack, timeToBeBored};
      
      double min = Utils.min(values);
      return min;
  }
}