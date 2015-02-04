package com.btanabe2.fbdu.dp.data.scrapers;

import com.btanabe2.fbdu.dp.web.SecureWebRequest;
import com.btanabe2.fbdu.dp.web.auth.EspnCredentialProvider;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by brian on 11/14/14.
 */
public class EspnLeagueIdScraper {

    public List<String> findFantasyLeagueHomePageUrls(SecureWebRequest webRequest, EspnCredentialProvider credentialProvider, String url) throws IOException {
        Document document = getFantasyHomePageAsDocument(webRequest, credentialProvider, url);
        Elements leagueHomePageLinks = document.select("a.leagueoffice-link[href$=&seasonId=2015]");

        List<String> links = new ArrayList<>();
        leagueHomePageLinks.stream().forEach(l -> links.add(l.attr("href")));

        return links;
    }

    private Document getFantasyHomePageAsDocument(SecureWebRequest webRequest, EspnCredentialProvider credentialProvider, String url) throws IOException {
        webRequest.login(credentialProvider);
        return webRequest.getPageAsDocument(url);
    }
}
