package com.btanabe2.fbdu.du.application;

import com.btanabe2.fbdu.dm.models.NbaTeamEntity;
import com.btanabe2.fbdu.dm.models.PlayerBiographyEntity;
import com.btanabe2.fbdu.dm.models.PositionsEntity;
import com.btanabe2.fbdu.dp.leagues.providers.NbaPositionProvider;
import com.btanabe2.fbdu.dp.stats.providers.NbaTeamProvider;
import com.btanabe2.fbdu.dp.stats.providers.PlayerBiographyProvider;
import com.btanabe2.fbdu.dp.web.WebRequest;
import com.btanabe2.fbdu.du.updaters.UpdateByDroppingExistingEntitiesActor;

import java.io.IOException;
import java.text.ParseException;
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
        } catch (ParseException e) {
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

    private static List<PlayerBiographyEntity> createPlayerBiographyTable(List<NbaTeamEntity> nbaTeams) throws IOException, ParseException {
        PlayerBiographyProvider provider = new PlayerBiographyProvider(new WebRequest());
        List<PlayerBiographyEntity> playerBiographies = provider.getAllPlayers(nbaTeams);
        UpdateByDroppingExistingEntitiesActor.doUpdate(PlayerBiographyEntity.class, playerBiographies);
        return playerBiographies;
    }
}
