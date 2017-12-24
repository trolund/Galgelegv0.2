package com.tinyappli.troelslund.galgeleg.Activitys;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.tinyappli.troelslund.galgeleg.Logik.Galgelogik;
import com.tinyappli.troelslund.galgeleg.R;
import com.tinyappli.troelslund.galgeleg.data.Data;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{



    public Button startBut;
    public Button HightscroreBut;
    public Button helpBut;
    private Data data;
    private Galgelogik logik;

    private ImageView img;

    private int nyStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logik = Galgelogik.getInstance();

        startBut = (Button) findViewById(R.id.StartGame);
        startBut.setOnClickListener(this);

        helpBut = (Button) findViewById(R.id.help);
        helpBut.setOnClickListener(this);

        HightscroreBut = (Button) findViewById(R.id.Highscore);
        HightscroreBut.setOnClickListener(this);

        ConnectivityManager cm = (ConnectivityManager) getBaseContext().getSystemService(getBaseContext().CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) { // connected to the internet
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                // connected to wifi
                nyStatus = 1;
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                // connected to the mobile provider's data plan
                nyStatus = 2;
            }
        } else {
            // not connected to the internet
            nyStatus = 3;
        }

        data = new Data(getBaseContext());
        data.start(nyStatus);
    }



    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.StartGame:
                Intent myIntent = new Intent(MainActivity.this, GameTypeActivity.class);
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


    private void setIMGAni(int x){
        switch (x) {
            case 0:
                img.setImageResource(R.drawable.galge);
                break;
            case 1:
                img.setImageResource(R.drawable.forkert1);
                break;

            case 2:
                img.setImageResource(R.drawable.forkert2);
                break;

            case 3:
                img.setImageResource(R.drawable.forkert3);
                break;

            case 4:
                img.setImageResource(R.drawable.forkert4);
                break;

            case 5:
                img.setImageResource(R.drawable.forkert5);
                break;

            case 6:
                img.setImageResource(R.drawable.forkert6);
                break;
        }
    }

}

