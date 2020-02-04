package com.hp.groceriapp.PreSignup;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.hp.groceriapp.R;

public class MainActivity extends AppCompatActivity {

    private TextView head;
    private MaterialButton proceed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashsapre);
        initView();

        Animation an2 = AnimationUtils.loadAnimation(this, R.anim.slide);
        head.startAnimation(an2);

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,SelectUser.class));
            }
        });

    }

    private void initView() {
        head = (TextView) findViewById(R.id.head);
        proceed = (MaterialButton) findViewById(R.id.proceed);
    }
}
