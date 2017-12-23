package com.tinyappli.troelslund.galgeleg.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.tinyappli.troelslund.galgeleg.Adapters.ListAdapter;
import com.tinyappli.troelslund.galgeleg.Logik.Galgelogik;
import com.tinyappli.troelslund.galgeleg.R;
import com.tinyappli.troelslund.galgeleg.data.DAO.HighscoreDAO;
import com.tinyappli.troelslund.galgeleg.data.DAO.IHighscoreDAO;
import com.tinyappli.troelslund.galgeleg.data.DTO.HighscoreDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class WordListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    public ListView view;
    public ArrayAdapter adapter;
    private Galgelogik logik = Galgelogik.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_list);

        adapter = new ArrayAdapter(getBaseContext(), android.R.layout.simple_list_item_1, logik.getMuligeOrd());

        view = findViewById(R.id.WordListview);
        view.setOnItemClickListener(this);

        view.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            logik.nultilMedOrd(position);
            Intent myIntent = new Intent(WordListActivity.this, gameActivity.class);
            WordListActivity.this.startActivity(myIntent);
    }
}
