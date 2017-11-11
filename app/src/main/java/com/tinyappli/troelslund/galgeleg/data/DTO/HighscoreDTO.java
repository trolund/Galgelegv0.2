package com.tinyappli.troelslund.galgeleg.data.DTO;

import com.tinyappli.troelslund.galgeleg.data.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by troelslund on 08/11/2017.
 */

public class HighscoreDTO {

    private int id;
    private String score;
    private String ord;
    private Date dato;



    public HighscoreDTO(String score, String ord, Date dato) {
        this.score = score;
        this.ord = ord;
        this.dato = dato;
    }


    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getOrd() {
        return ord;
    }

    public void setOrd(String ord) {
        this.ord = ord;
    }

    public Date getDato() {
        return dato;
    }

    public void setDato(Date dato) {
        this.dato = dato;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "HighscoreDTO{" +
                "id=" + id +
                ", score='" + score + '\'' +
                ", ord='" + ord + '\'' +
                ", dato=" + dato +
                '}';
    }

    public int compare(HighscoreDTO dto1, HighscoreDTO dto2) {
        return Integer.compare(Integer.parseInt(dto1.getScore()),Integer.parseInt(dto2.getScore()));
    }
}
