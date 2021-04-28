package com.siddheswar.newearthnews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.CubeGrid;
import com.github.ybq.android.spinkit.style.FadingCircle;
import com.github.ybq.android.spinkit.style.ThreeBounce;

public class Splashscreen extends AppCompatActivity {
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        progressBar = (ProgressBar) findViewById(R.id.spin_kit1);


        Sprite FadingCircle = new FadingCircle();   // Object for Sprite class for progressbar.
        progressBar.setIndeterminateDrawable(FadingCircle);
/*****
 Object of thread class is used to access the method for Thread class
 try/catch logics is used to pause the splash screen for 2000 millis sec.
 *****/
        Thread th = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(5000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    Intent i = new Intent(Splashscreen.this, Genres.class);
                    startActivity(i);
                }
                super.run();
            }
        };
        th.start();
    }


    /***
     onPause is basically used to finish the activity when the application is in pause state
     also this avoid crashing of app.
     ***/

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}