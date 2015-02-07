package com.btanabe2.fbdu.dp.sandbox;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * Created by Brian on 2/7/15.
 */
public class Sandbox {

    public static void main(String[] args) {
        Document teamRosterPageLinks = Jsoup.parse("http://espn.go.com/nba/teams");
        Elements teamPageLinks = teamRosterPageLinks.select("a");
    }
}
