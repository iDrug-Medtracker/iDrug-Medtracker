package com.example.idrug01;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import android.app.TimePickerDialog;
import android.widget.TimePicker;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class NewPillActivity extends AppCompatActivity {
    ArrayList<medication> medList;
    private String name;
    private String times;
    private int dosage;
    private int period;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newpill);
        //load medList with function created bellow
        loadData();

        //buttonDone should add the new medication and save ArrayList
        Button buttonDone = findViewById(R.id.but_done);
        buttonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et1 = findViewById(R.id.editName);
                name = et1.getEditableText().toString();

                TextView et2 = findViewById(R.id.time_display);
                times = et2.getText().toString();

                EditText et3 = findViewById(R.id.editDosage);
                //check for integers or it crashes
                try {
                    dosage = Integer.parseInt(et3.getEditableText().toString());
                } catch (NumberFormatException e) {
                    dosage = 0;
                }
                EditText et4 = findViewById(R.id.editPeriod);
                try {
                    period = Integer.parseInt(et4.getEditableText().toString());
                } catch (NumberFormatException e) {
                    period = 0;
                }
                medList.add( new medication(name, times, dosage, period));
                saveData();
                openHome();
            }
        });

        ///////////////////////////////////////////////////////////
        //-------------------------Times---------------------------
        ///////////////////////////////////////////////////////////
        final TextView timeTextView = findViewById(R.id.time_display);

        Button timePicker = findViewById(R.id.button_setTime);
        timePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Get Current time
                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);



                TimePickerDialog timePickerDialog = new TimePickerDialog(NewPillActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                String min = "";
                                if (minute<10){
                                    min="0";
                                }
                                min = min + minute;

                                timeTextView.append("(" + hourOfDay + ":" + min + ")");
                            }
                        }, hour, minute, false);
                timePickerDialog.show();
            }
        });

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
