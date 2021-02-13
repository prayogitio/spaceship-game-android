package com.example.spacheship;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.TextView;

public class RuleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_rule);
        TextView rules = (TextView) findViewById(R.id.textView3);
        rules.setText("Lives will decrease if the spaceship collided or enemy not being shot!");
    }
}