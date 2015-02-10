package com.btanabe2.fbdu.dp.data.scrapers.workers;

import com.btanabe2.fbdu.dp.web.WebRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by Brian on 2/9/15.
 */
public class CallableEspnPlayerProfileLinkScraper implements Callable<List<String>> {
    private WebRequest webRequest;
    private String url;

    public CallableEspnPlayerProfileLinkScraper(WebRequest webRequest, String url) {
        this.webRequest = webRequest;
        this.url = url;
    }

    @Override
    public List<String> call() throws Exception {
        List<String> playerProfilePageLinks = new ArrayList<>(14);
        webRequest.getPageAsDocument(url).select("td.sortcell").select("a").stream().forEach(l -> playerProfilePageLinks.add(l.attr("href")));
        return playerProfilePageLinks;
    }
}
