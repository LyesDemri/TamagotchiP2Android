package com.example.mytama;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class TamaSelectionActivity extends Activity {
  
  static public TextView tamaSelectTV;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.tama_selection_activity);
    tamaSelectTV = (TextView)findViewById(R.id.tama_select_tv);
    tamaSelectTV.setText("Hello!");

    //We need these 2 objects to define the layout at runtime
    ListView listView; //takes the views and displays them
    String[] files = new String[3];
    files[0] = "salut";
    files[1] = "lol";
    files[2] = "coucou";

    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.list_item_layout, files);
    listView = (ListView)findViewById(R.id.listView);
    try {
      //tamaSelectTV.setText(String.valueOf(arrayAdapter==null));
      listView.setAdapter(arrayAdapter);
    } catch (Exception e) {
      //tamaSelectTV.setText(e.getMessage());
    }
  }
}