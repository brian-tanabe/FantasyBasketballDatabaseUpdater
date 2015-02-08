package com.btanabe2.fbdu.dp.data.providers;

import com.btanabe2.fbdu.dp.data.scrapers.EspnPlayerProfileLinkScraper;
import com.btanabe2.fbdu.dp.data.scrapers.EspnTeamsRosterLinkScraper;
import com.btanabe2.fbdu.dp.web.WebRequest;
import org.apache.commons.io.FileUtils;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
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

    public static void main(String[] args) {
        try {
            new EspnFantasyIdToStandardIdProvider(new WebRequest()).getFantasyIdMappedToNormalIdMap();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<Integer, Integer> getFantasyIdMappedToNormalIdMap() throws IOException {
        List<String> allNbaTeamRosterPageUrls = getNbaTeamRosterPagesUrls();

//        allNbaTeamRosterPageUrls.forEach(u -> savePage(u));

        List<String> allNbaPlayerProfilePageUrls = getAllNbaPlayerProfilePageUrls(allNbaTeamRosterPageUrls);


        return new LinkedHashMap<>();
    }

    public List<String> getNbaTeamRosterPagesUrls() throws IOException {
        return EspnTeamsRosterLinkScraper.getTeamRosterPageLinks(webRequest.getPageAsDocument(ESPN_TEAMS_PAGE_URL));
    }

    public List<String> getAllNbaPlayerProfilePageUrls(List<String> teamPageUrls) throws IOException {
        List<String> allNbaPlayersProfilePageUrls = new ArrayList<>(500);
        for (String teamRosterPageUrl : teamPageUrls) {
            allNbaPlayersProfilePageUrls.addAll(extractPlayerProfilePageLinks(webRequest.getPageAsDocument(teamRosterPageUrl)));
        }

        return allNbaPlayersProfilePageUrls;
    }

    public List<String> extractPlayerProfilePageLinks(Document teamRosterPage) {
        return EspnPlayerProfileLinkScraper.getPlayerProfileLinks(teamRosterPage);
    }

    private void savePage(String url) {
        try {
            String pageString = webRequest.getPage(url);

            String teamName = String.format("espn-team-roster-page-%s.html", url.substring(url.lastIndexOf("/")).replace("/", ""));

            FileUtils.writeStringToFile(new File("./DataProvider/src/test/resources/webpages/espn-team-pages/" + teamName), pageString, Charset.forName("UTF8"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
