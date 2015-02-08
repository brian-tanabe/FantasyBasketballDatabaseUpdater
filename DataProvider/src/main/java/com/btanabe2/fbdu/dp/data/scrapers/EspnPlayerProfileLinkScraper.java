package com.btanabe2.fbdu.dp.data.scrapers;

import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brian on 2/7/15.
 */
public class EspnPlayerProfileLinkScraper {

    public static List<String> getPlayerProfileLinks(Document document) {
        List<String> playerProfilePageLinks = new ArrayList<>(14);
        document.select("td.sortcell").select("a").stream().forEach(l -> playerProfilePageLinks.add(l.attr("href")));
        return playerProfilePageLinks;
    }
}
