package com.btanabe2.fbdu.dp.scrapers;

import com.btanabe2.fbdu.dm.models.PlayerBiographyEntity;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by BTanabe on 11/10/2014.
 */
public class NumberFireRankingScraper {
    private LinkedHashMap<Integer, PlayerBiographyEntity> numberFireIdToPlayerBiographyEntityMap = new LinkedHashMap<>();

    public NumberFireRankingScraper(Document document){
        scrapeForPlayers(document);
    }

    private void scrapeForPlayers(Document document){

    }

    public List<PlayerBiographyEntity> getPlayerBiographyEntitiesList(){
        return new ArrayList(numberFireIdToPlayerBiographyEntityMap.values());
    }

    public Map<Integer, PlayerBiographyEntity> getPlayerBiographyEntitiesListMap(){
        return numberFireIdToPlayerBiographyEntityMap;
    }
}
