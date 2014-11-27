package com.btanabe2.fbdu.dp.stats.providers;

import com.btanabe2.fbdu.dm.models.NbaTeamEntity;
import com.btanabe2.fbdu.dm.models.PlayerBiographyEntity;
import com.btanabe2.fbdu.dp.stats.scrapers.EspnAndNumberFireIdPageScraper;
import com.btanabe2.fbdu.dp.stats.scrapers.PlayerProfileSportsVuScraper;
import com.btanabe2.fbdu.dp.web.WebRequest;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

import static com.btanabe2.fbdu.dp.web.WebConstants.*;

/**
 * Created by BTanabe on 11/18/2014.
 */
public class PlayerBiographyProvider {
    private WebRequest webRequest;

    public PlayerBiographyProvider(WebRequest webRequest){
        this.webRequest = webRequest;
    }

    public List<PlayerBiographyEntity> getAllPlayers(List<NbaTeamEntity> nbaTeams) throws IOException, ParseException {
        List<PlayerBiographyEntity> allActivePlayerBiographiesWithoutEspnIds = getAllActivePlayerBiographiesWithoutNumberFireOrEspnIds(nbaTeams);
        List<PlayerBiographyEntity> playerIds = getAllEspnAndNumberFireIds(nbaTeams);

        for(PlayerBiographyEntity player : allActivePlayerBiographiesWithoutEspnIds){
            PlayerBiographyEntity matchedPlayerEntityContainingEspnAndNumberFireIds = findNumberFireRemainingSeasonProjectionEntityForPlayer(player, playerIds);
            player.setEspnid(matchedPlayerEntityContainingEspnAndNumberFireIds.getEspnid());
            player.setNumberfireid(matchedPlayerEntityContainingEspnAndNumberFireIds.getNumberfireid());
        }

        return allActivePlayerBiographiesWithoutEspnIds;
    }

    private List<PlayerBiographyEntity> getAllActivePlayerBiographiesWithoutNumberFireOrEspnIds(List<NbaTeamEntity> nbaTeams) throws IOException, ParseException {
        PlayerProfileSportsVuScraper playerProfileSportsVuScraper = new PlayerProfileSportsVuScraper(webRequest);
        return playerProfileSportsVuScraper.scrapeForPlayerBiographies(nbaTeams);
    }

    private List<PlayerBiographyEntity> getAllEspnAndNumberFireIds(List<NbaTeamEntity> nbaTeams) throws IOException, ParseException {
        EspnAndNumberFireIdPageScraper idScraper = new EspnAndNumberFireIdPageScraper();

        List<PlayerBiographyEntity> playerIdEntities = idScraper.scrapePlayerBiographiesFromPage(webRequest.getPageAsDocument(NUMBER_FIRE_REMAINING_PROJECTIONS_GUARDS_URL), nbaTeams);
        playerIdEntities.addAll(idScraper.scrapePlayerBiographiesFromPage(webRequest.getPageAsDocument(NUMBER_FIRE_REMAINING_PROJECTIONS_FORWARDS_URL), nbaTeams));
        playerIdEntities.addAll(idScraper.scrapePlayerBiographiesFromPage(webRequest.getPageAsDocument(NUMBER_FIRE_REMAINING_PROJECTIONS_CENTERS_URL), nbaTeams));

        return playerIdEntities;
    }

    private PlayerBiographyEntity findNumberFireRemainingSeasonProjectionEntityForPlayer(PlayerBiographyEntity playerToSearchFor, List<PlayerBiographyEntity> listOfNumberFireRemainingSeasonProjections){
        return listOfNumberFireRemainingSeasonProjections.stream().filter(p -> p.getName().equals(playerToSearchFor.getName())).limit(1).collect(Collectors.toList()).get(0);
    }
}
