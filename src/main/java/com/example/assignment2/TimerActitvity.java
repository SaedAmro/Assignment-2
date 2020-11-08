package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;

import java.util.Locale;

public class TimerActitvity extends AppCompatActivity {
    private int seconds = 0;
    private int min = 0;
    private int hour = 0;
 private EditText timer ;
    private boolean isrunning;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_);
        timer = (EditText) findViewById( R.id.timer);

    }

    @Override
    public void onSaveInstanceState(
            Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", isrunning);
    }

    @Override
    protected void onPause() {
        super.onPause();
        isrunning = true;
    }

    protected void onResume() {
        super.onResume();
        isrunning = true;
    }

    public void onClickStart(View view) {
        isrunning = true;
     //   seconds = Integer.parseInt(timer.getText().toString());
        String [] time = timer.getText().toString().trim().split(":");
        min = Integer.parseInt(time[1]);
        hour = Integer.parseInt(time[0]);
        seconds = Integer.parseInt(time[2]);
        runTimer();
    }

    public void onClickPause(View view) {
        isrunning = false;
    }

    public void onClickReset(View view) {
        isrunning = false;
        seconds = 0;
    }

    private void runTimer() {

        final Handler handler = new Handler();

        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = hour;
                int minutes = min;
                int secs = seconds ;
                String time = String.format(Locale.getDefault(),"%d:%02d:%02d", hours,minutes, secs);
                timer.setText(time);
                if (isrunning) {
                    if (seconds == 0 && min != 0) {
                        min--;
                        seconds = 60;
                    }
                    if ( min == 0 && hour != 0) {
                        hours--;
                        min = 60;
                    }
                    if( seconds != 0 || min !=0 || hours != 0) {
                        seconds--;
                    }
                }
                handler.postDelayed(this, 1000);
            }
        });
    }


}
