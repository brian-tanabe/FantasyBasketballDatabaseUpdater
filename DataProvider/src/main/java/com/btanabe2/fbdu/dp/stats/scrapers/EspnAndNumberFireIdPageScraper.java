package com.btanabe2.fbdu.dp.stats.scrapers;

import com.btanabe2.fbdu.dm.models.PlayerBiographyEntity;
import com.btanabe2.fbdu.dp.models.NumberFireNbaTeamModel;
import org.jsoup.nodes.Document;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by BTanabe on 11/18/2014.
 */
public class EspnAndNumberFireIdPageScraper {

    public List<PlayerBiographyEntity> scrapePlayerBiographiesFromPage(Document page) throws ParseException {
        List<Map<String, String>> playerAttributeMapObjects = getAllPlayersOnPageAttributesAsMap(page);
        List<NumberFireNbaTeamModel> nbaTeams = getNumberFireNbaTeams(page);

        List<PlayerBiographyEntity> playerEntities = new ArrayList<>(playerAttributeMapObjects.size());
        playerEntities.addAll(playerAttributeMapObjects.stream().map(playerAttributes -> populatePlayerBiographyEntityFields(playerAttributes, nbaTeams)).collect(Collectors.toList()));

        return playerEntities;
    }

    private List<Map<String, String>> getAllPlayersOnPageAttributesAsMap(Document page){
        NumberFireJsonPageScraper scraper = new NumberFireJsonPageScraper();
        return scraper.getProjectionAttributeMapList(page, "players");
    }

    private List<NumberFireNbaTeamModel> getNumberFireNbaTeams(Document page){
        NumberFireJsonPageScraper scraper = new NumberFireJsonPageScraper();
        return scraper.getNumberFireNbaTeamModels(page);
    }

    private PlayerBiographyEntity populatePlayerBiographyEntityFields(Map<String, String> playerAttributes, List<NumberFireNbaTeamModel> nbaTeams) {
        PlayerBiographyEntity player = new PlayerBiographyEntity();
        try {
            player.setName(String.format("%s %s", playerAttributes.get("first_name"), playerAttributes.get("last_name")));
            player.setEspnid(Integer.parseInt(playerAttributes.get("espn_id")));
            player.setNumberfireid(Integer.parseInt(playerAttributes.get("id")));
            player.setNbateamid(nbaTeams.stream().filter(t -> t.getNumberFireId() == Integer.parseInt(playerAttributes.get("team_id"))).limit(1).collect(Collectors.toList()).get(0).getEspnId());
        } catch (Exception exception){
            exception.printStackTrace();
        } finally {
            return player;
        }
    }
}
