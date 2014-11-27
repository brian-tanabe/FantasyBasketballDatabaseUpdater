package com.btanabe2.fbdu.dm.models;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by brian on 11/20/14.
 */
@Entity
@Table(name = "player_biography", schema = "public", catalog = "FANTASY_BASKETBALL")
public class PlayerBiographyEntity {
    private int id;
    private String name;
    private Date birthday;
    private int experience;
    private int espnid;
    private int numberfireid;
    private int nbateamid;
    private int height;
    private int weight;
    private String country;
    private String school;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "birthday")
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Basic
    @Column(name = "experience")
    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    @Basic
    @Column(name = "espnid")
    public int getEspnid() {
        return espnid;
    }

    public void setEspnid(int espnid) {
        this.espnid = espnid;
    }

    @Basic
    @Column(name = "numberfireid")
    public int getNumberfireid() {
        return numberfireid;
    }

    public void setNumberfireid(int numberfireid) {
        this.numberfireid = numberfireid;
    }

    @Basic
    @Column(name = "nbateamid")
    public int getNbateamid() {
        return nbateamid;
    }

    public void setNbateamid(int nbateamid) {
        this.nbateamid = nbateamid;
    }

    @Basic
    @Column(name = "height")
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Basic
    @Column(name = "weight")
    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Basic
    @Column(name = "country")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Basic
    @Column(name = "school")
    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerBiographyEntity that = (PlayerBiographyEntity) o;

        if (espnid != that.espnid) return false;
        if (experience != that.experience) return false;
        if (height != that.height) return false;
        if (id != that.id) return false;
        if (nbateamid != that.nbateamid) return false;
        if (numberfireid != that.numberfireid) return false;
        if (weight != that.weight) return false;
        if (birthday != null ? !birthday.equals(that.birthday) : that.birthday != null) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (school != null ? !school.equals(that.school) : that.school != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + experience;
        result = 31 * result + espnid;
        result = 31 * result + numberfireid;
        result = 31 * result + nbateamid;
        result = 31 * result + height;
        result = 31 * result + weight;
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (school != null ? school.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("id=[%d]; name=[%s]; birthday=[%s]; experience=[%d], nbaTeamId=[%d]; espnId=[%d]; numberFireId=[%d]; height=[%d]; weight=[%d]; country=[%s]; school=[%s]", id, name, birthday.toString(), experience, nbateamid, espnid, numberfireid, height, weight, country, school);
    }
}
