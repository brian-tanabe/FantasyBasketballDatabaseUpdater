package com.btanabe2.fbdu.du.updaters;

import com.btanabe2.fbdu.dm.models.NbaTeamEntity;
import com.btanabe2.fbdu.dm.models.PlayerBiographyEntity;
import com.btanabe2.fbdu.dp.stats.providers.PlayerBiographyProvider;
import com.btanabe2.fbdu.dp.web.WebRequest;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * Created by brian on 11/26/14.
 */
public class PlayerBiographyTableUpdater {

    public void createPlayerBiographyTable(List<NbaTeamEntity> nbaTeams) throws IOException, ParseException {
        PlayerBiographyProvider provider = new PlayerBiographyProvider(new WebRequest());
        List<PlayerBiographyEntity> playersToAddToDatabase = provider.getAllPlayers(nbaTeams);



        System.out.println("");
    }
}
