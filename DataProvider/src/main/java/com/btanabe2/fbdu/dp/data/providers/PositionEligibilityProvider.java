package com.btanabe2.fbdu.dp.data.providers;

import com.btanabe2.fbdu.dm.models.PlayerBiographyEntity;
import com.btanabe2.fbdu.dm.models.PositionEligibilityEntity;
import com.btanabe2.fbdu.dm.models.PositionsEntity;
import com.btanabe2.fbdu.dp.data.models.EspnPositionEligibilityModel;
import com.btanabe2.fbdu.dp.data.scrapers.EspnLeagueIdAndTeamIdScraper;
import com.btanabe2.fbdu.dp.data.scrapers.EspnProjectionsPageScraper;
import com.btanabe2.fbdu.dp.web.SecureWebRequest;
import com.btanabe2.fbdu.dp.web.WebConstants;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Brian on 2/2/15.
 */
public class PositionEligibilityProvider {
    private SecureWebRequest webRequest;

    public PositionEligibilityProvider(SecureWebRequest webRequest) {
        this.webRequest = webRequest;
    }

    public List<PositionEligibilityEntity> getPlayerPositionEligibility(List<PlayerBiographyEntity> players, List<PositionsEntity> positions, Map<Integer, Integer> fantasyIdsMappedToEspnIds) throws IOException {
        List<PositionEligibilityEntity> playerPositionEligibilityEntities = new ArrayList<>(players.size());
        String url = WebConstants.getEspnPlayerRaterPageUrlForParameterizedLeagueIdAndTeamId(EspnLeagueIdAndTeamIdScraper.findFirstCurrentSeasonFantasyLeagueId(webRequest), 0, 0);
        Document page = webRequest.getPageAsDocument(url);
        do {
            List<EspnPositionEligibilityModel> positionEligibilityMappedToEspnFantasyIds = scrapePageForPlayerPositionEligibility(page, positions);
            playerPositionEligibilityEntities.addAll(mapTheFantasyEspnIdsToTheirNormalEspnIdsAndPopulatePositionEligibilityEntityObject(positionEligibilityMappedToEspnFantasyIds, fantasyIdsMappedToEspnIds));
            url = getNextPageUrl(page);
            page = webRequest.getPageAsDocument(url);
        } while (page != null);

        return playerPositionEligibilityEntities;
    }

    private String getNextPageUrl(Document page) {
        return page.select("div.paginationNav").select("a:Contains(Next)").attr("href");
    }

    private List<EspnPositionEligibilityModel> scrapePageForPlayerPositionEligibility(Document page, List<PositionsEntity> positions) {
        return EspnProjectionsPageScraper.getEspnPlayerPositionEligibilities(page, positions);
    }

    private List<PositionEligibilityEntity> mapTheFantasyEspnIdsToTheirNormalEspnIdsAndPopulatePositionEligibilityEntityObject(List<EspnPositionEligibilityModel> fantasyIdsAndPositionEligibility, Map<Integer, Integer> fantasyIdsMappedToEspnIds) {
        List<PositionEligibilityEntity> positionEligibilityEntities = new ArrayList<>(fantasyIdsAndPositionEligibility.size());
        for (EspnPositionEligibilityModel positionEligibility : fantasyIdsAndPositionEligibility) {
            if (fantasyIdsMappedToEspnIds.containsKey(positionEligibility.getEspnPlayerId())) {
                positionEligibilityEntities.add(new PositionEligibilityEntity(fantasyIdsMappedToEspnIds.get(positionEligibility.getEspnPlayerId()), positionEligibility.getPositionId()));
            }
        }

        return positionEligibilityEntities;
    }
}
