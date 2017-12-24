package com.tinyappli.troelslund.galgeleg.Activitys;

import android.content.Intent;
import android.graphics.drawable.TransitionDrawable;
import android.media.MediaPlayer;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;

import com.tinyappli.troelslund.galgeleg.Logik.Galgelogik;
import com.tinyappli.troelslund.galgeleg.data.DAO.HighscoreDAO;
import com.tinyappli.troelslund.galgeleg.data.DTO.HighscoreDTO;
import com.tinyappli.troelslund.galgeleg.R;

import java.util.ArrayList;
import java.util.Date;

public class gameActivity extends AppCompatActivity implements View.OnClickListener {

  //  private galgeleg.Galgelogik logik = new galgeleg.Galgelogik();

    public Galgelogik logik;

    public Button menuBut;
    public Chronometer time;
    public TextView Showord;
    public String synligtord;
    public ImageView img;
    public TextView liv;


    private final Button[] btnABCArray = new Button[30];
    private final Integer[] btnABCIdArray = {R.id.buttonA, R.id.buttonB, R.id.buttonC, R.id.buttonD, R.id.buttonE, R.id.buttonF, R.id.buttonG, R.id.buttonH, R.id.buttonI, R.id.buttonJ, R.id.buttonK, R.id.buttonL, R.id.buttonM, R.id.buttonN, R.id.buttonO, R.id.buttonP, R.id.buttonQ, R.id.buttonR, R.id.buttonS, R.id.buttonT, R.id.buttonU, R.id.buttonV, R.id.buttonW, R.id.buttonX, R.id.buttonY, R.id.buttonZ, R.id.buttonEA, R.id.buttonAA, R.id.buttonOE, R.id.buttonHint};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        logik = Galgelogik.getInstance();

        img = (ImageView) findViewById(R.id.imageView);

        synligtord = logik.getSynligtOrd();
        System.out.println(logik.getOrdet());

        Showord = (TextView) findViewById(R.id.textView);
        Showord.setText(synligtord);

        time = (Chronometer) findViewById(R.id.chronometer3);
        time.start();

        liv = (TextView) findViewById(R.id.livantal);
        liv.setText("6");

        menuBut = (Button) findViewById(R.id.menu);
        menuBut.setOnClickListener(this);


        for (int i = 0; i < btnABCArray.length; i++) {
            btnABCArray[i] = (Button) findViewById(btnABCIdArray[i]);
            btnABCArray[i].setOnClickListener(this);

        }


    }
    @Override
    protected void onStart() {
        super.onStart();
        Showord.setText(logik.getSynligtOrd());
    };

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.menu:
                logik.nulstil();
                Intent myIntent = new Intent(gameActivity.this, MainActivity.class);
                gameActivity.this.startActivity(myIntent);
                break;

            default:

                String bogstav = "" +((Button) view).getText().toString().toLowerCase().charAt(0);
                if(bogstav.equals("?")){
                    bogstav = Hint();
                }

                logik.gætBogstav(bogstav);
                Showord.setText(logik.getSynligtOrd());
                liv.setText("" + (6-logik.getAntalForkerteBogstaver()));
                setIMG();
                view.setEnabled(false);

                if (logik.erSidsteBogstavKorrekt()){
                    MediaPlayer mp = MediaPlayer.create(this, R.raw.winning);
                    mp.start();
                }
                else{
                    MediaPlayer mp = MediaPlayer.create(this, R.raw.losing);
                    mp.start();
                }


                if (logik.getAntalForkerteBogstaver() == 6){
                    time.stop();
                    deActivetABCBut();
                    Intent losserIntent = new Intent(gameActivity.this, LoseActivity.class);
                    losserIntent.putExtra("ord", logik.getOrdet());
                    gameActivity.this.startActivity(losserIntent);
                } else if(logik.erSpilletVundet()){
                    time.stop();
                    deActivetABCBut();
                    Intent winnerIntent = new Intent(gameActivity.this, WinningActivity.class);
                    String Score = BeregnScore();
                    String Antalforsøg = "" + (logik.getAntalForkerteBogstaver() + logik.getOrdet().length());
                    winnerIntent.putExtra("Score", Score);
                    winnerIntent.putExtra("Antalforsøg", Antalforsøg);

                    HighscoreDAO dao = HighscoreDAO.getInstance();
                    dao.save(getBaseContext(), new HighscoreDTO(Score, logik.getOrdet(), new Date()));
                    gameActivity.this.startActivity(winnerIntent);
                }
                break;
        }

    }

    private void setIMG(){
        switch (logik.getAntalForkerteBogstaver()) {
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

    private String getBrugteBogstaver(){
        ArrayList<String> bogstaver = logik.getBrugteBogstaver();
        StringBuilder stringBuilder = new StringBuilder();

        for (String s: bogstaver) {
            stringBuilder.append(s + ", ");
        }

        return stringBuilder.toString();
    }

    private String BeregnScore(){
        int ordLength = logik.getOrdet().length();

        return "" + (ordLength * 10);
    }

    private void deActivetABCBut(){
        for (int i = 0; i < btnABCArray.length; i++) {
            btnABCArray[i].setEnabled(false);

        }
    }

    private String Hint(){
        String ordet = logik.getOrdet();
        String hintBogstav;

        for(int i=0; i < ordet.length(); i++) {
            hintBogstav = "" + ordet.charAt(i);
            if(!getBrugteBogstaver().contains(hintBogstav)) {


                for (int x = 0; x < btnABCArray.length; x++) {
                    String buttonBogstav = "" + btnABCArray[x].getText().toString().toLowerCase().charAt(0);

                    if(buttonBogstav.equals(hintBogstav)){
                        btnABCArray[x].setEnabled(false);
                    }
                }


                return hintBogstav;
            }


        }


    return "";
    }






}

