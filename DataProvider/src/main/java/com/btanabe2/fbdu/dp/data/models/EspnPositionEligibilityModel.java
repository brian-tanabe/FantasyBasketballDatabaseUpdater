package com.btanabe2.fbdu.dp.data.models;

/**
 * Created by Brian on 2/2/15.
 */
public class EspnPositionEligibilityModel {
    private int espnPlayerId;
    private int positionId;

    public EspnPositionEligibilityModel(int espnPlayerId, int positionId) {
        this.espnPlayerId = espnPlayerId;
        this.positionId = positionId;
    }

    public int getPositionId() {
        return positionId;
    }

    public int getEspnPlayerId() {
        return espnPlayerId;
    }

    @Override
    public String toString() {
        return String.format("espnPlayerId=[%d]; positionId=[%d]", espnPlayerId, positionId);
    }
}
