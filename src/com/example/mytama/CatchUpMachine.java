package com.example.mytama;

public class CatchUpMachine 
{
  public static void catchUp()
  {
    //-----Import des variables vers des variables locales-----//
    //(C'est juste pour que ça soit plus simple à utiliser)
    //on commence par recuperer les valeurs des variables a la fermeture
    int HgHUC=MainActivity.stomach;
    int HpHUC=MainActivity.happy;
    int HgH=HgHUC;
    int HpH=HpHUC;
    double TSHgC=MainActivity.timeSinceHungryChanged;
    double TSHpC=MainActivity.timeSinceHappyChanged;
    double TSH=MainActivity.timeSinceHungry;
    double TSB=MainActivity.timeSinceBored;
    double DFHgP=6*3600;
    double DFBP=6*3600;
    double CMP=900;
    int CMUC=MainActivity.careMisses;
    int CM = CMUC;
    boolean isAlive=MainActivity.isAlive; //aucun interet. si le joueur a ferme l'app pendant qu'il etait mort, cette question ne se pose pas
    boolean sleeping=MainActivity.sleeping;
    boolean lightsOn=MainActivity.lightsOn;
    boolean dirty=MainActivity.dirty;
    double TUC=MainActivity.t;
    double TSL=MainActivity.timeSinceLeft;
    double TTS;
    double TTW;
    double TTP;
    double TUO=TUC+TSL;
    double lastEventTime;
    
    int numEvents=MainActivity.eventsList.size();

    //For debugging purposes:
    MainActivity.tv.setText("Time Since Left: "+String.valueOf(MainActivity.timeSinceLeft)+"\n");
    MainActivity.tv.setText(MainActivity.tv.getText()+"Current Time: "+String.valueOf(TUO)+"\n");
    for (int i=0; i<numEvents;i++)
      MainActivity.tv.setText(MainActivity.tv.getText()+MainActivity.eventsList.get(i)+": "+MainActivity.eventsTimesList.get(i)+"\n");

    //-----Simulation jusqu'à l'instant actuel-----//
    int i=0;
    //Si aucun événement ne s'est produit:
    if (TUO<MainActivity.eventsTimesList.get(0))
    {
      MainActivity.tv.setText(MainActivity.tv.getText()+"Nothing happened");
    }
    //Si le tamagotchi est mort:
    else if (TUO>=MainActivity.eventsTimesList.get(numEvents-1))
    {
      MainActivity.tv.setText(MainActivity.tv.getText()+"Tamagotchi has died");
      MainActivity.isAlive=false;
    }
    //S'il y a eu des évènements
    else
    {
      String nextEvent;
      for (i=0; i<numEvents;i++)
      {
        //On vérifie s'il ne faut pas sortir de la boucle:
        //on sort de la boucle si TUO est plus petit que la date du prochain evenement
        if (TUO<MainActivity.eventsTimesList.get(i))
        {
          MainActivity.tv.setText(MainActivity.tv.getText()+"Nothing else happened\n");
          break;
        }
        
        if (i>0)
        {
          lastEventTime=MainActivity.eventsTimesList.get(i-1);
        }
        else
          lastEventTime=TUC;    // pas sûr de ça
        
        //Il faut mettre à jour les durées depuis le dernier évènement
        //Supposons que le tamagotchi vient de perdre un coeur juste avant que 
        //l'utilisateur ferme l'appli. Il reste par exemple 175 secs avant de
        //perdre un nouveau coeur. 
        //Si on ne fait pas ce qui suit, il restera toujours 175 secs avant de
        //perdre le nouveau coeur, ce qui n'est pas normal.
        //Et oui, il faut le faire à chaque évènement (le temps passe toujours)
        //Si le tamagotchi dort, on ne compte pas le temps qui passe
        if (!sleeping)
        {
          //Combien de temps est passé depuis que Hunger a changé?
          //C'est le temps de l'évènement actuel - le temps du dernier évènement
          //Il faut avancer TSHgC de cette durée.
          TSHgC=TSHgC+MainActivity.eventsTimesList.get(i)-lastEventTime;
          TSHpC=TSHpC+MainActivity.eventsTimesList.get(i)-lastEventTime;
          //Il faut augmenter la durée depuis laquelle tmgc a faim mais 
          //uniquement s'il a faim
          if (HgH==0)
            TSH=TSH+MainActivity.eventsTimesList.get(i)-lastEventTime;
          if (HpH==0)
            TSB=TSB+MainActivity.eventsTimesList.get(i)-lastEventTime;
        }
        //On récupère le prochain évènement:
        nextEvent=MainActivity.eventsList.get(i);
        //quand tu fais get tu obtiens un ArrayList donc faut convertir (je crois!):
        nextEvent=(String)nextEvent;
        
        //MainActivity.tv.setText(MainActivity.tv.getText()+"Prochain évènement: "+nextEvent+" ");
        //On voit de quel type d'évènement il s'agissait et on agit en conséquence
        if (nextEvent.equals("HgHL"))
        {
          //Si on avait prédit une perte de coeur:
          HgH=HgH-1;
          TSHgC=0;
          //MainActivity.tv.setText(MainActivity.tv.getText()+"Lost 1 Hg Heart \n");
        }
        else if (nextEvent.equals("CMFHg"))
        {
          CM = CM+1;
        }
        else if (nextEvent.equals("DFHg"))
        {
          MainActivity.isAlive=false;
        }
        else if (nextEvent.equals("HpHL"))
        {
          //Si on avait prédit une perte de coeur:
          HpH=HpH-1;
          TSHpC=0;
          //MainActivity.tv.setText(MainActivity.tv.getText()+"Lost 1 Hg Heart \n");
        }
        else if (nextEvent.equals("CMFB"))
        {
          CM = CM+1;
        }
        else if (nextEvent.equals("DFB"))
        {
          MainActivity.isAlive=false;
        }
        else if (nextEvent.equals("Sleep"))
        {
          sleeping=true;
        }
        else if (nextEvent.equals("Wake"))
        {
          sleeping=false;
          lightsOn=true;
        }
        else if (nextEvent.equals("Poop"))
        {
          dirty=true;
        }
        else
        {
          MainActivity.tv.setText(MainActivity.tv.getText()+"Unknown event happened\n");
        }
      }
    }

    //-----Derniere mise à jour des durées (time since hungry changed, etc.)-----//
    if (!sleeping)
    {
      if (i>0)
        lastEventTime=MainActivity.eventsTimesList.get(i-1);
      else
        lastEventTime=TUC;    // pas sûr de ça
          
      //TSHgC=TSHgC+MainActivity.eventsTimesList.get(i)-lastEventTime;
      TSHgC=TSHgC+TUO-lastEventTime;
      if (HgH==0)
        TSH=TSH+TUO-lastEventTime;
      TSHpC=TSHpC+TUO-lastEventTime;
      if (HpH==0)
        TSB=TSB+TUO-lastEventTime;
    }
    
    //-----Remise des résultats dans les variables globales-----//
    MainActivity.t=TUO;
    MainActivity.timeSinceHungryChanged=TSHgC;
    MainActivity.timeSinceHappyChanged=TSHpC;
    MainActivity.stomach=HgH;
    MainActivity.happy=HpH;
    MainActivity.careMisses = CM;
    MainActivity.sleeping = sleeping;
    MainActivity.lightsOn = lightsOn;
    MainActivity.dirty = dirty;
  }
}