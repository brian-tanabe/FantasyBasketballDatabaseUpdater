package com.btanabe2.fbdu.dp.stats.providers;

import com.btanabe2.fbdu.dm.models.NbaTeamEntity;
import com.btanabe2.fbdu.dm.models.NfRemainingSeasonProjectionsEntity;
import com.btanabe2.fbdu.dm.models.PlayerBiographyEntity;
import com.btanabe2.fbdu.dp.stats.scrapers.NumberFireRemainingSeasonProjectionsPageScraper;
import com.btanabe2.fbdu.dp.stats.scrapers.PlayerProfileSportsVuScraper;
import com.btanabe2.fbdu.dp.web.WebRequest;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import static com.btanabe2.fbdu.dp.web.WebConstants.*;

/**
 * Created by BTanabe on 11/18/2014.
 */
public class PlayerBiographyProvider {
    private WebRequest webRequest;

    public PlayerBiographyProvider(WebRequest webRequest){
        this.webRequest = webRequest;
    }

    public List<PlayerBiographyEntity> getAllPlayers() throws IOException, ParseException {
        List<PlayerBiographyEntity> allActivePlayerBiographiesWithoutEspnIds = getAllActivePlayerBiographiesWithoutNumberFireOrEspnIds();
        List<NfRemainingSeasonProjectionsEntity> playerIds = getAllEspnAndNumberFireIds();


        return allActivePlayerBiographiesWithoutEspnIds;
    }

    private List<PlayerBiographyEntity> getAllActivePlayerBiographiesWithoutNumberFireOrEspnIds() throws IOException, ParseException {
        List<NbaTeamEntity> nbaTeams = NbaTeamProvider.getAllNbaTeamEntities(webRequest);

        PlayerProfileSportsVuScraper playerProfileSportsVuScraper = new PlayerProfileSportsVuScraper(webRequest);
        return playerProfileSportsVuScraper.scrapeForPlayerBiographies(nbaTeams);
    }

    private List<NfRemainingSeasonProjectionsEntity> getAllEspnAndNumberFireIds() throws IOException {
        NumberFireRemainingSeasonProjectionsPageScraper espnAndNumberFireIdScraper = new NumberFireRemainingSeasonProjectionsPageScraper();
        List<NfRemainingSeasonProjectionsEntity> playerIds = espnAndNumberFireIdScraper.scrapeRemainingSeasonProjectionsFromPage(webRequest.getPageAsDocument(NUMBER_FIRE_REMAINING_PROJECTIONS_GUARDS_URL));
        playerIds.addAll(espnAndNumberFireIdScraper.scrapeRemainingSeasonProjectionsFromPage(webRequest.getPageAsDocument(NUMBER_FIRE_REMAINING_PROJECTIONS_FORWARDS_URL)));
        playerIds.addAll(espnAndNumberFireIdScraper.scrapeRemainingSeasonProjectionsFromPage(webRequest.getPageAsDocument(NUMBER_FIRE_REMAINING_PROJECTIONS_CENTERS_URL)));

        return playerIds;
    }

    private NfRemainingSeasonProjectionsEntity findNumberFireRemainingSeasonProjectionEntityForPlayer(PlayerBiographyEntity playerToSearchFor, List<NfRemainingSeasonProjectionsEntity> listOfNumberFireRemainingSeasonProjections){

        return new NfRemainingSeasonProjectionsEntity();
    }
}
