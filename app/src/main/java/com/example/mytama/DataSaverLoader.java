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
      for (int j = 0; j < 2; j++){
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
        }
        if (MainActivity.version.equals("P2")){
          fields = P2Tama.class.getDeclaredFields();
        } else if (MainActivity.version.equals("Santa")){
          fields = SantaTama.class.getDeclaredFields();
        }
      }
      
      dos.close(); fos.close();
    } catch (Exception e) {
      Printer.append("Error saving data: " + e.getMessage());
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
      
      for (int j = 0; j < 2; j++) {
        for (int i = 0; i < fields.length; i++) {
          fields[i].setAccessible(true);

          Class<?> type = fields[i].getType();
          if (type == int.class)
            fields[i].setInt(null, dis.readInt());
          else if (type == double.class)
            fields[i].setDouble(null, dis.readDouble());
          else if (type == boolean.class)
            fields[i].setBoolean(null, dis.readBoolean());
          else if (type == long.class)
            fields[i].setLong(null, dis.readLong());
        }
        if (MainActivity.version.equals("P2")){
          fields = P2Tama.class.getDeclaredFields();
        } else if (MainActivity.version.equals("Santa")){
          fields = SantaTama.class.getDeclaredFields();
        }
      }
      
      dis.close();fis.close();
                  
      long currentTime = new Date().getTime();
      P2Tama.timeSinceLeft = Math.round((double)((currentTime-timeUponClosing)/1000));
      Graphics.loadCharacterGraphics(MainActivity.context, Tama.character);
      if (Tama.isAlive) {
        catchUp();
        MainActivity.state = "idle";
      } else
        MainActivity.state = "dead1";
    } catch (Exception e) {
      Printer.append("Error loading save file: " + e.getMessage());
      MainActivity.version = "P2";
      Tama.reset();
    }
  }

  public static String[] getSaveFiles() {
    File[] files = MainActivity.context.getFilesDir().listFiles();
    String[] fileNames = new String[files.length];
    if (files.length == 0)
      MainActivity.state = "reset_screen";
    else {
      for (int i = 0; i < files.length; i++)
        fileNames[i] = files[i].getName().substring(0,(files[i].getName().length()-4));
    }
    return fileNames;
  }
  

  public static void catchUp() {
    MainActivity.isOpen = false;
    MainActivity.catchingUp = true;
    if (Tama.timeSinceLeft <= 3*24*3600) {
      if (Tama.isAlive) {
        for (int i = 0; i < Tama.timeSinceLeft; i++) {
          Updater.update();
        }
      }
      MainActivity.state = "idle";
    } else {
      Tama.isAlive = false;
      MainActivity.state = "dead1";
    }
    MainActivity.isOpen = true;
    MainActivity.catchingUp = false;
  }
}