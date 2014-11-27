package com.btanabe2.fbdu.du.application;

import com.btanabe2.fbdu.dm.models.PositionsEntity;
import com.btanabe2.fbdu.du.updaters.PlayerBiographyTableUpdater;
import com.btanabe2.fbdu.du.updaters.PositionFactory;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * Created by brian on 11/8/14.
 */
public class Application {

    public static void main(String[] args) {
        try {
            createPositionsTable();
            createPlayerBiographyTable();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static void createPositionsTable(){
        List<PositionsEntity> positions = PositionFactory.getAllPositions();

    }

    private static void createPlayerBiographyTable() throws IOException, ParseException {
        PlayerBiographyTableUpdater playerBiographyTableUpdater = new PlayerBiographyTableUpdater();
        playerBiographyTableUpdater.createPlayerBiographyTable();
    }
}
