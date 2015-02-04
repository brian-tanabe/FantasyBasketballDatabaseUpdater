package com.btanabe2.fbdu.dp.data.models;

/**
 * Created by BTanabe on 11/21/2014.
 */
public class NbaTeamSportsVuModel {
    private int id;
    private String abbreviation;

    public NbaTeamSportsVuModel(int id, String abbreviation) {
        this.id = id;
        this.abbreviation = abbreviation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    @Override
    public String toString() {
        return String.format("abbreviation=[%s]; id=[%d]", abbreviation, id);
    }
}
