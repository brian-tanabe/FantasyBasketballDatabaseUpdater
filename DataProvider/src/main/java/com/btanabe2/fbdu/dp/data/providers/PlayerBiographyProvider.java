package com.btanabe2.fbdu.dp.data.providers;

import com.btanabe2.fbdu.dm.models.NbaTeamEntity;
import com.btanabe2.fbdu.dm.models.PlayerBiographyEntity;
import com.btanabe2.fbdu.dp.data.scrapers.EspnAndNumberFireIdPageScraper;
import com.btanabe2.fbdu.dp.data.scrapers.PlayerProfileSportsVuScraper;
import com.btanabe2.fbdu.dp.web.WebRequest;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import static com.btanabe2.fbdu.dp.web.WebConstants.*;

/**
 * Created by BTanabe on 11/18/2014.
 */
public class PlayerBiographyProvider {
    private WebRequest webRequest;

    public PlayerBiographyProvider(WebRequest webRequest) {
        this.webRequest = webRequest;
    }

    public List<PlayerBiographyEntity> getAllPlayers(List<NbaTeamEntity> nbaTeams) throws IOException, ParseException, ExecutionException, InterruptedException {
        List<PlayerBiographyEntity> allActivePlayerBiographiesWithoutEspnIds = getAllActivePlayerBiographiesWithoutNumberFireOrEspnIds(nbaTeams);
        List<PlayerBiographyEntity> playerIds = getAllEspnAndNumberFireIds();

        for (PlayerBiographyEntity player : allActivePlayerBiographiesWithoutEspnIds) {
            PlayerBiographyEntity matchedPlayerEntityContainingEspnAndNumberFireIds = findNumberFireRemainingSeasonProjectionEntityForPlayer(player, playerIds);
            player.setEspnid(matchedPlayerEntityContainingEspnAndNumberFireIds.getEspnid());
            player.setNumberfireid(matchedPlayerEntityContainingEspnAndNumberFireIds.getNumberfireid());
        }

        return allActivePlayerBiographiesWithoutEspnIds;
    }

    private List<PlayerBiographyEntity> getAllActivePlayerBiographiesWithoutNumberFireOrEspnIds(List<NbaTeamEntity> nbaTeams) throws IOException, ParseException, ExecutionException, InterruptedException {
        return new PlayerProfileSportsVuScraper(webRequest).scrapeForPlayerBiographies(nbaTeams);
    }

    private List<PlayerBiographyEntity> getAllEspnAndNumberFireIds() throws IOException, ParseException {
        List<PlayerBiographyEntity> playerIdEntities = new ArrayList<>();

        playerIdEntities.addAll(EspnAndNumberFireIdPageScraper.scrapePlayerBiographiesFromPage(webRequest.getPageAsDocument(NUMBER_FIRE_REMAINING_PROJECTIONS_GUARDS_URL)));
        playerIdEntities.addAll(EspnAndNumberFireIdPageScraper.scrapePlayerBiographiesFromPage(webRequest.getPageAsDocument(NUMBER_FIRE_REMAINING_PROJECTIONS_FORWARDS_URL)));
        playerIdEntities.addAll(EspnAndNumberFireIdPageScraper.scrapePlayerBiographiesFromPage(webRequest.getPageAsDocument(NUMBER_FIRE_REMAINING_PROJECTIONS_CENTERS_URL)));

        return playerIdEntities;
    }

    private PlayerBiographyEntity findNumberFireRemainingSeasonProjectionEntityForPlayer(PlayerBiographyEntity playerToSearchFor, List<PlayerBiographyEntity> listOfNumberFireRemainingSeasonProjections) {
        try {
            return listOfNumberFireRemainingSeasonProjections.stream().filter(p -> p.getName().equals(playerToSearchFor.getName())).limit(1).collect(Collectors.toList()).get(0);
        } catch (Exception ex) {
            // TODO LOG THIS EVENT:
            PlayerBiographyEntity emptyPlayer = new PlayerBiographyEntity();
            emptyPlayer.setNumberfireid(0);
            emptyPlayer.setEspnid(0);
            return emptyPlayer;
        }
    }
}
