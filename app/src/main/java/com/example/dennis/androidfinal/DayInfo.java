package com.example.dennis.androidfinal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import static com.example.dennis.androidfinal.MainActivity.DAY_THEME;
import static com.example.dennis.androidfinal.MainActivity.current;
import static com.example.dennis.androidfinal.MainActivity.day_bool;
import static com.example.dennis.androidfinal.MainActivity.gpsBool;
import static com.example.dennis.androidfinal.MainActivity.gpsBool2;
import static com.example.dennis.androidfinal.MainActivity.mOperationCallback;
import static com.example.dennis.androidfinal.MainActivity.night_bool;
import static com.example.dennis.androidfinal.MainActivity.tracker;

public class DayInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void playJimi(View view) {
        MainActivity.mPlayer.playUri(mOperationCallback, DAY_THEME.get(8), 0, 0);
        current = "day";
        tracker = 8;
        night_bool = false;
        day_bool = true;
        gpsBool = false;
        gpsBool2 = false;
    }

    public void playChili(View view) {
        MainActivity.mPlayer.playUri(mOperationCallback, DAY_THEME.get(9), 0, 0);
        current = "day";
        tracker = 9;
        night_bool = false;
        day_bool = true;
        gpsBool = false;
        gpsBool2 = false;
    }

    public void playAva(View view) {
        MainActivity.mPlayer.playUri(mOperationCallback, DAY_THEME.get(6), 0, 0);
        current = "day";
        tracker = 6;
        night_bool = false;
        day_bool = true;
        gpsBool = false;
        gpsBool2 = false;
    }

    public void playTdd(View view) {
        MainActivity.mPlayer.playUri(mOperationCallback, DAY_THEME.get(3), 0, 0);
        current = "day";
        tracker = 3;
        night_bool = false;
        day_bool = true;
        gpsBool = false;
        gpsBool2 = false;
    }

    public void playEagle(View view) {
        MainActivity.mPlayer.playUri(mOperationCallback, DAY_THEME.get(2), 0, 0);
        current = "day";
        tracker = 2;
        night_bool = false;
        day_bool = true;
        gpsBool = false;
        gpsBool2 = false;
    }

    public void playOasis(View view) {
        MainActivity.mPlayer.playUri(mOperationCallback, DAY_THEME.get(1), 0, 0);
        current = "day";
        tracker = 1;
        night_bool = false;
        day_bool = true;
        gpsBool = false;
        gpsBool2 = false;
    }

    public void playFray(View view) {
        MainActivity.mPlayer.playUri(mOperationCallback, DAY_THEME.get(5), 0, 0);
        current = "day";
        tracker = 5;
        night_bool = false;
        day_bool = true;
        gpsBool = false;
        gpsBool2 = false;
    }

    public void playAva2(View view) {
        MainActivity.mPlayer.playUri(mOperationCallback, DAY_THEME.get(7), 0, 0);
        current = "day";
        tracker = 7;
        night_bool = false;
        day_bool = true;
        gpsBool = false;
        gpsBool2 = false;
    }

    public void playKol(View view) {
        MainActivity.mPlayer.playUri(mOperationCallback, DAY_THEME.get(0), 0, 0);
        current = "day";
        tracker = 0;
        night_bool = false;
        day_bool = true;
        gpsBool = false;
        gpsBool2 = false;
    }

    public void playPink(View view) {
        MainActivity.mPlayer.playUri(mOperationCallback, DAY_THEME.get(4), 0, 0);
        current = "day";
        tracker = 4;
        night_bool = false;
        day_bool = true;
        gpsBool = false;
        gpsBool2 = false;
    }

    public void finish(View view) {
        finish();
    }
}
