package com.btanabe2.fbdu.dm.models;

/**
 * Created by brian on 11/5/14.
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "position_eligibility", schema = "public", catalog = "FANTASY_BASKETBALL")
public class PositionEligibilityEntity {
    private int id;
    private int playerid;
    private int positionid;

    @javax.persistence.Id
    @javax.persistence.Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "playerid")
    public int getPlayerid() {
        return playerid;
    }

    public void setPlayerid(int playerid) {
        this.playerid = playerid;
    }

    @javax.persistence.Basic
    @javax.persistence.Column(name = "positionid")
    public int getPositionid() {
        return positionid;
    }

    public void setPositionid(int positionid) {
        this.positionid = positionid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PositionEligibilityEntity that = (PositionEligibilityEntity) o;

        if (id != that.id) return false;
        if (playerid != that.playerid) return false;
        if (positionid != that.positionid) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + playerid;
        result = 31 * result + positionid;
        return result;
    }
}
