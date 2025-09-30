package com.example.guessinggame;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Delay for 3 seconds before moving to MainActivity
        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, com.example.guessinggame.MainActivity.class));
            finish();
        }, 3000);
    }
}
