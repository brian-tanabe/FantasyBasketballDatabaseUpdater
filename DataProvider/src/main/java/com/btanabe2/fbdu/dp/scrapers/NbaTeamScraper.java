package com.btanabe2.fbdu.dp.scrapers;

import com.btanabe2.fbdu.dm.models.NbaTeamEntity;
import com.btanabe2.fbdu.dp.web.WebRequest;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.btanabe2.fbdu.dp.web.WebConstants.BASKETBALL_REFERENCE_STANDINGS_PAGE_URL;

/**
 * Created by brian on 11/6/14.
 */
public class NbaTeamScraper {

    public static List<NbaTeamEntity> getAllNbaTeams(WebRequest webRequest) throws IOException {
        List<NbaTeamEntity> nbaTeams = new ArrayList<>(30);

        for(String teamPageUrl : getTeamPageUrls(webRequest.getPageAsDocument(BASKETBALL_REFERENCE_STANDINGS_PAGE_URL))){
            nbaTeams.add(scrapeNbaTeamPage(webRequest, String.format("http://www.basketball-reference.com%s", teamPageUrl.replace("/2015.html", "/"))));
        }

        return nbaTeams;
    }

    private static List<String> getTeamPageUrls(Document nbaTeamsDocument){
        Set<String> nbaTeamPageUrlsSet = new LinkedHashSet<>(30);

        Elements nbaTeamPageLinks = nbaTeamsDocument.select("tr.full_table").select("a[href^=/teams/]");
        nbaTeamPageUrlsSet.addAll(nbaTeamPageLinks.stream().map(nbaTeamElement -> nbaTeamElement.attr("href")).collect(Collectors.toList()));

        return new ArrayList<>(nbaTeamPageUrlsSet);
    }

    private static NbaTeamEntity scrapeNbaTeamPage(WebRequest webRequest, String nbaTeamPageUrl) throws IOException {
        System.out.println(nbaTeamPageUrl);

        Document teamStadiumPage = webRequest.getPageAsDocument(nbaTeamPageUrl);

        Element dashboardElements = teamStadiumPage.select("div.stw").select("span:contains(Location:)").parents().select("p").get(0);
        String dashboardString = dashboardElements.text();

        String locationString = dashboardString.substring(dashboardString.indexOf("Location: ") + "Location: ".length(), dashboardString.indexOf("Team Name:")).trim();
        String teamName = dashboardString.substring(dashboardString.indexOf("Team Name: ") + "Team Name: ".length()).trim();
        String abbreviation = nbaTeamPageUrl.replaceAll("http://www.basketball-reference.com/teams/", "").replaceAll("/", "");

        NbaTeamEntity team = new NbaTeamEntity();
        team.setName(teamName);
//        team.setLocation(locationString);
        team.setAbbreviation(abbreviation);

        return team;
    }
}
