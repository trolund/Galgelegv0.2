package com.tinyappli.troelslund.galgeleg.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tinyappli.troelslund.galgeleg.Logik.Galgelogik;
import com.tinyappli.troelslund.galgeleg.R;

public class OwnWordActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText ord;
    private Button spilbut;
    private Galgelogik logik = Galgelogik.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_own_word);

        ord = findViewById(R.id.editText_Word);

        spilbut = findViewById(R.id.PlayOwnWord_but);
        spilbut.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        logik.nultilMedOrd(logik.setOrd(ord.getText().toString().toLowerCase()));
        Intent myIntent = new Intent(OwnWordActivity.this, gameActivity.class);
        OwnWordActivity.this.startActivity(myIntent);
    }
}
