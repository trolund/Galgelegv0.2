package com.tinyappli.troelslund.galgeleg.data;

import android.os.AsyncTask;

import com.tinyappli.troelslund.galgeleg.Logik.Galgelogik;
import com.tinyappli.troelslund.galgeleg.data.DTO.HighscoreDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Created by troelslund on 05/11/2017.
 */

public class Data extends AsyncTask<String,String, List<String>> {

    private Galgelogik logik = Galgelogik.getInstance();
    public static ArrayList<HighscoreDTO> scoreList = new ArrayList();

    @Override
    protected List<String> doInBackground(String... strings) {
        ArrayList<String> words = new ArrayList<>();

        try {
            String data = logik.hentUrl("https://dr.dk");
            data = data.replaceAll("<.+?>", " ").toLowerCase().replaceAll("[^a-z]", " ");
            words.addAll(new HashSet<>(Arrays.asList(data.split(" "))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }

    @Override
    protected void onPostExecute(List<String> strings) {
        super.onPostExecute(strings);
        logik.setMuligeOrd(strings);
        System.out.print("færdig med at hente data.");

      //  for (int i = 0; i < strings.size(); i++)
        //    System.out.println(("Ord nr." + i + ": " + strings.get(i)));

        logik.nulstil();
        System.out.println("Ordet:" + logik.getOrdet());
    }

    public static ArrayList<HighscoreDTO> getScoreList() {
        return scoreList;
    }
}
