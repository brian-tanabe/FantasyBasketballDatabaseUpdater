package com.btanabe2.fbdu.du.updaters;

import com.btanabe2.fbdu.dm.models.PositionsEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brian on 11/15/14.
 */
public class PositionFactory {

    public static List<PositionsEntity> getAllPositions(){
        List<PositionsEntity> positions = new ArrayList<>(5);

        PositionsEntity pointGuard = new PositionsEntity();
        pointGuard.setId(1);
        pointGuard.setAbbreviation("PG");
        pointGuard.setFullname("Point Guard");

        PositionsEntity shootingGuard = new PositionsEntity();
        shootingGuard.setId(2);
        shootingGuard.setAbbreviation("SG");
        shootingGuard.setFullname("Shooting Guard");

        PositionsEntity smallForward = new PositionsEntity();
        smallForward.setId(3);
        smallForward.setAbbreviation("SF");
        smallForward.setFullname("Small Forward");

        PositionsEntity powerForward = new PositionsEntity();
        powerForward.setAbbreviation("PF");
        powerForward.setFullname("Power Forward");

        PositionsEntity center = new PositionsEntity();
        center.setAbbreviation("C");
        center.setFullname("Center");


        positions.add(pointGuard);
        positions.add(shootingGuard);
        positions.add(smallForward);
        positions.add(powerForward);
        positions.add(center);

        return positions;
    }
}
