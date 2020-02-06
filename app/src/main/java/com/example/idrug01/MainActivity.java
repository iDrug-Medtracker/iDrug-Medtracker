package com.example.idrug01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;



public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private int progressStatus = 0;

    //not used, example function for eric
    public static int extractHourOfDayFromMS(long ms) {
        int millis = (int) (ms % 86400000);

        if (millis < 0) {
            millis += 86400000;
        }
        while (millis < 0) {
            millis += 86400000;
        }
        while (millis >= 86400000) {
            millis -= 86400000;
        }
        millis /= (1000*60*60);
        return millis % 24;
    }

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar) findViewById(R.id.circulo);
        //to get a clock
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                TextView tdate = (TextView) findViewById(R.id.date);
                                long date = System.currentTimeMillis();
                                SimpleDateFormat sdf = new SimpleDateFormat("h:mm a");
                                String dateString = sdf.format(date);
                                tdate.setText(dateString);

                                //get hour for progress circle
                                SimpleDateFormat sdf2 = new SimpleDateFormat("HH");
                                int x = Integer.parseInt(sdf2.format(date));
                                progressStatus = 100-(x*100/24);
                                progressBar.setProgress(progressStatus);
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        t.start();
    }
}
