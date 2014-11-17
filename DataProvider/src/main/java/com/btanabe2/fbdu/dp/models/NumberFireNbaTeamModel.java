package com.btanabe2.fbdu.dp.models;

/**
 * Created by BTanabe on 11/13/2014.
 */
public class NumberFireNbaTeamModel {
    private int numberFireId;
    private int espnId;
    private String name;

    public NumberFireNbaTeamModel(String name, int numberFireId, int espnId){
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
