package com.tinyappli.troelslund.galgeleg.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tinyappli.troelslund.galgeleg.Logik.Galgelogik;
import com.tinyappli.troelslund.galgeleg.R;

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
