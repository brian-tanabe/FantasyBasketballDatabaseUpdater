package com.btanabe2.fbdu.dp.stats.scrapers;

import com.btanabe2.fbdu.dm.models.PlayerBiographyEntity;
import com.btanabe2.fbdu.dp.models.NumberFireNbaTeamModel;
import org.jsoup.nodes.Document;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by BTanabe on 11/10/2014.
 */
public class NumberFireRankingPageScraper {
    private List<NumberFireNbaTeamModel> nbaTeams = new ArrayList<>();
    private LinkedHashMap<Integer, PlayerBiographyEntity> numberFireIdToPlayerBiographyEntityMap = new LinkedHashMap<>();

    public void scrapeForPlayers(Document document) throws ParseException {
        NumberFireJsonPageScraper scraper = new NumberFireJsonPageScraper();
        nbaTeams = scraper.getNumberFireNbaTeamModels(document);
        scraper.getProjectionAttributeMapList(document).forEach(this::parseJsonDataAndStoreInPlayerBiographyEntityObject);
    }

    private void parseJsonDataAndStoreInPlayerBiographyEntityObject(Map<String, String> playerJsonProjection) {
        try {
            PlayerBiographyEntity playerBiographyEntity = new PlayerBiographyEntity();

            playerBiographyEntity.setNumberfireid(Integer.parseInt(playerJsonProjection.getOrDefault("id", "-1")));
            playerBiographyEntity.setEspnid(Integer.parseInt(playerJsonProjection.getOrDefault("espn_id", "-1")));
            playerBiographyEntity.setYahooid(Integer.parseInt(playerJsonProjection.getOrDefault("yahoo_id", "-1")));
            playerBiographyEntity.setName(playerJsonProjection.getOrDefault("name", "UNKNOWN NAME"));
            playerBiographyEntity.setExperience(Integer.parseInt(playerJsonProjection.getOrDefault("experience", "-1")));
            playerBiographyEntity.setBirthday(new Date(new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH).parse(playerJsonProjection.getOrDefault("dob", "1900-01-01")).getTime()));
            playerBiographyEntity.setNbateamid(nbaTeams.stream().filter(t -> t.getNumberFireId() == Integer.parseInt(playerJsonProjection.get("team_id"))).limit(1).collect(Collectors.toList()).get(0).getEspnId());

            numberFireIdToPlayerBiographyEntityMap.put(playerBiographyEntity.getNumberfireid(), playerBiographyEntity);
        } catch (ParseException ex){
            ex.printStackTrace();
        }
    }

    public List<PlayerBiographyEntity> getPlayerBiographyEntitiesList(){
        return new ArrayList(numberFireIdToPlayerBiographyEntityMap.values());
    }

    public Map<Integer, PlayerBiographyEntity> getPlayerBiographyEntitiesListMap(){
        return numberFireIdToPlayerBiographyEntityMap;
    }
}
