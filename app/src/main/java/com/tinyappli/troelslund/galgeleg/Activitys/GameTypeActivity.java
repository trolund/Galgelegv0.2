package com.tinyappli.troelslund.galgeleg.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tinyappli.troelslund.galgeleg.Logik.Galgelogik;
import com.tinyappli.troelslund.galgeleg.R;

public class GameTypeActivity extends AppCompatActivity implements View.OnClickListener {

    private Button randdom;
    private Button ownWord;
    private Button listWord;
    private Galgelogik logik = Galgelogik.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_type);

        randdom = findViewById(R.id.ranWord_but);
        ownWord = findViewById(R.id.ownWord_but);
        listWord = findViewById(R.id.listWord_but);

        randdom.setOnClickListener(this);
        ownWord.setOnClickListener(this);
        listWord.setOnClickListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Spil muligheder");
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.ranWord_but:
                logik.nulstil();
                Intent myIntent = new Intent(GameTypeActivity.this, gameActivity.class);
                GameTypeActivity.this.startActivity(myIntent);
                break;
            case R.id.ownWord_but:
                Intent myIntent2 = new Intent(GameTypeActivity.this, OwnWordActivity.class);
                GameTypeActivity.this.startActivity(myIntent2);
                break;
            case R.id.listWord_but:
                Intent myIntent3 = new Intent(GameTypeActivity.this, WordListActivity.class);
                GameTypeActivity.this.startActivity(myIntent3);
                break;
        }
    }
}
