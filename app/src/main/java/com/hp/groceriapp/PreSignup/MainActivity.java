package com.hp.groceriapp.PreSignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.hp.groceriapp.R;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView=(ImageView)findViewById(R.id.img);
        Animation an2= AnimationUtils.loadAnimation(this,R.anim.slide);
        imageView.startAnimation(an2);
        Thread background = new Thread() {
            public void run() {
                try {
                    // Thread will sleep for 5 seconds
                    sleep(2*1000);

                    // After 5 seconds redirect to another intent
                    Intent i=new Intent(getBaseContext(),SelectUser.class);
                    startActivity(i);

                    //Remove activity
                    finish();
                } catch (Exception e) {
                    Log.e("Splash", String.valueOf(e));
                }
            }
        };
        // start thread
        background.start();
    }
}
