package com.tinyappli.troelslund.galgeleg.data.DAO;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tinyappli.troelslund.galgeleg.data.DTO.HighscoreDTO;
import com.tinyappli.troelslund.galgeleg.data.exceptions.DataException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by troelslund on 09/11/2017.
 */

public class HighscoreDAO implements IHighscoreDAO {

    private static HighscoreDAO singleton = new HighscoreDAO();

    private final String MEMORY_KEY ="highscore";

    public static final String PREF_FILE = "preferences";

    private HighscoreDAO() {
    }

    @Override
    public HighscoreDTO getById(int id) throws DataException {
/*
        if (highScoreList == null) {
            throw new DataException("der er ingen liste i hentet.");
        } else {
            for (HighscoreDTO listObj : highScoreList) {
                if (listObj.getId() == id) {
                    return listObj;
                }
            }
        }*/
    return null;
    }

    @Override
    public List<HighscoreDTO> getList() {
        return null;
    }

    @Override
    public void setHighscore(HighscoreDTO dto) {

    }

    @Override
    public void addList(List<HighscoreDTO> dtoList) throws DataException {

    }

    @Override
    public void save(Context context , HighscoreDTO dto) {

        Gson gson = new Gson();
        List<HighscoreDTO> list = load(context);

        if(list == null) {
            list = new ArrayList<HighscoreDTO>();
        }

        list.add(dto);

        SharedPreferences preferences = context.getSharedPreferences(PREF_FILE, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        String json = gson.toJson(list);

        SharedPreferences.Editor editor1 = editor.putString(MEMORY_KEY, json);

        editor.commit();
    }

    @Override
    public List<HighscoreDTO> load(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_FILE, MODE_PRIVATE);

        String json = preferences.getString(MEMORY_KEY, null);

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<HighscoreDTO>>() {
        }.getType();

        return gson.fromJson(json, type);
    }

    public static HighscoreDAO getInstance(){
        return singleton;
    }

}
