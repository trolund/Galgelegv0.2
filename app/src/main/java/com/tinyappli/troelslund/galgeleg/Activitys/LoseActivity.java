package com.tinyappli.troelslund.galgeleg.Activitys;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tinyappli.troelslund.galgeleg.Logik.Galgelogik;
import com.tinyappli.troelslund.galgeleg.R;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

public class LoseActivity extends AppCompatActivity implements View.OnClickListener {

    public TextView ordet;
    private Galgelogik logik;

    public Button restart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lose);

        logik = Galgelogik.getInstance();

        ordet = (TextView) findViewById(R.id.ordet);

        ordet.setText(getIntent().getStringExtra("ord"));

        restart = findViewById(R.id.restart);
        restart.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        MediaPlayer mp = MediaPlayer.create(this, R.raw.losing);
        mp.start();
    }

    @Override
    public void onClick(View v) {
        logik.nulstil();
        Intent myIntent = new Intent(LoseActivity.this, gameActivity.class);
        LoseActivity.this.startActivity(myIntent);

    }
}
