package com.btanabe2.fbdu.dp.data.providers;

import com.btanabe2.fbdu.dp.data.scrapers.EspnPlayerProfileLinkScraper;
import com.btanabe2.fbdu.dp.data.scrapers.EspnPlayerProfilePageIdScraper;
import com.btanabe2.fbdu.dp.data.scrapers.EspnTeamsRosterLinkScraper;
import com.btanabe2.fbdu.dp.web.SecureWebRequest;
import com.btanabe2.fbdu.dp.web.auth.EspnCredentialProvider;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.btanabe2.fbdu.dp.web.WebConstants.ESPN_TEAMS_PAGE_URL;

/**
 * Created by Brian on 2/7/15.
 */
public class EspnFantasyIdToStandardIdProvider {
    private SecureWebRequest webRequest;

    public EspnFantasyIdToStandardIdProvider(SecureWebRequest webRequest) {
        this.webRequest = webRequest;
    }

    public Map<Integer, Integer> getFantasyIdMappedToNormalIdMap(EspnCredentialProvider credentials) throws IOException {
        Map<Integer, Integer> playerFantasyIdsMappedToTheirEspnIds = new HashMap<>();
        for (String playerProfilePageUrl : getAllNbaPlayerProfilePageUrls(getNbaTeamRosterPagesUrls())) {
            playerFantasyIdsMappedToTheirEspnIds.putAll(extractPlayerFantasyIdMappedToHisEspnId(playerProfilePageUrl));
        }
        return playerFantasyIdsMappedToTheirEspnIds;
    }

    private int determineEspnFantasyLeagueId(EspnCredentialProvider credentials) throws IOException {
        return -1;
    }

    private List<String> getNbaTeamRosterPagesUrls() throws IOException {
        return EspnTeamsRosterLinkScraper.getTeamRosterPageLinks(webRequest.getPageAsDocument(ESPN_TEAMS_PAGE_URL));
    }

    private List<String> getAllNbaPlayerProfilePageUrls(List<String> teamPageUrls) throws IOException {
        List<String> allNbaPlayersProfilePageUrls = new ArrayList<>(500);
        for (String teamRosterPageUrl : teamPageUrls) {
            allNbaPlayersProfilePageUrls.addAll(extractPlayerProfilePageLinks(webRequest.getPageAsDocument(teamRosterPageUrl)));
        }

        return allNbaPlayersProfilePageUrls;
    }

    private List<String> extractPlayerProfilePageLinks(Document teamRosterPage) {
        return EspnPlayerProfileLinkScraper.getPlayerProfileLinks(teamRosterPage);
    }

    private Map<Integer, Integer> extractPlayerFantasyIdMappedToHisEspnId(String playerProfilePageUrl) throws IOException {
        String playerEspnId = playerProfilePageUrl.replaceAll("[^\\d+]", "");
        return EspnPlayerProfilePageIdScraper.getPlayerFantasyIdMappedToHisEspnId(webRequest.getPageAsDocument(playerProfilePageUrl), Integer.parseInt(playerEspnId));
    }
}
