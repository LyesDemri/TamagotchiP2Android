package com.example.mytama;

public class P2Game {
  static public int secretNumber;
  static public int givenNumber;
  static public int round=0;
  static public int score=0;
  static public String answer;
  
  public static void handleWinLoss(){
    if (score >= 3) {
      Sounds.playSound("good_sound");
      MainActivity.state = "happy";
      Animations.animation_counter = 8;
      if (Tama.happy == 4 
         && Tama.stomach == 4
         && (!P2Tama.dirty)
         && P2Tama.idealWeight == Tama.weight) {
        Tama.hghlp++;
        Tama.hphlp++;
        P2Tama.pp++;
      }
      Tama.happy = Math.min(Tama.happy + 1, 4);
      Tama.timeSinceHappyChanged = 0;
      Tama.timeSinceBored = 0;
    } else {
      MainActivity.state = "unhappy";
      Animations.animation_counter = 8;
    }
    Tama.weight = Math.max(Tama.weight - 1, P2Tama.idealWeight);
  }
  
  public static void endRound() {
    //on passe a l'animation happy ou unhappy
    if ((P2Game.answer == "higher" && P2Game.secretNumber > P2Game.givenNumber)||
        (P2Game.answer == "lower" && P2Game.secretNumber < P2Game.givenNumber)) {
      MainActivity.state = "happy";
      P2Game.score += 1;
    } else
      MainActivity.state = "unhappy";
    P2Game.round++;
    if (P2Game.round<5) {
      MainActivity.oldState = "playing";
      Utils.generateGameNumbers();
    } else {
      MainActivity.oldState = "final game results";
      Animations.oldAnimationCounter = 5;
    }
    MainActivity.myRunnable.j = -1;
    Animations.animation_counter = 8;
    MainActivity.even = 0;
  }
}
