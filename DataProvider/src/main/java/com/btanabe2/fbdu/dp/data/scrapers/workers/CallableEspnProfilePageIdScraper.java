package com.btanabe2.fbdu.dp.data.scrapers.workers;

import com.btanabe2.fbdu.dp.data.scrapers.EspnPlayerProfilePageIdScraper;
import com.btanabe2.fbdu.dp.web.WebRequest;

import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Created by Brian on 2/9/15.
 */
public class CallableEspnProfilePageIdScraper implements Callable<Map<Integer, Integer>> {
    private WebRequest webRequest;
    private String playerProfilePageUrl;

    public CallableEspnProfilePageIdScraper(WebRequest webRequest, String playerProfilePageUrl) {
        this.webRequest = webRequest;
        this.playerProfilePageUrl = playerProfilePageUrl;
    }

    @Override
    public Map<Integer, Integer> call() throws Exception {
        String playerEspnId = playerProfilePageUrl.replaceAll("[^\\d+]", "");
        return EspnPlayerProfilePageIdScraper.getPlayerFantasyIdMappedToHisEspnId(webRequest.getPageAsDocument(playerProfilePageUrl), Integer.parseInt(playerEspnId));
    }
}
