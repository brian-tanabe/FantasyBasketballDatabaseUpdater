package com.btanabe2.fbdu.dp.stats.providers;

import com.btanabe2.fbdu.dm.models.NbaTeamEntity;
import com.btanabe2.fbdu.dm.models.PlayerBiographyEntity;
import com.btanabe2.fbdu.dp.stats.scrapers.PlayerProfileSportsVuScraper;
import com.btanabe2.fbdu.dp.web.WebRequest;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * Created by BTanabe on 11/18/2014.
 */
public class PlayerBiographyProvider {
    private WebRequest webRequest;

    public PlayerBiographyProvider(WebRequest webRequest){
        this.webRequest = webRequest;
    }

    public List<PlayerBiographyEntity> getAllPlayers() throws IOException, ParseException {
        List<NbaTeamEntity> nbaTeams = NbaTeamProvider.getAllNbaTeamEntities(webRequest);

        PlayerProfileSportsVuScraper playerProfileSportsVuScraper = new PlayerProfileSportsVuScraper(webRequest);
        List<PlayerBiographyEntity> allActivePlayerBiographiesWithoutEspnIds = playerProfileSportsVuScraper.scrapeForPlayerBiographies(nbaTeams);



        return allActivePlayerBiographiesWithoutEspnIds;
    }

}
