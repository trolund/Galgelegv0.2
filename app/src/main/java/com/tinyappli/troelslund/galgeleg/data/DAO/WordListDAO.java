package com.tinyappli.troelslund.galgeleg.data.DAO;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tinyappli.troelslund.galgeleg.data.exceptions.DataException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by troelslund on 12/11/2017.
 */

public class WordListDAO {

    private static  WordListDAO singleton = new WordListDAO();

    private List<String> wordList = new ArrayList<String>();

    private final String MEMORY_KEY ="WORDLIST";

    private WordListDAO() {
    }

    public List<String> getWordList() {
        return wordList;
    }
    /*
        public void save(Context context , List<String> dtoList) {

            Gson gson = new Gson();
            wordList = dtoList;

            SharedPreferences preferences = context.getSharedPreferences(PREF_FILE, MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();

            String json = gson.toJson(wordList);

            SharedPreferences.Editor editor1 = editor.putString(MEMORY_KEY, json);

            editor.commit();
        }

        public List<String> load(Context context) {
            SharedPreferences preferences = context.getSharedPreferences(PREF_FILE, MODE_PRIVATE);

            String json = preferences.getString(MEMORY_KEY, null);

            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<String>>() {
            }.getType();

            wordList = gson.fromJson(json, type);

            System.out.printf(wordList.toString());

            return wordList;
        }
    */
    public static WordListDAO getInstance(){
        return singleton;
    }


    @Override
    public String toString() {
        return "WordListDAO{" +
                "wordList=" + wordList +
                ", MEMORY_KEY='" + MEMORY_KEY + '\'' +
                '}';
    }
}
