package com.btanabe2.fbdu.dm.models;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by brian on 11/5/14.
 */
@Entity
@Table(name = "nf_daily_projections")
@IdClass(NfDailyProjectionsEntityPK.class)
public class NfDailyProjectionsEntity {
    private int playerid;
    private Date date;
    private int opponent;
    private double minutes;
    private double points;
    private double rebounds;
    private double assists;
    private double steals;
    private double firepoints;

    @Id
    @Column(name = "playerid")
    public int getPlayerid() {
        return playerid;
    }

    public void setPlayerid(int playerid) {
        this.playerid = playerid;
    }

    @Id
    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "opponent")
    public int getOpponent() {
        return opponent;
    }

    public void setOpponent(int opponent) {
        this.opponent = opponent;
    }

    @Basic
    @Column(name = "minutes")
    public double getMinutes() {
        return minutes;
    }

    public void setMinutes(double minutes) {
        this.minutes = minutes;
    }

    @Basic
    @Column(name = "points")
    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    @Basic
    @Column(name = "rebounds")
    public double getRebounds() {
        return rebounds;
    }

    public void setRebounds(double rebounds) {
        this.rebounds = rebounds;
    }

    @Basic
    @Column(name = "assists")
    public double getAssists() {
        return assists;
    }

    public void setAssists(double assists) {
        this.assists = assists;
    }

    @Basic
    @Column(name = "steals")
    public double getSteals() {
        return steals;
    }

    public void setSteals(double steals) {
        this.steals = steals;
    }

    @Basic
    @Column(name = "firepoints")
    public double getFirepoints() {
        return firepoints;
    }

    public void setFirepoints(double firepoints) {
        this.firepoints = firepoints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NfDailyProjectionsEntity that = (NfDailyProjectionsEntity) o;

        if (Double.compare(that.assists, assists) != 0) return false;
        if (Double.compare(that.firepoints, firepoints) != 0) return false;
        if (Double.compare(that.minutes, minutes) != 0) return false;
        if (opponent != that.opponent) return false;
        if (playerid != that.playerid) return false;
        if (Double.compare(that.points, points) != 0) return false;
        if (Double.compare(that.rebounds, rebounds) != 0) return false;
        if (Double.compare(that.steals, steals) != 0) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = playerid;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + opponent;
        temp = Double.doubleToLongBits(minutes);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(points);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(rebounds);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(assists);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(steals);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(firepoints);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
