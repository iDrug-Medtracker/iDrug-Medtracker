package com.example.idrug01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Calendar extends AppCompatActivity {
    ArrayList<medication> medList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        //load medList with function created bellow
        loadData();

        //just to see if medList is working display all its content

        TextView DisplayStringArray = findViewById(R.id.lista);
        DisplayStringArray.setTextSize(15);
        for (int i=0; i<medList.size();i++) {
            DisplayStringArray.append(medList.get(i).name);
            DisplayStringArray.append(" ");
            DisplayStringArray.append(String.valueOf(medList.get(i).dosage));
            DisplayStringArray.append(" ");
            DisplayStringArray.append(String.valueOf(medList.get(i).period));
            DisplayStringArray.append(" ");
            DisplayStringArray.append(medList.get(i).times);
            DisplayStringArray.append("\n");

        }

    }
    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(medList);
        editor.putString("task list", json);
        editor.apply();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList<medication>>() {}.getType();
        medList = gson.fromJson(json, type);
        if (medList == null) {
            medList = new ArrayList<>();
        }
    }
    public void openHome(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
