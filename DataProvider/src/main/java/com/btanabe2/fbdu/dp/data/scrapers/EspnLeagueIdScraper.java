package com.btanabe2.fbdu.dp.data.scrapers;

import com.btanabe2.fbdu.dp.web.SecureWebRequest;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.btanabe2.fbdu.dp.web.WebConstants.ESPN_FANTASY_BASKETBALL_HOMEPAGE;

/**
 * Created by brian on 11/14/14.
 */
public class EspnLeagueIdScraper {

    public int findCurrentSeasonFantasyLeagueId(SecureWebRequest webRequest, String leagueNameHint) throws IOException {
        List<String> leagueUrls = findFantasyLeagueHomePageUrls(webRequest);

        return -1;
    }

    /**
     * @param webRequest A <class>SecureWebRequest</class> that has already authenticated with ESPN
     * @return A <class>List</class> of links to that user's ESPN fantasy clubhouses.
     * @throws IOException thrown if an exception occurs when making web requests
     */
    public List<String> findFantasyLeagueHomePageUrls(SecureWebRequest webRequest) throws IOException {
        Elements leagueHomePageLinks = webRequest.getPageAsDocument(ESPN_FANTASY_BASKETBALL_HOMEPAGE).select("a.leagueoffice-link[href$=&seasonId=2015]");

        List<String> links = new ArrayList<>();
        leagueHomePageLinks.stream().forEach(l -> links.add(l.attr("href")));

        return links;
    }
}
