package com.btanabe2.fbdu.du.application;

import com.btanabe2.fbdu.dm.models.NbaTeamEntity;
import com.btanabe2.fbdu.dm.models.PositionsEntity;
import com.btanabe2.fbdu.dp.leagues.providers.NbaPositionProvider;
import com.btanabe2.fbdu.dp.stats.providers.NbaTeamProvider;
import com.btanabe2.fbdu.dp.web.WebRequest;
import com.btanabe2.fbdu.du.updaters.UpdateByDroppingExistingEntitiesActor;

import java.io.IOException;
import java.util.List;

/**
 * Created by brian on 11/8/14.
 */
public class Application {

    public static void main(String[] args) {
        try {
            List<NbaTeamEntity> nbaTeams = createNbaTeamsTable();
            createPositionsTable();
            createPlayerBiographyTable(nbaTeams);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            MySessionManager.getInstance().getSessionFactory().close();
        }
    }

    private static List<NbaTeamEntity> createNbaTeamsTable() throws IOException {
        List<NbaTeamEntity> nbaTeams = NbaTeamProvider.getAllNbaTeamEntities(new WebRequest());
        UpdateByDroppingExistingEntitiesActor.doUpdate(NbaTeamEntity.class, nbaTeams);
        return nbaTeams;
    }

    private static List<PositionsEntity> createPositionsTable() {
        List<PositionsEntity> positions = NbaPositionProvider.getAllPositions();
        UpdateByDroppingExistingEntitiesActor.doUpdate(PositionsEntity.class, positions);
        return positions;
    }

    private static void createPlayerBiographyTable(List<NbaTeamEntity> nbaTeams) {
    }
}
