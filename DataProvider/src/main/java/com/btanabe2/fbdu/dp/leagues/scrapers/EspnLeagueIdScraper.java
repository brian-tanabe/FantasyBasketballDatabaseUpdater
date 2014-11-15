package com.btanabe2.fbdu.dp.leagues.scrapers;

import com.btanabe2.fbdu.dp.web.SecureWebRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brian on 11/14/14.
 */
public class EspnLeagueIdScraper {
    private SecureWebRequest webRequest;

    public EspnLeagueIdScraper(SecureWebRequest webRequest){
        this.webRequest = webRequest;
    }

    public List<String> findFantasyLeagueHomePageUrls(){

        return new ArrayList<String>();
    }
}
