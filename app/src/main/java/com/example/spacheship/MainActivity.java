package com.example.spacheship;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private ImageButton buttonPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        buttonPlay = (ImageButton) findViewById(R.id.buttonPlay);
    }

    public void playGame(View v) {
        startActivity(new Intent(this, GameActivity.class));
    }

    public void seeRules(View v) {
        startActivity(new Intent(this, RuleActivity.class));
    }
}