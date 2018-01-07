package com.tinyappli.troelslund.galgeleg.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.tinyappli.troelslund.galgeleg.Adapters.ListAdapter;
import com.tinyappli.troelslund.galgeleg.R;
import com.tinyappli.troelslund.galgeleg.data.DAO.HighscoreDAO;
import com.tinyappli.troelslund.galgeleg.data.DAO.IHighscoreDAO;
import com.tinyappli.troelslund.galgeleg.data.DTO.HighscoreDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class highscores extends AppCompatActivity {

    public ListView view;
    public ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscores);

        IHighscoreDAO dao = HighscoreDAO.getInstance();

        List<HighscoreDTO> daoList = null;
        try {
            daoList = dao.load(getBaseContext());
            System.out.println("DAO Liste!!!!!!!!!!!!!!!");
            System.out.println(daoList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(daoList == null){
            daoList = new ArrayList<HighscoreDTO>();
           // daoList.add(new HighscoreDTO("0", "Ingen ord er gemt endnu", new Date()));
            Toast toast = Toast.makeText(getBaseContext(), "Ingen ord er gemt endnu", Toast.LENGTH_LONG);
            toast.show();
        }

        Collections.sort(daoList, new Comparator<HighscoreDTO>() {
            public int compare(HighscoreDTO dto1, HighscoreDTO dto2) {
                return Integer.compare(Integer.parseInt(dto2.getScore()),Integer.parseInt(dto1.getScore()));
            }
        });

        adapter = new ListAdapter(getBaseContext(), R.layout.listeelement, daoList);

        view = findViewById(R.id.listView);

        view.setAdapter(adapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Highscores");
    }
}