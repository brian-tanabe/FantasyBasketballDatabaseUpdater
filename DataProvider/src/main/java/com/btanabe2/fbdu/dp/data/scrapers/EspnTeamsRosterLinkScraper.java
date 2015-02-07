package com.btanabe2.fbdu.dp.data.scrapers;

import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brian on 2/7/15.
 */
public class EspnTeamsRosterLinkScraper {

    public static List<String> getTeamRosterPageLinks(Document document) {
        List<String> teamLinks = new ArrayList<>(30);
        document.select("a:contains(Roster)").stream().forEach(h -> teamLinks.add(String.format("http://www.go.espn.com%s", h.attr("href"))));
        return teamLinks;
    }
}
