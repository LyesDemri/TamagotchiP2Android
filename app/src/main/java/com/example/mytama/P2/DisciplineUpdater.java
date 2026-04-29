package com.example.mytama;

public class DisciplineUpdater {
  public static void update() {
    //Avons nous atteint un discipline call?
    if (Tama.t == P2Tama.tfdc) {
      if (!Tama.sleeping) {
        P2Tama.needsDiscipline = true;
        P2Tama.timeSinceNeedsDiscipline = 0;
        Utils.notifyUser();
      }
      P2Tama.tfdc = P2Tama.tfdc + P2Tama.dcp;
    }
    //Doit on ajouter un care miss?
    if (P2Tama.needsDiscipline) {
      P2Tama.timeSinceNeedsDiscipline += 1;
      if (P2Tama.timeSinceNeedsDiscipline == 15*60) {
        P2Tama.disciplineMistakes += 1;
        P2Tama.timeSinceNeedsDiscipline = 0;
        P2Tama.needsDiscipline = false;
      }
    }
  }
}
