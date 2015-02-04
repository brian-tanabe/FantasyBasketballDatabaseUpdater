package com.btanabe2.fbdu.dp.data.providers;

import com.btanabe2.fbdu.dm.models.PlayerBiographyEntity;
import com.btanabe2.fbdu.dm.models.PositionEligibilityEntity;
import com.btanabe2.fbdu.dm.models.PositionsEntity;
import com.btanabe2.fbdu.dp.data.models.EspnPositionEligibilityModel;
import com.btanabe2.fbdu.dp.data.scrapers.EspnProjectionsPageScraper;
import com.btanabe2.fbdu.dp.web.SecureWebRequest;
import com.btanabe2.fbdu.dp.web.WebConstants;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brian on 2/2/15.
 */
public class PositionEligibilityProvider {
    private SecureWebRequest webRequest;
    private int espnFantasyLeagueId;
    private int espnFantasyTeamId;

    public PositionEligibilityProvider(SecureWebRequest webRequest, int espnFantasyLeagueId, int espnFantasyTeamId) {
        this.webRequest = webRequest;
        this.espnFantasyLeagueId = espnFantasyLeagueId;
        this.espnFantasyTeamId = espnFantasyTeamId;
    }

    public List<PositionEligibilityEntity> getPlayerPositionEligibility(List<PlayerBiographyEntity> players, List<PositionsEntity> positions) throws IOException {
        List<PositionEligibilityEntity> playerPositionEligibilityEntities = new ArrayList<>(players.size());

        Document page = webRequest.getPageAsDocument(WebConstants.getEspnPlayerRaterPageUrlForParameterizedLeagueIdAndTeamId(espnFantasyLeagueId, espnFantasyTeamId, 0));
        do {
            List<EspnPositionEligibilityModel> positionEligibilityMappedToEspnFantasyIds = scrapePageForPlayerPositionEligibility(page, positions);
            playerPositionEligibilityEntities.addAll(mapTheFantasyEspnIdsToTheirNormalEspnIdsAndPopulatePositionEligibilityEntityObject(positionEligibilityMappedToEspnFantasyIds));
            page = webRequest.getPageAsDocument(getNextPageUrl(page));
        } while (page != null); // FIXME THIS MAY NOT RETURN NULL WHEN A NON-EXISTENT URL IS REQUESTED

        return playerPositionEligibilityEntities;
    }

    private String getNextPageUrl(Document page) {
        return page.select("div.paginationNav").select("a:Contains(Next)").attr("href");
    }

    private List<EspnPositionEligibilityModel> scrapePageForPlayerPositionEligibility(Document page, List<PositionsEntity> positions) {
        return EspnProjectionsPageScraper.getEspnPlayerPositionEligibilities(page, positions);
    }

    private List<PositionEligibilityEntity> mapTheFantasyEspnIdsToTheirNormalEspnIdsAndPopulatePositionEligibilityEntityObject(List<EspnPositionEligibilityModel> fantasyIdsAndPositionEligibility) {
        List<PositionEligibilityEntity> positionEligibilityEntities = new ArrayList<>(fantasyIdsAndPositionEligibility.size());

        // TODO FIX THIS!  THE IDS HERE WILL NOT MATCH THOSE IN THE DATABASE.
        // TODO I THINK I'LL NEED TO FOLLOW THE PLAYERCARD LINK TO FIND THE PLAYER'S REAL ESPN ID
        fantasyIdsAndPositionEligibility.forEach(e -> positionEligibilityEntities.add(new PositionEligibilityEntity(e.getEspnPlayerId(), e.getPositionId())));

        return positionEligibilityEntities;
    }
}
