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
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;

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
               // startActivity(new Intent(MainActivity.this,SelectUser.class));
            presentActivity(view);
            }
        });

    }

    private void initView() {
        head = (TextView) findViewById(R.id.head);
        proceed = (MaterialButton) findViewById(R.id.proceed);
    }

    public void presentActivity(View view) {
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(MainActivity.this, view, "transition");
        int revealX = (int) (view.getX() + view.getWidth() / 2);
        int revealY = (int) (view.getY() + view.getHeight() / 2);

        Intent intent = new Intent(MainActivity.this, SelectUser.class);
        intent.putExtra(SelectUser.EXTRA_CIRCULAR_REVEAL_X, revealX);
        intent.putExtra(SelectUser.EXTRA_CIRCULAR_REVEAL_Y, revealY);

        ActivityCompat.startActivity(MainActivity.this, intent, options.toBundle());
    }
}

