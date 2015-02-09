package com.btanabe2.fbdu.du.application;

import com.btanabe2.fbdu.dm.models.NbaTeamEntity;
import com.btanabe2.fbdu.dm.models.PlayerBiographyEntity;
import com.btanabe2.fbdu.dm.models.PositionEligibilityEntity;
import com.btanabe2.fbdu.dm.models.PositionsEntity;
import com.btanabe2.fbdu.dp.data.providers.*;
import com.btanabe2.fbdu.dp.web.WebRequest;
import com.btanabe2.fbdu.du.updaters.UpdateByDroppingExistingEntitiesActor;
import com.btanabe2.fbdu.du.web.EspnWebRequestProvider;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * Created by brian on 11/8/14.
 */
public class Application {

    public static void main(String[] args) {
        try {
            List<NbaTeamEntity> nbaTeamEntityList = createNbaTeamsTable();
            List<PositionsEntity> positionsEntityList = createPositionsTable();
            List<PlayerBiographyEntity> playerBiographyEntityList = createPlayerBiographyTable(nbaTeamEntityList);
            List<PositionEligibilityEntity> positionEligibilityEntityList = createPositionEligibilityTable(playerBiographyEntityList, positionsEntityList);
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

    private static List<PositionEligibilityEntity> createPositionEligibilityTable(List<PlayerBiographyEntity> playerBiographies, List<PositionsEntity> positionsEntities) throws IOException {
        PositionEligibilityProvider provider = new PositionEligibilityProvider(EspnWebRequestProvider.getInstance());
        List<PositionEligibilityEntity> positionEligibilityEntityList = provider.getPlayerPositionEligibility(playerBiographies, positionsEntities, new EspnFantasyIdToStandardIdProvider(EspnWebRequestProvider.getInstance()).getFantasyIdMappedToNormalIdMap());
        UpdateByDroppingExistingEntitiesActor.doUpdate(PositionEligibilityEntity.class, positionEligibilityEntityList);
        return positionEligibilityEntityList;
    }
}
