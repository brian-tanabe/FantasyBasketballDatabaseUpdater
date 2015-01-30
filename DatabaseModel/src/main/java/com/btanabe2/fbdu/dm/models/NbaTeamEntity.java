package com.btanabe2.fbdu.dm.models;

import javax.persistence.*;

/**
 * Created by brian on 11/7/14.
 */
@Entity
@Table(name = "nba_team")
public class NbaTeamEntity {
    private int id;
    private int espnId;
    private int numberFireId;
    private String location;
    private String abbreviation;
    private String name;

    public NbaTeamEntity(){}

    public NbaTeamEntity(int id, String abbreviation){ this.id = id; this.abbreviation = abbreviation; }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "espn_id")
    public int getEspnId() { return espnId; }

    public void setEspnId(int espnId) { this.espnId = espnId; }

    @Basic
    @Column(name = "number_fire_id")
    public int getNumberFireId() { return numberFireId; }

    public void setNumberFireId(int numberFireId) { this.numberFireId = numberFireId; }

    @Basic
    @Column(name = "location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Basic
    @Column(name = "abbreviation")
    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NbaTeamEntity that = (NbaTeamEntity) o;

        if (id != that.id) return false;
        if (abbreviation != null ? !abbreviation.equals(that.abbreviation) : that.abbreviation != null) return false;
        if (location != null ? !location.equals(that.location) : that.location != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (abbreviation != null ? abbreviation.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("abbreviation=[%s]; id=[%d]; espnId=[%d]; numberFireId=[%d]; name=[%s]; location=[%s]", abbreviation, id, espnId, numberFireId, name, location);
    }
}
