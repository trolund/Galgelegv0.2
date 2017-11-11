package com.tinyappli.troelslund.galgeleg.data.DAO;

import android.content.Context;

import com.tinyappli.troelslund.galgeleg.data.DTO.HighscoreDTO;
import com.tinyappli.troelslund.galgeleg.data.exceptions.DataException;

import java.util.List;

/**
 * Created by troelslund on 09/11/2017.
 */

public interface IHighscoreDAO {

    public HighscoreDTO getById(int id) throws DataException;

    public List<HighscoreDTO> getList() throws DataException;

    public void setHighscore(HighscoreDTO dto) throws DataException;

    public void addList(List<HighscoreDTO> dtoList) throws DataException;

    public void save(Context context, HighscoreDTO dto) throws DataException;

    public List<HighscoreDTO> load(Context context) throws DataException;


}
