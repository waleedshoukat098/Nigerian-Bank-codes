package com.techinnovation.nigerianbankcodes.Activites;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

import com.techinnovation.nigerianbankcodes.R;

public class Webviewprivacy extends AppCompatActivity {

    WebView webviewprivacy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webviewprivacy);
        webviewprivacy = findViewById(R.id.privacypolicy);
        webviewprivacy.loadUrl("file:///android_asset/privacypolicy.html");




    }
}