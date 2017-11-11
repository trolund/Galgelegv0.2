package com.tinyappli.troelslund.galgeleg.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tinyappli.troelslund.galgeleg.R;
import com.tinyappli.troelslund.galgeleg.data.DAO.HighscoreDAO;
import com.tinyappli.troelslund.galgeleg.data.DTO.HighscoreDTO;
import com.tinyappli.troelslund.galgeleg.data.Data;

import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String PREF_FILE = "preferences";

    public Button startBut;
    public Button HightscroreBut;
    public Button helpBut;
    private Data data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startBut = (Button) findViewById(R.id.StartGame);
        startBut.setOnClickListener(this);

        helpBut = (Button) findViewById(R.id.help);
        helpBut.setOnClickListener(this);

        HightscroreBut = (Button) findViewById(R.id.Highscore);
        HightscroreBut.setOnClickListener(this);


        data = new Data();
        data.execute();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.StartGame:
                Intent myIntent = new Intent(MainActivity.this, gameActivity.class);
                MainActivity.this.startActivity(myIntent);
                break;

            case R.id.Highscore:
                Intent myIntent2 = new Intent(MainActivity.this, highscores.class);
                MainActivity.this.startActivity(myIntent2);
                break;

            case R.id.help:
                Intent myIntent1 = new Intent(MainActivity.this, HelpActivity.class);
                MainActivity.this.startActivity(myIntent1);
                break;

            default:
                break;
        }
    }
}

