package com.btanabe2.fbdu.dp.stats.providers;

import com.btanabe2.fbdu.dm.models.NbaTeamEntity;
import com.btanabe2.fbdu.dp.stats.scrapers.NbaTeamJsonParser;

import java.io.IOException;
import java.util.List;

/**
 * Created by brian on 11/6/14.
 */
public class NbaTeamProvider {

    public static List<NbaTeamEntity> getAllNbaTeamEntities() throws IOException {
        return NbaTeamJsonParser.getAllNbaTeams();
    }
}
