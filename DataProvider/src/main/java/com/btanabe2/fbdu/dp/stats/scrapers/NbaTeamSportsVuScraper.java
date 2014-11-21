package com.btanabe2.fbdu.dp.stats.scrapers;

import com.btanabe2.fbdu.dp.models.NbaTeamSportsVuModel;
import com.btanabe2.fbdu.dp.web.WebRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BTanabe on 11/21/2014.
 */
public class NbaTeamSportsVuScraper {
    private WebRequest webRequest;

    public NbaTeamSportsVuScraper(WebRequest webRequest){
        this.webRequest = webRequest;
    }

    public List<NbaTeamSportsVuModel> getAllNbaTeams(){

        return new ArrayList<>();
    }
}
