package com.btanabe2.fbdu.dm.models;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

/**
 * Created by brian on 11/5/14.
 */
@Entity
@Table(name = "position_eligibility")
public class PositionEligibilityEntity {
    private int id;
    private int playerid;
    private int positionid;

    public PositionEligibilityEntity(int playerId, int positionId) {
        this.playerid = playerId;
        this.positionid = positionId;
    }

    public PositionEligibilityEntity() {
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "playerid")
    public int getPlayerid() {
        return playerid;
    }

    public void setPlayerid(int playerid) {
        this.playerid = playerid;
    }

    @Basic
    @Column(name = "positionid")
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
