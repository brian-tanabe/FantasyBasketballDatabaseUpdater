package com.btanabe2.fbdu.dp.data.scrapers.sportsvu;

import com.btanabe2.fbdu.dm.models.NbaTeamEntity;
import com.btanabe2.fbdu.dm.models.PlayerBiographyEntity;
import com.btanabe2.fbdu.dp.web.WebRequest;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static com.btanabe2.fbdu.dp.data.models.PlayerProfile.INACTIVE_PLAYER;
import static com.btanabe2.fbdu.dp.web.WebConstants.SPORTS_VU_ALL_PLAYERS_URL;

/**
 * Created by BTanabe on 11/19/2014.
 */
public class PlayerProfileSportsVuScraper {
    // Check out this project:
    // https://www.npmjs.org/package/nba

    // Aaron Afflalo game logs: http://stats.nba.com/stats/playerprofile?LeagueID=00&Season=2014-15&IsOnlyCurrentSeason=1&PlayerId=201167&GraphStat=PTS&GraphEndSeason=2014-15&GraphStartSeason=1990-91&SeasonType=Regular%20Season
    // Aaron Afflalo player info: http://stats.nba.com/stats/commonplayerinfo?PlayerID=201167
    // All players: http://stats.nba.com/stats/commonallplayers?LeagueID=00&Season=2014-15&IsOnlyCurrentSeason=1

    private WebRequest webRequest;

    public PlayerProfileSportsVuScraper(WebRequest webRequest) {
        this.webRequest = webRequest;
    }

    public List<PlayerBiographyEntity> scrapeForPlayerBiographies(List<NbaTeamEntity> nbaTeams) throws IOException, ParseException, ExecutionException, InterruptedException {
        List<Integer> allActiveNbaPlayersSportsVuPlayerIds = getListOfAllActivePlayerIds();
        return scrapeAllPlayerInfoFromEachPlayersSportsVuPageMultithreaded(allActiveNbaPlayersSportsVuPlayerIds, nbaTeams);
    }

    private List<Integer> getListOfAllActivePlayerIds() throws IOException {
        // TODO THIS CAN BE ABSTRACTED OUT:
        JsonObject jsonElement = new JsonParser().parse(webRequest.getPage(SPORTS_VU_ALL_PLAYERS_URL)).getAsJsonObject();
        JsonArray jsonArray = jsonElement.getAsJsonArray("resultSets");
        JsonArray elements = (JsonArray) jsonArray.get(0).getAsJsonObject().get("rowSet");

        List<Integer> playerIds = new ArrayList<>(elements.size());
        elements.forEach(p -> playerIds.add(p.getAsJsonArray().get(0).getAsInt()));

        return playerIds;
    }

    private List<PlayerBiographyEntity> scrapeAllPlayerInfoFromEachPlayersSportsVuPageMultithreaded(List<Integer> allActivePlayerIds, List<NbaTeamEntity> nbaTeam) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(allActivePlayerIds.size());
        List<Future<PlayerBiographyEntity>> futureList = new ArrayList<>(allActivePlayerIds.size());
        allActivePlayerIds.forEach(playerId -> futureList.add(executorService.submit(new CallablePlayerProfileSportsVuScraper(webRequest, playerId, nbaTeam))));

        List<PlayerBiographyEntity> playerBiographyEntityList = new ArrayList<>(allActivePlayerIds.size());
        for (Future<PlayerBiographyEntity> future : futureList) {
            PlayerBiographyEntity possiblePlayer = future.get();
            if (possiblePlayer != INACTIVE_PLAYER) {
                playerBiographyEntityList.add(possiblePlayer);
            }
        }

        executorService.shutdown();

        return playerBiographyEntityList;
    }
}
