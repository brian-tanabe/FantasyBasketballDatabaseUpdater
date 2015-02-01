package com.btanabe2.fbdu.dm.models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by brian on 11/5/14.
 */
public class NfDailyProjectionsEntityPK implements Serializable {
    private int playerid;
    private Date date;

    @Column(name = "playerid")
    @Id
    public int getPlayerid() {
        return playerid;
    }

    public void setPlayerid(int playerid) {
        this.playerid = playerid;
    }

    @Column(name = "date")
    @Id
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NfDailyProjectionsEntityPK that = (NfDailyProjectionsEntityPK) o;

        if (playerid != that.playerid) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = playerid;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}
