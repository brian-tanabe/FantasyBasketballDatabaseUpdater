package com.btanabe2.fbdu.du.application;

import com.btanabe2.fbdu.dm.models.PositionsEntity;
import com.btanabe2.fbdu.du.updaters.PositionFactory;

import java.util.List;

/**
 * Created by brian on 11/8/14.
 */
public class Application {

    public static void main(String[] args){
        List<PositionsEntity> positions = PositionFactory.getAllPositions();

    }
}
