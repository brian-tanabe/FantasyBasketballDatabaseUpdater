package com.btanabe2.fbdu.dm.models;

import java.sql.Date;

/**
 * Created by brian on 11/5/14.
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "player_biography", schema = "public", catalog = "FANTASY_BASKETBALL")
public class PlayerBiographyEntity {
    private int id;
    private String name;
    private Date birthday;
    private int experience;
    private int espnid;
    private int yahooid;
    private int numberfireid;
    private int nbateamid;

    @javax.persistence.Id
    @javax.persistence.Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "birthday")
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "experience")
    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "espnid")
    public int getEspnid() {
        return espnid;
    }

    public void setEspnid(int espnid) {
        this.espnid = espnid;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "yahooid")
    public int getYahooid() {
        return yahooid;
    }

    public void setYahooid(int yahooid) {
        this.yahooid = yahooid;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "numberfireid")
    public int getNumberfireid() {
        return numberfireid;
    }

    public void setNumberfireid(int numberfireid) {
        this.numberfireid = numberfireid;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "nbateamid")
    public int getNbateamid() {
        return nbateamid;
    }

    public void setNbateamid(int nbateamid) {
        this.nbateamid = nbateamid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerBiographyEntity that = (PlayerBiographyEntity) o;

        if (espnid != that.espnid) return false;
        if (experience != that.experience) return false;
        if (id != that.id) return false;
        if (name != that.name) return false;
        if (nbateamid != that.nbateamid) return false;
        if (numberfireid != that.numberfireid) return false;
        if (yahooid != that.yahooid) return false;
        if (birthday != null ? !birthday.equals(that.birthday) : that.birthday != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + experience;
        result = 31 * result + espnid;
        result = 31 * result + yahooid;
        result = 31 * result + numberfireid;
        result = 31 * result + nbateamid;
        return result;
    }

    @Override
    public String toString() {
        return String.format("name=[%s], espnID=[%d], yahooID=[%d], numberFireID=[%d], nbaTeamID=[%d], experience=[%d], birthday[%s], ID=[%d]", name, espnid, yahooid, numberfireid, nbateamid, experience, birthday.toString(), id);
    }
}
