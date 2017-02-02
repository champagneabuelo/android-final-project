package com.example.dennis.androidfinal;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import static com.example.dennis.androidfinal.MainActivity.SLOW_THEME;
import static com.example.dennis.androidfinal.MainActivity.current;
import static com.example.dennis.androidfinal.MainActivity.day_bool;
import static com.example.dennis.androidfinal.MainActivity.gpsBool2;
import static com.example.dennis.androidfinal.MainActivity.mOperationCallback;
import static com.example.dennis.androidfinal.MainActivity.night_bool;
import static com.example.dennis.androidfinal.MainActivity.tracker;
import static com.example.dennis.androidfinal.MainActivity.gpsBool;

public class SlowInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slow_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void playEch(View view) {
        MainActivity.mPlayer.playUri(mOperationCallback, SLOW_THEME.get(8), 0, 0);
        current = "slow";
        tracker = 8;
        gpsBool = false;
        day_bool = false;
        night_bool = false;
        gpsBool2 = true;
    }

    public void playHel(View view) {
        MainActivity.mPlayer.playUri(mOperationCallback, SLOW_THEME.get(5), 0, 0);
        current = "slow";
        tracker = 5;
        gpsBool = false;
        day_bool = false;
        night_bool = false;
        gpsBool2 = true;
    }

    public void playRan(View view) {
        MainActivity.mPlayer.playUri(mOperationCallback, SLOW_THEME.get(7), 0, 0);
        current = "slow";
        tracker = 7;
        gpsBool = false;
        day_bool = false;
        night_bool = false;
        gpsBool2 = true;
    }

    public void playSleep(View view) {
        MainActivity.mPlayer.playUri(mOperationCallback, SLOW_THEME.get(6), 0, 0);
        current = "slow";
        tracker = 6;
        gpsBool = false;
        day_bool = false;
        night_bool = false;
        gpsBool2 = true;
    }

    public void play65(View view) {
        MainActivity.mPlayer.playUri(mOperationCallback, SLOW_THEME.get(4), 0, 0);
        current = "slow";
        tracker = 4;
        gpsBool = false;
        day_bool = false;
        night_bool = false;
        gpsBool2 = true;
    }

    public void playBuck(View view) {
        MainActivity.mPlayer.playUri(mOperationCallback, SLOW_THEME.get(0), 0, 0);
        current = "slow";
        tracker = 0;
        gpsBool = false;
        day_bool = false;
        night_bool = false;
        gpsBool2 = true;
    }

    public void playEvo(View view) {
        MainActivity.mPlayer.playUri(mOperationCallback, SLOW_THEME.get(1), 0, 0);
        current = "slow";
        tracker = 1;
        gpsBool = false;
        day_bool = false;
        night_bool = false;
        gpsBool2 = true;
    }

    public void playRand(View view) {
        MainActivity.mPlayer.playUri(mOperationCallback, SLOW_THEME.get(3), 0, 0);
        current = "slow";
        tracker = 3;
        gpsBool = false;
        day_bool = false;
        night_bool = false;
        gpsBool2 = true;
    }

    public void playAlb(View view) {
        MainActivity.mPlayer.playUri(mOperationCallback, SLOW_THEME.get(9), 0, 0);
        current = "slow";
        tracker = 9;
        gpsBool = false;
        day_bool = false;
        night_bool = false;
        gpsBool2 = true;
    }

    public void playU(View view) {
        MainActivity.mPlayer.playUri(mOperationCallback, SLOW_THEME.get(2), 0, 0);
        current = "slow";
        tracker = 2;
        gpsBool = false;
        day_bool = false;
        night_bool = false;
        gpsBool2 = true;
    }

    public void finish(View view) {
        finish();
    }
}
