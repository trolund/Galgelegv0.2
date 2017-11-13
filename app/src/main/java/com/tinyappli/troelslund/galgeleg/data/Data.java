package com.tinyappli.troelslund.galgeleg.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tinyappli.troelslund.galgeleg.Logik.Galgelogik;
import com.tinyappli.troelslund.galgeleg.data.DAO.HighscoreDAO;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by troelslund on 05/11/2017.
 */

public class Data extends AsyncTask<String,String, List<String>> {

    private Galgelogik logik = Galgelogik.getInstance();
    public static List<String> wordList;
    private final String URL ="https://dr.dk";
    private final String MEMORY_KEY ="WORDLIST";
    private Context context;

    private static boolean downloadstatus = true; // true så er der ikke blevet hentet noget da app åbnet. false så er der henter og det behøver ikke ske igen.

    public static final String WORDLIST_FILE = "wordlistfile";

    public Data(Context context) {
        this.context = context;
    }

    public void start(int status){
        if((status == 1 || status == 2) && downloadstatus){
            System.out.println("downloadstatus = " + downloadstatus);
            execute();
        } else if(status == 3 && downloadstatus){
            wordList = load();
            if (wordList == null || wordList.size() < 1){
                wordList = new ArrayList<String>();
                wordList.add("Ord");
                Toast toast = Toast.makeText(context, "Der er ingen ord i hukommelsen, og kan ikke hente fra " + URL, Toast.LENGTH_SHORT);
                toast.show();
            } else{
                logik.setMuligeOrd(wordList);
                logik.nulstil();
            }
        }
    }


    @Override
    protected List<String> doInBackground(String... strings) {
        ArrayList<String> words = new ArrayList<>();

        try {
            String data = logik.hentUrl(URL);
            data = data.replaceAll("<.+?>", " ").toLowerCase().replaceAll("[^a-z]", " ");
            words.addAll(new HashSet<>(Arrays.asList(data.split(" "))));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return words;
    }

    @Override
    protected void onPostExecute(List<String> strings) {
        super.onPostExecute(strings);

        if(strings == null || strings.size() < 1){
            wordList = load();

            Toast toast2 = Toast.makeText(context, "Der blev ikke hentet fra " + URL + ", men fra memory!", Toast.LENGTH_SHORT);
            toast2.show();

            if (wordList == null || wordList.size() < 1){
                wordList = new ArrayList<String>();
                wordList.add("Ord");
                Toast toast = Toast.makeText(context, "Der er ingen ord i hukommelsen, og kan ikke hente fra " + URL, Toast.LENGTH_SHORT);
                toast.show();
            }
        } else {
            wordList = strings;
            save(wordList);
            downloadstatus = false;
            Toast toast2 = Toast.makeText(context, "Færdig med at hente data, fra" + URL + "og det ordliste er gemt i memory.", Toast.LENGTH_LONG);
            toast2.show();
        }
            logik.setMuligeOrd(wordList);
            System.out.print("færdig med at hente data. fra URL: " + URL);

            logik.nulstil();
            System.out.println("Ordet:" + logik.getOrdet());
    }

    public static List<String> getWordList() {
        return wordList;
    }

    public void save(List<String> dtoList) {

        Gson gson = new Gson();
        wordList = dtoList;

        SharedPreferences preferences = context.getSharedPreferences(WORDLIST_FILE, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        String json = gson.toJson(wordList);

        SharedPreferences.Editor editor1 = editor.putString(MEMORY_KEY, json);

        editor.commit();
    }

    public List<String> load() {
        SharedPreferences preferences = context.getSharedPreferences(WORDLIST_FILE, MODE_PRIVATE);

        String json = preferences.getString(MEMORY_KEY, null);

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<String>>() {
        }.getType();

        wordList = gson.fromJson(json, type);

        System.out.printf(wordList.toString());

        return wordList;
    }


}
