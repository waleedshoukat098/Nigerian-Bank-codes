package com.techinnovation.nigerianbankcodes.Activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.techinnovation.nigerianbankcodes.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        int splash = 5;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },splash*1000);
        ;
    }
}
