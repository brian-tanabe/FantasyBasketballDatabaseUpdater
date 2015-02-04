package com.btanabe2.fbdu.dp.data.providers;

import com.btanabe2.fbdu.dm.models.NbaTeamEntity;
import com.btanabe2.fbdu.dp.data.models.NbaTeamModel;
import com.btanabe2.fbdu.dp.data.models.NbaTeamSportsVuModel;
import com.btanabe2.fbdu.dp.data.models.NumberFireNbaTeamModel;
import com.btanabe2.fbdu.dp.data.scrapers.NbaTeamJsonParser;
import com.btanabe2.fbdu.dp.data.scrapers.NbaTeamSportsVuScraper;
import com.btanabe2.fbdu.dp.data.scrapers.NumberFireJsonPageScraper;
import com.btanabe2.fbdu.dp.web.WebRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.btanabe2.fbdu.dp.web.WebConstants.NUMBER_FIRE_REMAINING_PROJECTIONS_GUARDS_URL;

/**
 * Created by brian on 11/6/14.
 */
public class NbaTeamProvider {

    public static List<NbaTeamEntity> getAllNbaTeamEntities(WebRequest webRequest) throws IOException {
        List<NbaTeamModel> teamNamesAndAbbreviations = NbaTeamJsonParser.getAllNbaTeams();

        NbaTeamSportsVuScraper scraper = new NbaTeamSportsVuScraper(webRequest);
        List<NbaTeamSportsVuModel> teamIdsAndAbbreviations = scraper.getAllNbaTeams();

        NumberFireJsonPageScraper nbaTeamAbbreviationAndEspnIdScraper = new NumberFireJsonPageScraper();
        List<NumberFireNbaTeamModel> nbaTeamAbbreviationsAndEspnIds = nbaTeamAbbreviationAndEspnIdScraper.getNumberFireNbaTeamModels(webRequest.getPageAsDocument(NUMBER_FIRE_REMAINING_PROJECTIONS_GUARDS_URL));

        List<NbaTeamEntity> teams = new ArrayList<>(30);
        for (NbaTeamModel nbaTeamFromJsonFile : teamNamesAndAbbreviations) {
            NbaTeamSportsVuModel idObject = teamIdsAndAbbreviations.stream().filter(a -> a.getAbbreviation().equals(nbaTeamFromJsonFile.getAbbreviation())).limit(1).collect(Collectors.toList()).get(0);
            NumberFireNbaTeamModel numberFireNbaTeamModel = nbaTeamAbbreviationsAndEspnIds.stream().filter(a -> a.getAbbreviation().equals(nbaTeamFromJsonFile.getNumberFireAbbreviation())).limit(1).collect(Collectors.toList()).get(0);

            NbaTeamEntity team = new NbaTeamEntity();
            team.setId(idObject.getId());
            team.setAbbreviation(nbaTeamFromJsonFile.getAbbreviation());
            team.setName(nbaTeamFromJsonFile.getName());
            team.setLocation(nbaTeamFromJsonFile.getLocation());
            team.setEspnId(numberFireNbaTeamModel.getEspnId());
            team.setNumberFireId(numberFireNbaTeamModel.getNumberFireId());

            teams.add(team);
        }

        return teams;
    }
}
