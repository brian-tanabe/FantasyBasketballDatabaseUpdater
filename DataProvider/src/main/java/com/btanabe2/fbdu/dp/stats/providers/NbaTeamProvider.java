package com.btanabe2.fbdu.dp.stats.providers;

import com.btanabe2.fbdu.dm.models.NbaTeamEntity;
import com.btanabe2.fbdu.dp.models.NbaTeamSportsVuModel;
import com.btanabe2.fbdu.dp.models.NumberFireNbaTeamModel;
import com.btanabe2.fbdu.dp.stats.scrapers.NbaTeamJsonParser;
import com.btanabe2.fbdu.dp.stats.scrapers.NbaTeamSportsVuScraper;
import com.btanabe2.fbdu.dp.stats.scrapers.NumberFireJsonPageScraper;
import com.btanabe2.fbdu.dp.web.WebRequest;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static com.btanabe2.fbdu.dp.web.WebConstants.NUMBER_FIRE_REMAINING_PROJECTIONS_GUARDS_URL;

/**
 * Created by brian on 11/6/14.
 */
public class NbaTeamProvider {

    public static List<NbaTeamEntity> getAllNbaTeamEntities(WebRequest webRequest) throws IOException {
        List<NbaTeamEntity> teamNamesAndAbbreviations = NbaTeamJsonParser.getAllNbaTeams();

        NbaTeamSportsVuScraper scraper = new NbaTeamSportsVuScraper(webRequest);
        List<NbaTeamSportsVuModel> teamIdsAndAbbreviations = scraper.getAllNbaTeams();

        teamNamesAndAbbreviations.forEach(t -> t.setId(teamIdsAndAbbreviations.stream().filter(i -> i.getAbbreviation().equals(t.getAbbreviation())).limit(1).collect(Collectors.toList()).get(0).getId()));

        NumberFireJsonPageScraper nbaTeamAbbreviationAndEspnIdScraper = new NumberFireJsonPageScraper();
        List<NumberFireNbaTeamModel> nbaTeamAbbreviationsAndEspnIds =  nbaTeamAbbreviationAndEspnIdScraper.getNumberFireNbaTeamModels(webRequest.getPageAsDocument(NUMBER_FIRE_REMAINING_PROJECTIONS_GUARDS_URL));

//        teamNamesAndAbbreviations.forEach(t -> t.setEspnId(nbaTeamAbbreviationsAndEspnIds.stream().filter(i -> i.getAbbreviation().equals(t.getAbbreviation())).limit(1).collect(Collectors.toList()).get(0).getEspnId()));

        for (NbaTeamEntity teamNamesAndAbbreviation : teamNamesAndAbbreviations) {
            System.out.println(teamNamesAndAbbreviation.getName());

            teamNamesAndAbbreviation.setEspnId(nbaTeamAbbreviationsAndEspnIds.stream().filter(t -> t.getAbbreviation().equals(teamNamesAndAbbreviation.getAbbreviation())).limit(1).collect(Collectors.toList()).get(0).getEspnId());

        }
        return teamNamesAndAbbreviations;
    }
}
