package com.btanabe2.fbdu.dp.data.providers;

import com.btanabe2.fbdu.dm.models.PlayerBiographyEntity;
import com.btanabe2.fbdu.dm.models.PositionEligibilityEntity;
import com.btanabe2.fbdu.dm.models.PositionsEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brian on 2/2/15.
 */
public class PositionEligibilityProvider {

    public static List<PositionEligibilityEntity> getPlayerPositionEligibility(List<PlayerBiographyEntity> players, List<PositionsEntity> positions) {
        List<PositionEligibilityEntity> playerPositionEligibilityEntities = new ArrayList<>(players.size());

        // TODO WILL NEED TO SCRAPE ESPN FOR EACH PLAYER'S POSITION ELIGIBILITY
        // TODO THIS MAY BE EASIER NOW THAT WE HAVE THEIR ESPN PLAYER IDS...

        return playerPositionEligibilityEntities;
    }


}
