package com.btanabe2.fbdu.dp.models;

/**
 * Created by BTanabe on 11/13/2014.
 */
public class NumberFireNbaTeamModel {
    private int numberFireId;
    private int espnId;
    private String abbreviation;

    public NumberFireNbaTeamModel(String abbreviation, int numberFireId, int espnId){
        this.abbreviation = abbreviation;
        this.numberFireId = numberFireId;
        this.espnId = espnId;
    }

    public int getNumberFireId() {
        return numberFireId;
    }

    public void setNumberFireId(int numberFireId) {
        this.numberFireId = numberFireId;
    }

    public int getEspnId() {
        return espnId;
    }

    public void setEspnId(int espnId) {
        this.espnId = espnId;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    @Override
    public String toString() {
        return String.format("abbreviation=[%s]; numberFireId=[%d]; espnId=[%d]", abbreviation, numberFireId, espnId);
    }
}
