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
public class EspnLeagueIdAndTeamIdScraper {

    /**
     * Finds the first ESPN league ID for a given user
     *
     * @param webRequest A <class>SecureWebRequest</class> that has already
     *                   authenticated with ESPN
     * @return the league ID of the first league for a user
     * @throws IOException thrown if errors occurred while making web requests
     */
    public static int findFirstCurrentSeasonFantasyLeagueId(SecureWebRequest webRequest) throws IOException {
        Map<String, String> allFantasyLeagueUrlsMappedToTheirLeagueNames = getFantasyClubhouseLinksMappedToTheirLeagueNames(webRequest);
        return extractLeagueIdFromUrl(allFantasyLeagueUrlsMappedToTheirLeagueNames.get(allFantasyLeagueUrlsMappedToTheirLeagueNames.keySet().stream().limit(1).collect(Collectors.toList()).get(0)));
    }

    /**
     * Finds the teamId for the logged in user for the parameterized league
     *
     * @param webRequest A <class>SecureWebRequest</class> that has
     *                   already authenticated with ESPN
     * @param leagueId   the league ID of the league that the logged in user
     *                   is a member of
     * @return the user's team ID
     * @throws IOException thrown if errors occurred while making web requests
     */
    public static int getUsersTeamId(SecureWebRequest webRequest, int leagueId) throws IOException {
        Map<String, String> allFantasyLeagueUrlsMappedToTheirLeagueNames = getFantasyClubhouseLinksMappedToTheirLeagueNames(webRequest);
        return extractTeamIdFromUrl(allFantasyLeagueUrlsMappedToTheirLeagueNames.values().stream().filter(u -> u.contains(Integer.toString(leagueId))).limit(1).collect(Collectors.toList()).get(0));
    }

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
    public static int findCurrentSeasonFantasyLeagueId(SecureWebRequest webRequest, String leagueNameHint) throws IOException {
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
    public static List<String> findFantasyLeagueHomePageUrls(SecureWebRequest webRequest) throws IOException {
        return getFantasyClubhouseLinksMappedToTheirLeagueNames(webRequest).values().stream().collect(Collectors.toList());
    }

    private static Map<String, String> getFantasyClubhouseLinksMappedToTheirLeagueNames(SecureWebRequest webRequest) throws IOException {
        Elements leagueHomePageLinks = webRequest.getPageAsDocument(ESPN_FANTASY_BASKETBALL_HOMEPAGE).select("a.leagueoffice-link[href$=&seasonId=2015]");

        Map<String, String> linksMappedToTheirLeagueName = new HashMap<>();
        leagueHomePageLinks.stream().forEach(l -> linksMappedToTheirLeagueName.put(l.text(), l.attr("href")));

        return linksMappedToTheirLeagueName;
    }

    private static int extractLeagueIdFromUrl(String url) {
        return Integer.parseInt(url.substring(url.indexOf("leagueId="), url.indexOf("&")).replaceAll("[^\\d]", ""));
    }

    private static int extractTeamIdFromUrl(String url) {
        String teamIdString = url.substring(url.indexOf("teamId="), url.indexOf("&", url.indexOf("teamId="))).replaceAll("[^\\d]", "");
        return Integer.parseInt(teamIdString);
    }
}
