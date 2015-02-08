package com.btanabe2.fbdu.dp.data.providers;

import com.btanabe2.fbdu.dp.data.scrapers.EspnTeamsRosterLinkScraper;
import com.btanabe2.fbdu.dp.web.WebRequest;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.btanabe2.fbdu.dp.web.WebConstants.ESPN_TEAMS_PAGE_URL;

/**
 * Created by Brian on 2/7/15.
 */
public class EspnFantasyIdToStandardIdProvider {
    private WebRequest webRequest;

    public EspnFantasyIdToStandardIdProvider(WebRequest webRequest) {
        this.webRequest = webRequest;
    }

    public Map<Integer, Integer> getFantasyIdMappedToNormalIdMap() throws IOException {
        List<String> nbaTeamRosterPageUrls = getNbaTeamRosterPagesUrls();

        return new LinkedHashMap<>();
    }

    public List<String> getNbaTeamRosterPagesUrls() throws IOException {
        return EspnTeamsRosterLinkScraper.getTeamRosterPageLinks(webRequest.getPageAsDocument(ESPN_TEAMS_PAGE_URL));
    }
}
