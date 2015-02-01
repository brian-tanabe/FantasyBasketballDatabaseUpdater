package com.btanabe2.fbdu.dm.models;

import javax.persistence.*;

/**
 * Created by brian on 11/5/14.
 */
@Entity
@Table(name = "nf_remaining_season_projections")
public class NfRemainingSeasonProjectionsEntity {
    private int playerid;
    private int points;
    private int minutes;
    private int fieldgoalsmade;
    private int fieldgoalsattempted;
    private int freethrowsmade;
    private int freethrowsattempted;
    private int threepointersmade;
    private int threepointersattempted;
    private int rebounds;
    private int assists;
    private int steals;
    private int blocks;
    private int turnovers;

    @Id
    @Column(name = "playerid")
    public int getPlayerid() {
        return playerid;
    }

    public void setPlayerid(int playerid) {
        this.playerid = playerid;
    }

    @Basic
    @Column(name = "points")
    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Basic
    @Column(name = "minutes")
    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    @Basic
    @Column(name = "fieldgoalsmade")
    public int getFieldgoalsmade() {
        return fieldgoalsmade;
    }

    public void setFieldgoalsmade(int fieldgoalsmade) {
        this.fieldgoalsmade = fieldgoalsmade;
    }

    @Basic
    @Column(name = "fieldgoalsattempted")
    public int getFieldgoalsattempted() {
        return fieldgoalsattempted;
    }

    public void setFieldgoalsattempted(int fieldgoalsattempted) {
        this.fieldgoalsattempted = fieldgoalsattempted;
    }

    @Basic
    @Column(name = "freethrowsmade")
    public int getFreethrowsmade() {
        return freethrowsmade;
    }

    public void setFreethrowsmade(int freethrowsmade) {
        this.freethrowsmade = freethrowsmade;
    }

    @Basic
    @Column(name = "freethrowsattempted")
    public int getFreethrowsattempted() {
        return freethrowsattempted;
    }

    public void setFreethrowsattempted(int freethrowsattempted) {
        this.freethrowsattempted = freethrowsattempted;
    }

    @Basic
    @Column(name = "threepointersmade")
    public int getThreepointersmade() {
        return threepointersmade;
    }

    public void setThreepointersmade(int threepointersmade) {
        this.threepointersmade = threepointersmade;
    }

    @Basic
    @Column(name = "threepointersattempted")
    public int getThreepointersattempted() {
        return threepointersattempted;
    }

    public void setThreepointersattempted(int threepointersattempted) {
        this.threepointersattempted = threepointersattempted;
    }

    @Basic
    @Column(name = "rebounds")
    public int getRebounds() {
        return rebounds;
    }

    public void setRebounds(int rebounds) {
        this.rebounds = rebounds;
    }

    @Basic
    @Column(name = "assists")
    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    @Basic
    @Column(name = "steals")
    public int getSteals() {
        return steals;
    }

    public void setSteals(int steals) {
        this.steals = steals;
    }

    @Basic
    @Column(name = "blocks")
    public int getBlocks() {
        return blocks;
    }

    public void setBlocks(int blocks) {
        this.blocks = blocks;
    }

    @Basic
    @Column(name = "turnovers")
    public int getTurnovers() {
        return turnovers;
    }

    public void setTurnovers(int turnovers) {
        this.turnovers = turnovers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NfRemainingSeasonProjectionsEntity that = (NfRemainingSeasonProjectionsEntity) o;

        if (assists != that.assists) return false;
        if (blocks != that.blocks) return false;
        if (fieldgoalsattempted != that.fieldgoalsattempted) return false;
        if (fieldgoalsmade != that.fieldgoalsmade) return false;
        if (freethrowsattempted != that.freethrowsattempted) return false;
        if (freethrowsmade != that.freethrowsmade) return false;
        if (minutes != that.minutes) return false;
        if (playerid != that.playerid) return false;
        if (points != that.points) return false;
        if (rebounds != that.rebounds) return false;
        if (steals != that.steals) return false;
        if (threepointersattempted != that.threepointersattempted) return false;
        if (threepointersmade != that.threepointersmade) return false;
        if (turnovers != that.turnovers) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = playerid;
        result = 31 * result + points;
        result = 31 * result + minutes;
        result = 31 * result + fieldgoalsmade;
        result = 31 * result + fieldgoalsattempted;
        result = 31 * result + freethrowsmade;
        result = 31 * result + freethrowsattempted;
        result = 31 * result + threepointersmade;
        result = 31 * result + threepointersattempted;
        result = 31 * result + rebounds;
        result = 31 * result + assists;
        result = 31 * result + steals;
        result = 31 * result + blocks;
        result = 31 * result + turnovers;
        return result;
    }
}
