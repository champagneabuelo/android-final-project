package com.example.dennis.androidfinal;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import static com.example.dennis.androidfinal.MainActivity.FAST_THEME;
import static com.example.dennis.androidfinal.MainActivity.current;
import static com.example.dennis.androidfinal.MainActivity.day_bool;
import static com.example.dennis.androidfinal.MainActivity.gpsBool2;
import static com.example.dennis.androidfinal.MainActivity.mOperationCallback;
import static com.example.dennis.androidfinal.MainActivity.night_bool;
import static com.example.dennis.androidfinal.MainActivity.tracker;
import static com.example.dennis.androidfinal.MainActivity.gpsBool;

public class FastInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fast_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void playStone(View view) {
        MainActivity.mPlayer.playUri(mOperationCallback, FAST_THEME.get(4), 0, 0);
        current = "fast";
        tracker = 4;
        gpsBool = true;
        day_bool = false;
        night_bool = false;
        gpsBool2 = false;
    }

    public void playDef(View view) {
        MainActivity.mPlayer.playUri(mOperationCallback, FAST_THEME.get(8), 0, 0);
        current = "fast";
        tracker = 8;
        gpsBool = true;
        day_bool = false;
        night_bool = false;
        gpsBool2 = false;
    }

    public void playChev(View view) {
        MainActivity.mPlayer.playUri(mOperationCallback, FAST_THEME.get(2), 0, 0);
        current = "fast";
        tracker = 2;
        gpsBool = true;
        day_bool = false;
        night_bool = false;
        gpsBool2 = false;
    }

    public void playBlink(View view) {
        MainActivity.mPlayer.playUri(mOperationCallback, FAST_THEME.get(0), 0, 0);
        current = "fast";
        tracker = 0;
        gpsBool = true;
        day_bool = false;
        night_bool = false;
        gpsBool2 = false;
    }

    public void playFun(View view) {
        MainActivity.mPlayer.playUri(mOperationCallback, FAST_THEME.get(6), 0, 0);
        current = "fast";
        tracker = 6;
        gpsBool = true;
        day_bool = false;
        night_bool = false;
        gpsBool2 = false;
    }

    public void playThree(View view) {
        MainActivity.mPlayer.playUri(mOperationCallback, FAST_THEME.get(1), 0, 0);
        current = "fast";
        tracker = 1;
        gpsBool = true;
        day_bool = false;
        night_bool = false;
        gpsBool2 = false;
    }

    public void playDis(View view) {
        MainActivity.mPlayer.playUri(mOperationCallback, FAST_THEME.get(9), 0, 0);
        current = "fast";
        tracker = 9;
        gpsBool = true;
        day_bool = false;
        night_bool = false;
        gpsBool2 = false;
    }

    public void playBreak(View view) {
        MainActivity.mPlayer.playUri(mOperationCallback, FAST_THEME.get(3), 0, 0);
        current = "fast";
        tracker = 3;
        gpsBool = true;
        day_bool = false;
        night_bool = false;
        gpsBool2 = false;
    }

    public void play30(View view) {
        MainActivity.mPlayer.playUri(mOperationCallback, FAST_THEME.get(7), 0, 0);
        current = "fast";
        tracker = 7;
        gpsBool = true;
        day_bool = false;
        night_bool = false;
        gpsBool2 = false;
    }

    public void playSTT(View view) {
        MainActivity.mPlayer.playUri(mOperationCallback, FAST_THEME.get(5), 0, 0);
        current = "fast";
        tracker = 5;
        gpsBool = true;
        day_bool = false;
        night_bool = false;
        gpsBool2 = false;
    }

    public void finish(View view) {
        finish();
    }
}
