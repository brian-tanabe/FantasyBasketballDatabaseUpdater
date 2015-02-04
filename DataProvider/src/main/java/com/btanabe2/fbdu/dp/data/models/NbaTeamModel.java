package com.btanabe2.fbdu.dp.data.models;

/**
 * Created by brian on 11/7/14.
 */
public class NbaTeamModel {
    public String location;
    public String name;
    public String abbreviation;
    public String numberFireAbbreviation;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getNumberFireAbbreviation() {
        return numberFireAbbreviation;
    }

    public void setNumberFireAbbreviation(String numberFireAbbreviation) {
        this.numberFireAbbreviation = numberFireAbbreviation;
    }

    @Override
    public String toString() {
        return String.format("abbreviation=[%s]; numberFireAbbreviation=[%s]; name=[%s]; location=[%s]", abbreviation, numberFireAbbreviation, name, location);
    }
}
