package com.example.mytama;

import java.util.Arrays;
import java.util.Collections;

public class FuturePredictor {
  public static void predict()
  {
    //recuperation des variables necessaires
    double TUC = MainActivity.t;
    int HgHUC=MainActivity.stomach;
    int HpHUC=MainActivity.happy;
    double HgHLP=180;
    double HpHLP=180;
    double TSHgC=MainActivity.timeSinceHungryChanged;
    double TSHpC=MainActivity.timeSinceHappyChanged;
    double TSH=MainActivity.timeSinceHungry;
    double TSB=MainActivity.timeSinceBored;
    int CM=MainActivity.careMisses;
    boolean sleeping=MainActivity.sleeping;
    boolean lightsOn=MainActivity.lightsOn;
    boolean dirty=MainActivity.dirty;
    
    double DFHgP=6*3600;
    double DFBP=6*3600;
    double CMP = 900;
    
    //en fait je me demande si c'est necessaire de calculer ca
    //Time for hunger care miss:
    double TFHgCM=TUC+(HgHUC-1)*HgHLP+(HgHLP-TSHgC)+CMP;
    if (TFHgCM<TUC)
      TFHgCM=Integer.MAX_VALUE;

    //time for bordom care miss
    double TFBCM=TUC+(HpHUC-1)*HpHLP+(HpHLP-TSHpC)+CMP;
    if (TFBCM<TUC)
      TFBCM=Integer.MAX_VALUE;
    
    //----
    //Calcul de Time to Lose Hungry Heart
    //et de Time to Die from Hunger
    double TTLHgH,TTDFHg;
    if (HgHUC!=0)
    {
      TTLHgH=TUC+HgHLP-TSHgC;
      TTDFHg=TUC+(HgHUC-1)*HgHLP+(HgHLP-TSHgC)+DFHgP;
    }
    else
    {
      TTLHgH=Integer.MAX_VALUE;
      TTDFHg=TUC+DFHgP-TSH;
    }

    //Calcul de Time to Lose Happy Heart
    //et de Time to Die from Boredom
    double TTLHpH,TTDFB;
    if (HpHUC!=0)
    {
      TTLHpH=TUC+HpHLP-TSHpC;
      TTDFB=TUC+(HpHUC-1)*HpHLP+(HpHLP-TSHpC)+DFBP;
    }
    else
    {
      TTLHpH=Integer.MAX_VALUE;
      TTDFB=TUC+DFBP-TSB;
    }

    //sleeping stuff
    double TTS, TTW, TSA;
    if (!sleeping)
    {
      if (TUC<2400)
      {
        TTS=2400;
        TTW=2700;
      }
      else
      {
        TTS=Double.MAX_VALUE;
        TTW=Double.MAX_VALUE;
      }
      // pas la peine de decaler les evenements car ils seront decales lors de l'endormissement
    }
    else
    {
      //Si babytchi dormait on a pas besoin de verifier si on est avant t=2700, c'est forcement le cas
      TTS=Double.MAX_VALUE;
      TTW=2700;
      //pas de lights care miss possible pour babytchi
      //decaler tous les evenements de la duree du sommeil vers le futur
      TSA=TUC-2700;
      double sleepDuration=2700-TSA;
      TTLHgH=TTLHgH+sleepDuration;
      TTLHpH=TTLHpH+sleepDuration;
      TFHgCM=TFHgCM+sleepDuration;
      TFBCM=TFBCM+sleepDuration;
      TTDFHg=TTDFHg+sleepDuration;
      TTDFB=TTDFB+sleepDuration;
    }
    
    double TTP;
    if (dirty) 
    {
      TTP=Double.MAX_VALUE;
    }
    else
    {
      if (TUC<900)
        TTP=900;
      else if (TUC < 2705)
      {
        TTP=2705;
      }
      else
        TTP=3900+180*60;
    }
    //Liste d'événements possibles avec leur date
    String[] possibleEvents = new String[]{"HgHL","DFHg","CMFHg","HpHL","DFB","CMFB","Sleep","Wake","Poop"};
    Double[] eventTimes = {TTLHgH,TTDFHg,TFHgCM,TTLHpH,TTDFB,TFBCM,TTS,TTW,TTP};
    boolean alive=true;
    
    //k compte combien de fois on fait la boucle
    //(pour éviter les boucles infinies
    int k=0;
    //instant du prochain événement
    double nextEventTime;
    //index qui servira à désigner le prochain événement
    //dans la liste des évènements possibles
    int i=0;
    //Hunger Hearts pour la simulation
    int HgH=HgHUC;
    int HpH=HpHUC;
    //prochain événement
    String nextEvent="";
    MainActivity.eventsList.clear();
    MainActivity.eventsTimesList.clear();    
    //List 
    while (alive)
    {
      //determination du prochain événement
      nextEventTime=Collections.min(Arrays.asList(eventTimes));
      i=Utils.minValueIndex(eventTimes);
      nextEvent = possibleEvents[i];
      
      //application de l'evenement
      if (nextEvent=="HgHL")
      {
        //MainActivity.tv.setText(MainActivity.tv.getText()+"\n HgHL: "+String.valueOf(nextEventTime));
        HgH=HgH-1;
        if (HgH==0)
          TTLHgH=Double.MAX_VALUE;
        else
          TTLHgH=TTLHgH+HgHLP;
      }
      else if (nextEvent=="DFHg")
      {
        //MainActivity.tv.setText(MainActivity.tv.getText()+"\n DFHg"+String.valueOf(nextEventTime));
        alive=false;
      }
      else if (nextEvent=="CMFHg")
      {
        //MainActivity.tv.setText(MainActivity.tv.getText()+"\n CMFHg"+String.valueOf(nextEventTime));
        CM=CM+1;
        TFHgCM=Double.MAX_VALUE;
        if (CM==25)
          alive=false;
      }
      else if (nextEvent=="HpHL")
      {
        //MainActivity.tv.setText(MainActivity.tv.getText()+"\n HpHL: "+String.valueOf(nextEventTime));
        HpH=HpH-1;
        if (HpH==0)
          TTLHpH=Double.MAX_VALUE;
        else
          TTLHpH=TTLHpH+HpHLP;
      }
      else if (nextEvent=="DFB")
      {
        //MainActivity.tv.setText(MainActivity.tv.getText()+"\n DFB"+String.valueOf(nextEventTime));
        alive=false;
      }
      else if (nextEvent=="CMFB")
      {
        //MainActivity.tv.setText(MainActivity.tv.getText()+"\n CMFB"+String.valueOf(nextEventTime));
        CM=CM+1;
        TFBCM=Double.MAX_VALUE;
        if (CM==25)
          alive=false;
      }
      else if (nextEvent=="Sleep")
      {
        //decaler tous les evenements de sleepDuration
        //pour babytchi sleepDuration=300
        double sleepDuration=300;
        TTLHgH=TTLHgH+sleepDuration;
        TTLHpH=TTLHpH+sleepDuration;
        TFHgCM=TFHgCM+sleepDuration;
        TFBCM=TFBCM+sleepDuration;
        TTDFHg=TTDFHg+sleepDuration;
        TTDFB=TTDFB+sleepDuration;
        //Le time to poop n'est pas décalé pour babytchi
        //TTP=TTP+sleepDuration;
        
        //on signalise a la simulation que le tmgc dort
        sleeping=true;
        //on decale l'endormissement
        TTS=TTS+24*3600;
      }
      else if (nextEvent=="Wake")
      {
        sleeping=false;
        lightsOn=true;
        TTW=TTW+24*3600;
      }
      else if (nextEvent=="Poop")
      {
          dirty=true;
          TTP=Double.MAX_VALUE;
      }
      
      //Pour éviter les boucles infinies
      k=k+1;
      if (k==100)
      {
        alive=false; //pour sortir de la boucle
        MainActivity.tv.setText(MainActivity.tv.getText()+"\n PROBLEME!!");
      }
      //On ajoute l'évènement à la liste d'évènements prédits:
      MainActivity.eventsList.add(nextEvent);
      MainActivity.eventsTimesList.add(nextEventTime);
      //On met à jour les instants des évènements possibles
      eventTimes= new Double[]{TTLHgH,TTDFHg,TFHgCM,TTLHpH,TTDFB,TFBCM,TTS,TTW,TTP};
    }
  }
}