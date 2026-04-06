package com.example.mytama;

public class DisciplineUpdater {
  public static void update() {
    //Avons nous atteint un discipline call?
    if (Tama.t == Tama.tfdc) {
      if (!Tama.sleeping) {
        Tama.needsDiscipline = true;
        Tama.timeSinceNeedsDiscipline = 0;
        Utils.notifyUser();
      }
      Tama.tfdc = Tama.tfdc + Tama.dcp;
    }
    //Doit on ajouter un care miss?
    if (Tama.needsDiscipline) {
      Tama.timeSinceNeedsDiscipline += 1;
      if (Tama.timeSinceNeedsDiscipline == 15*60) {
        Tama.careMisses += 1;
        Tama.timeSinceNeedsDiscipline = 0;
        Tama.needsDiscipline = false;
      }
    }
  }
}
