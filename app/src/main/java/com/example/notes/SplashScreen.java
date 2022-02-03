package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class SplashScreen extends AppCompatActivity {

    private TextView txt;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        txt=findViewById(R.id.notestext);
        getSupportActionBar().hide();
        Animation anim= AnimationUtils.loadAnimation(this,R.anim.anim);
        txt.startAnimation(anim);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
              Intent i=new Intent(getApplicationContext(),MainActivity.class);
              startActivity(i);
              finish();
            }
        },4000);

    }



}