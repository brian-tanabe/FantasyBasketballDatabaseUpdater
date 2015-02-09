package com.btanabe2.fbdu.dp.data.scrapers;

import com.btanabe2.fbdu.dp.web.SecureWebRequest;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.btanabe2.fbdu.dp.web.WebConstants.ESPN_FANTASY_BASKETBALL_HOMEPAGE;

/**
 * Created by brian on 11/14/14.
 */
public class EspnLeagueIdScraper {

    /**
     * Finds the ESPN league ID of the league whose name is passed into this
     * function.  If that league name isn't present then the ID found will be
     * returned.  If there are no leagues found then -1 will be returned.
     *
     * @param webRequest     A <class>SecureWebRequest</class> that has already
     *                       authenticated with ESPN
     * @param leagueNameHint League name to find its ID
     * @return The league ID of the (1) parameterized league name, (2) the
     * first league ID, or (3) -1 if no leagues were found
     * @throws IOException thrown if errors occurred while making web requests.
     */
    public int findCurrentSeasonFantasyLeagueId(SecureWebRequest webRequest, String leagueNameHint) throws IOException {
        Map<String, String> allFantasyLeagueUrlsMappedToTheirLeagueNames = getFantasyClubhouseLinksMappedToTheirLeagueNames(webRequest);
        if (allFantasyLeagueUrlsMappedToTheirLeagueNames.containsKey(leagueNameHint)) {
            return extractLeagueIdFromUrl(allFantasyLeagueUrlsMappedToTheirLeagueNames.get(leagueNameHint));
        } else if (allFantasyLeagueUrlsMappedToTheirLeagueNames.size() > 0) {
            return extractLeagueIdFromUrl(allFantasyLeagueUrlsMappedToTheirLeagueNames.get(allFantasyLeagueUrlsMappedToTheirLeagueNames.keySet().stream().limit(1).collect(Collectors.toList()).get(0)));
        } else {
            return -1;
        }
    }

    /**
     * @param webRequest A <class>SecureWebRequest</class> that has already authenticated with ESPN
     * @return A <class>List</class> of links to that user's ESPN fantasy clubhouses.
     * @throws IOException thrown if an exception occurs when making web requests
     */
    public List<String> findFantasyLeagueHomePageUrls(SecureWebRequest webRequest) throws IOException {
        return getFantasyClubhouseLinksMappedToTheirLeagueNames(webRequest).values().stream().collect(Collectors.toList());
    }

    private Map<String, String> getFantasyClubhouseLinksMappedToTheirLeagueNames(SecureWebRequest webRequest) throws IOException {
        Elements leagueHomePageLinks = webRequest.getPageAsDocument(ESPN_FANTASY_BASKETBALL_HOMEPAGE).select("a.leagueoffice-link[href$=&seasonId=2015]");

        Map<String, String> linksMappedToTheirLeagueName = new HashMap<>();
        leagueHomePageLinks.stream().forEach(l -> linksMappedToTheirLeagueName.put(l.text(), l.attr("href")));

        return linksMappedToTheirLeagueName;
    }

    private int extractLeagueIdFromUrl(String url) {
        return Integer.parseInt(url.substring(url.indexOf("leagueId="), url.indexOf("&")).replaceAll("[^\\d]", ""));
    }
}
