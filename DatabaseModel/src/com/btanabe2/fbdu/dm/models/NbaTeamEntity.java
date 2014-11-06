package com.btanabe2.fbdu.dm.models;

/**
 * Created by brian on 11/5/14.
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "nba_team", schema = "public", catalog = "FANTASY_BASKETBALL")
public class NbaTeamEntity {
    private int id;
    private int location;
    private int abbreviation;
    private int name;

    @javax.persistence.Id
    @javax.persistence.Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "location")
    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "abbreviation")
    public int getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(int abbreviation) {
        this.abbreviation = abbreviation;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "name")
    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NbaTeamEntity that = (NbaTeamEntity) o;

        if (abbreviation != that.abbreviation) return false;
        if (id != that.id) return false;
        if (location != that.location) return false;
        if (name != that.name) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + location;
        result = 31 * result + abbreviation;
        result = 31 * result + name;
        return result;
    }
}
