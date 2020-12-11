package com.siddheswar.newearthnews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.CubeGrid;
import com.github.ybq.android.spinkit.style.ThreeBounce;

public class Splashscreen extends AppCompatActivity {
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        progressBar = (ProgressBar)findViewById(R.id.spin_kit1);
        Sprite doubleBounce = new ThreeBounce();
        progressBar.setIndeterminateDrawable(doubleBounce);

        Thread th = new Thread(){
            @Override
    public void run() {
        try {
            sleep(2000);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        finally {
            Intent i = new Intent(Splashscreen.this, Login.class);
            startActivity(i);
        }
        super.run();
    }
};
        th.start();
                }

@Override
protected void onPause() {
        super.onPause();
        finish();
        }
}