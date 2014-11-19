package com.btanabe2.fbdu.dp.stats.scrapers;

import com.btanabe2.fbdu.dm.models.NfRemainingSeasonProjectionsEntity;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by BTanabe on 11/11/2014.
 */
public class NumberFireRemainingSeasonProjectionsPageScraper {

    public List<NfRemainingSeasonProjectionsEntity> scrapeRemainingSeasonProjectionsFromPage(Document page){
        NumberFireJsonPageScraper jsonPageScraper = new NumberFireJsonPageScraper();
        List<Map<String, String>> playerProjectionsJson = jsonPageScraper.getProjectionAttributeMapList(page, "projections");

        List<NfRemainingSeasonProjectionsEntity> playerProjectionEntities = new ArrayList<NfRemainingSeasonProjectionsEntity>();
        for(Map<String, String> playerProjection : playerProjectionsJson){
            int numberFirePlayerId = Integer.parseInt(playerProjection.get("nba_player_id"));

            NfRemainingSeasonProjectionsEntity playerProjectionEntity = new NfRemainingSeasonProjectionsEntity();
            playerProjectionEntity.setMinutes(Integer.parseInt(playerProjection.get("minutes")));

            playerProjectionEntities.add(playerProjectionEntity);
        }

        return playerProjectionEntities;
    }
}
