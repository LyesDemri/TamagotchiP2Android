package com.example.mytama;

import java.io.File;
import java.io.FileOutputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.DataInputStream;
import java.util.Date;
import java.lang.reflect.Field;

public class DataSaverLoader {
  public static void saveData() {
    try { 
      FileOutputStream fos = new FileOutputStream(MainActivity.context.getFilesDir()+"/"+Tama.name+".txt");
      DataOutputStream dos = new DataOutputStream(fos);
      
      dos.writeUTF(Tama.character);
      dos.writeLong(new Date().getTime());
      dos.writeUTF(MainActivity.state);
      dos.writeUTF(MainActivity.version);
      Field[] fields = Tama.class.getDeclaredFields();
      for (int j = 0; j < 2; j++) {
        for (int i = 0; i < fields.length; i++) {
          Object value = fields[i].get(null);
          if (value instanceof Double)
            dos.writeDouble((double)value);
          else if (value instanceof Integer)
            dos.writeInt((int)value);
          else if (value instanceof Boolean)
            dos.writeBoolean((boolean)value);
          else if (value instanceof Long)
            dos.writeLong((long)value);
          else if (value instanceof int[]) {
            int[] table = (int[])value;
            dos.writeInt(table.length);
            for (int k = 0; k < table.length; k++) {
              dos.writeInt(table[k]);
            }
          }
        }
        if (MainActivity.version.equals("P2")){
          fields = P2Tama.class.getDeclaredFields();
        } else if (MainActivity.version.equals("Santa")){
          fields = SantaTama.class.getDeclaredFields();
        }
      }
      
      if (MainActivity.version.equals("Santa")) {
        dos.writeUTF(SantaTama.companion);
      }  
      dos.close(); fos.close();
    } catch (Exception e) {
      Printer.log("Error saving data: " + e.getMessage());
    }
  }

  public static void loadData() {
    try {
      FileInputStream fis = new FileInputStream(MainActivity.context.getFilesDir()+"/"+Tama.name+".txt");
      DataInputStream dis = new DataInputStream(fis);
      
      Tama.character = dis.readUTF();
      long timeUponClosing = dis.readLong();
      MainActivity.state = dis.readUTF();
      try {
        MainActivity.version = dis.readUTF();
      } catch (Exception e) {
        MainActivity.version = "P2";
      }
      
      Field[] fields = Tama.class.getDeclaredFields();
      Printer.print("Loaded fields:");
      for (int j = 0; j < 2; j++) {
        for (int i = 0; i < fields.length; i++) {
          fields[i].setAccessible(true);
          
          Class<?> type = fields[i].getType();
          Printer.append(" " + fields[i].getName());
          if (type == int.class)
            fields[i].setInt(null, dis.readInt());
          else if (type == double.class)
            fields[i].setDouble(null, dis.readDouble());
          else if (type == boolean.class)
            fields[i].setBoolean(null, dis.readBoolean());
          else if (type == long.class)
            fields[i].setLong(null, dis.readLong());
          else if (type == int[].class) {
            Printer.append("Reading table " + fields[i].getName(), false);
            int l = dis.readInt();
            Printer.append("length =  " + l, false);
            int[] table = new int[l];
            for (int k = 0; k < l; k++) {
              table[k] = dis.readInt();
              Printer.append("Value " + k + ": " + table[k], false);
            }
            fields[i].set(null, table);
          }
        }
        if (MainActivity.version.equals("P2")){
          fields = P2Tama.class.getDeclaredFields();
        } else if (MainActivity.version.equals("Santa")){
          fields = SantaTama.class.getDeclaredFields();
        }
      }
      
      if (MainActivity.version.equals("Santa")) {
          SantaTama.companion = dis.readUTF();
      }
      dis.close();fis.close();
                  
      long currentTime = new Date().getTime();
      Tama.timeSinceLeft = Math.round((double)((currentTime-TimeWizard.getTamagotchiLongTime())/1000));
      Graphics.loadCharacterGraphics();
      
      catchUp();
      Printer.print(Tama.name);
      Printer.append("\n" + Tama.updatesWhileAbsent + " updates while absent", false);
      Printer.append("\n" + Tama.notificationsSent + " notifications sent", false);
    } catch (Exception e) {
      Printer.log("Error loading save file: " + e.getMessage());
      //MainActivity.version = "P2";
      Tama.reset();
    }
  }

  public static String[] getSaveFiles() {
    File[] files = MainActivity.context.getFilesDir().listFiles();
    String[] fileNames = new String[files.length];
    if (files.length == 0)
      MainActivity.state = "version_select_screen";
    else {
      for (int i = 0; i < files.length; i++)
        fileNames[i] = files[i].getName().substring(0,(files[i].getName().length()-4));
    }
    return fileNames;
  }

  public static void catchUp() {
    MainActivity.isOpen = false;
    MainActivity.catchingUp = true;
    if (Tama.timeSinceLeft <= 3*24*3600 && Tama.isAlive) {
      for (int i = 0; i < Tama.timeSinceLeft; i++) {
        Updater.update();
      }
      if (Tama.isAlive) MainActivity.state = "idle";
      else MainActivity.state = "dead";
    } else {
      Tama.isAlive = false;
      MainActivity.state = "dead";
    }
    MainActivity.isOpen = true;
    MainActivity.catchingUp = false;
  }
}