package com.example.idrug01;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
//import androidx.navigation.fragment.NavHostFragment;

import com.example.idrug01.R;

import java.util.Calendar;

public class fragment_calendar extends Fragment {
//    View viewTest = View.inflate(getActivity(), R.layout.ROOT_LAYOUT, null);

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calendar, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final TextView timeTextView = (TextView) view.findViewById(R.id.time_display);

        view.findViewById(R.id.button_accept).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                NavHostFragment.findNavController(CalendarFragment.this)
//                        .navigate(R.id.action_calendarFragment_to_FirstFragment);
            }
        });
        view.findViewById(R.id.button_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                NavHostFragment.findNavController(CalendarFragment.this)
//                        .navigate(R.id.action_calendarFragment_to_FirstFragment);
            }
        });

        view.findViewById(R.id.button_set_time).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Get Current time
                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);


                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                String am_pm = "PM";
                                if (hourOfDay<12){
                                    am_pm = "AM";
                                }
                                if (hourOfDay>12){
                                    hourOfDay=hourOfDay-12;
                                    am_pm = "PM";
                                }
                                if (hourOfDay==0){
                                    hourOfDay=12;
                                }


                                String min = "";
                                if (minute<10){
                                    min="0";
                                }
                                min = min + String.valueOf(minute);

                                timeTextView.setText(hourOfDay + ":" + min + " " + am_pm);
                                //timeTextView.setText(getString(R.string.time, hourOfDay, minute));
                            }
                        }, hour, minute, false);
                timePickerDialog.show();
            }
        });


    }


}

