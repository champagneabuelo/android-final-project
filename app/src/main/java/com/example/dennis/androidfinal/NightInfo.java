package com.example.dennis.androidfinal;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import static com.example.dennis.androidfinal.MainActivity.NIGHT_THEME;
import static com.example.dennis.androidfinal.MainActivity.current;
import static com.example.dennis.androidfinal.MainActivity.day_bool;
import static com.example.dennis.androidfinal.MainActivity.gpsBool;
import static com.example.dennis.androidfinal.MainActivity.gpsBool2;
import static com.example.dennis.androidfinal.MainActivity.mOperationCallback;
import static com.example.dennis.androidfinal.MainActivity.night_bool;
import static com.example.dennis.androidfinal.MainActivity.tracker;

public class NightInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_night_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void playKen(View view) {
        MainActivity.mPlayer.playUri(mOperationCallback, NIGHT_THEME.get(7), 0, 0);
        current = "night";
        tracker = 7;
        night_bool = true;
        day_bool = false;
        gpsBool = false;
        gpsBool2 = false;
    }

    public void playChili(View view) {
        MainActivity.mPlayer.playUri(mOperationCallback, NIGHT_THEME.get(0), 0, 0);
        current = "night";
        tracker = 0;
        night_bool = true;
        day_bool = false;
        gpsBool = false;
        gpsBool2 = false;
    }

    public void playAva(View view) {
        MainActivity.mPlayer.playUri(mOperationCallback, NIGHT_THEME.get(1), 0, 0);
        current = "night";
        tracker = 1;
        night_bool = true;
        day_bool = false;
        gpsBool = false;
        gpsBool2 = false;
    }

    public void playBreak(View view) {
        MainActivity.mPlayer.playUri(mOperationCallback, NIGHT_THEME.get(3), 0, 0);
        current = "night";
        tracker = 3;
        night_bool = true;
        day_bool = false;
        gpsBool = false;
        gpsBool2 = false;
    }

    public void playDrake(View view) {
        MainActivity.mPlayer.playUri(mOperationCallback, NIGHT_THEME.get(6), 0, 0);
        current = "night";
        tracker = 6;
        night_bool = true;
        day_bool = false;
        gpsBool = false;
        gpsBool2 = false;
    }

    public void playU2(View view) {
        MainActivity.mPlayer.playUri(mOperationCallback, NIGHT_THEME.get(8), 0, 0);
        current = "night";
        tracker = 8;
        night_bool = true;
        day_bool = false;
        gpsBool = false;
        gpsBool2 = false;
    }

    public void playU22(View view) {
        MainActivity.mPlayer.playUri(mOperationCallback, NIGHT_THEME.get(9), 0, 0);
        current = "night";
        tracker = 9;
        night_bool = true;
        day_bool = false;
        gpsBool = false;
        gpsBool2 = false;
    }

    public void playPlus(View view) {
        MainActivity.mPlayer.playUri(mOperationCallback, NIGHT_THEME.get(5), 0, 0);
        current = "night";
        tracker = 5;
        night_bool = true;
        day_bool = false;
        gpsBool = false;
        gpsBool2 = false;
    }

    public void playPink2(View view) {
        MainActivity.mPlayer.playUri(mOperationCallback, NIGHT_THEME.get(4), 0, 0);
        current = "night";
        tracker = 4;
        night_bool = true;
        day_bool = false;
        gpsBool = false;
        gpsBool2 = false;
    }

    public void playPink(View view) {
        MainActivity.mPlayer.playUri(mOperationCallback, NIGHT_THEME.get(2), 0, 0);
        current = "night";
        tracker = 2;
        night_bool = true;
        day_bool = false;
        gpsBool = false;
        gpsBool2 = false;
    }

    public void finish(View view) {
        finish();
    }
}
