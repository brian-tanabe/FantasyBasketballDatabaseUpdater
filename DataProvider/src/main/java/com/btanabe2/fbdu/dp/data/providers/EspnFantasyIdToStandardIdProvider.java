package com.btanabe2.fbdu.dp.data.providers;

import com.btanabe2.fbdu.dp.data.scrapers.EspnPlayerProfileLinkScraper;
import com.btanabe2.fbdu.dp.data.scrapers.EspnTeamsRosterLinkScraper;
import com.btanabe2.fbdu.dp.web.WebRequest;
import org.jsoup.nodes.Document;

import java.io.IOException;
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

//    private void savePage(String url) {
//        try {
//            String pageString = webRequest.getPage(url);
//
//            int teamNameStringIndex = url.lastIndexOf("/");
//            String teamName = url.substring(teamNameStringIndex);
//            teamNameStringIndex = url.replace(teamName, "").lastIndexOf("/");
//            teamName = url.substring(teamNameStringIndex) + teamName;
//            teamName = teamName.replace("/", "-");
//
//            FileUtils.writeStringToFile(new File("./DataProvider/src/test/resources/webpages/espn-team-pages/" + teamName), pageString, Charset.forName("UTF8"));
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public static void main(String[] args) {
//        try {
//            new EspnFantasyIdToStandardIdProvider(new WebRequest()).getFantasyIdMappedToNormalIdMap();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
