package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Handler;
import android.content.Intent;
import android.os.Bundle;

public class InstantActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instant);
        getSupportActionBar().hide();

        final Intent i = new Intent(InstantActivity.this, MainActivity2.class);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(i);
                finish();
            }
        }, 2500);
    }
}