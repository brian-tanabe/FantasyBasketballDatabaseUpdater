package com.btanabe2.fbdu.dp.leagues.scrapers;

import com.btanabe2.fbdu.dp.models.EspnPositionEligibilityModel;
import com.btanabe2.fbdu.dp.web.SecureWebRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brian on 2/2/15.
 */
public class EspnPositionEligibilityScraper {
    private SecureWebRequest webRequest;

    public EspnPositionEligibilityScraper(SecureWebRequest webRequest) {
        this.webRequest = webRequest;
    }

    public List<EspnPositionEligibilityModel> getEspnPlayerPositionEligibilities() {

        return new ArrayList<>();
    }
}
