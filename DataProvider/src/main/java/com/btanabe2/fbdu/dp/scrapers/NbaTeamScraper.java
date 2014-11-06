package com.btanabe2.fbdu.dp.scrapers;

import com.btanabe2.fbdu.dm.models.NbaTeamEntity;
import com.btanabe2.fbdu.dp.web.WebRequest;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.btanabe2.fbdu.dp.web.WebConstants.ESPN_TEAMS_PAGE_URL;

/**
 * Created by brian on 11/6/14.
 */
public class NbaTeamScraper {

    public static List<NbaTeamEntity> getAllNbaTeams(WebRequest webRequest) throws IOException {
        List<NbaTeamEntity> nbaTeams = new ArrayList<>(30);

        for(String teamPageUrl : getTeamPageUrls(webRequest.getPageAsDocument(ESPN_TEAMS_PAGE_URL))){
            nbaTeams.add(scrapeNbaTeamPage(webRequest, teamPageUrl.replace("http://espn.go.com/nba/team/_/", "http://espn.go.com/nba/team/stadium/_/")));
        }

        return nbaTeams;
    }

    private static List<String> getTeamPageUrls(Document nbaTeamsDocument){
        Set<String> nbaTeamPageUrlsSet = new LinkedHashSet<>(30);

        Elements nbaTeamPageLinks = nbaTeamsDocument.select("a[href^=http://espn.go.com/nba/team/_/name/]");
        nbaTeamPageUrlsSet.addAll(nbaTeamPageLinks.stream().map(nbaTeamElement -> nbaTeamElement.attr("href")).collect(Collectors.toList()));

        return new ArrayList<>(nbaTeamPageUrlsSet);
    }

    private static NbaTeamEntity scrapeNbaTeamPage(WebRequest webRequest, String nbaTeamPageUrl) throws IOException {
        Document teamStadiumPage = webRequest.getPageAsDocument(nbaTeamPageUrl);

        String teamName = teamStadiumPage.select("a.sub-brand-title").text();


        NbaTeamEntity team = new NbaTeamEntity();
        team.setName(teamName);

        return null;
    }
}
