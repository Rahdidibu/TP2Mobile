package com.example.tp2;

import android.annotation.SuppressLint;
import android.os.CountDownTimer;
import android.widget.EditText;

public class CustomTimer extends CountDownTimer {
    EditText TIMER_TEXT;
    GameActivity ACTIVITY;

    public CustomTimer(long NBR_SECONDES, long INTERVAL, EditText TEXT, GameActivity ACTIVITY){
        super(NBR_SECONDES,INTERVAL);
        this.TIMER_TEXT = TEXT;
        this.ACTIVITY = ACTIVITY;
    }

    @SuppressLint("SetTextI18n")
    public void onTick(long MILLISECONDES_AVANT_FIN){
        TIMER_TEXT.setText("TEMPS RESTANT: " + MILLISECONDES_AVANT_FIN/1000 + " secondes");
    }

    @SuppressLint("SetTextI18n")
    public void onFinish(){
        TIMER_TEXT.setText("TEMPS RESTANT: " + 0 + " secondes");
        ACTIVITY.timer_end();
    }
}
