package com.tinyappli.troelslund.galgeleg.Activitys;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.effect.Effect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tinyappli.troelslund.galgeleg.Logik.Galgelogik;
import com.tinyappli.troelslund.galgeleg.R;

import java.util.List;
import java.util.Random;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

public class WinningActivity extends AppCompatActivity implements View.OnClickListener{

    public TextView points;
    public TextView Antalforsøg;

    public Button restart;
    public Button Menu;
    public Button hightscore;
    private Galgelogik logik;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winnng);

        logik = Galgelogik.getInstance();

        points = (TextView) findViewById(R.id.Scrore);
        Antalforsøg = (TextView) findViewById(R.id.Antalforsøg);

        String Score = getIntent().getStringExtra("Score");
        String forsøg = getIntent().getStringExtra("Antalforsøg");

        points.setText(Score);
        Antalforsøg.setText("Du brugte " + forsøg + " forsøg");


        restart = findViewById(R.id.restarte);
        Menu = findViewById(R.id.menu);
        hightscore = findViewById(R.id.highscore);

        restart.setOnClickListener(this);
        Menu.setOnClickListener(this);
        hightscore.setOnClickListener(this);


    }

    @Override
    protected void onStart() {
        super.onStart();

        final KonfettiView konfettiView = (KonfettiView)findViewById(R.id.viewKonfetti);

        MediaPlayer mp = MediaPlayer.create(this, R.raw.winning);
        mp.start();

        konfettiView.build()
                .addColors(Color.YELLOW, Color.RED)
                .setDirection(0.0, 359.0)
                .setSpeed(1f, 5f)
                .setFadeOutEnabled(true)
                .setTimeToLive(3000L)
                .addShapes(Shape.RECT, Shape.CIRCLE)
                .addSizes(new Size(12, 5f))
                .setPosition(-50f, konfettiView.getWidth() + 50f, -50f, -50f)
                .stream(300, 5000L);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.restarte:
                logik.nulstil();
                Intent myIntent = new Intent(WinningActivity.this, gameActivity.class);
                WinningActivity.this.startActivity(myIntent);
                break;

            case R.id.menu:
                Intent myIntent2 = new Intent(WinningActivity.this, MainActivity.class);
                WinningActivity.this.startActivity(myIntent2);
                break;

            case R.id.highscore:
                Intent myIntent1 = new Intent(WinningActivity.this, highscores.class);
                WinningActivity.this.startActivity(myIntent1);
                break;

            default:
                break;
        }


    }
}
