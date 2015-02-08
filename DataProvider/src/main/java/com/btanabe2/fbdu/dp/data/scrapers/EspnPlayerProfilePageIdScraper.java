package com.btanabe2.fbdu.dp.data.scrapers;

import com.btanabe2.fbdu.dp.json.playerProfilePage.fantasy.EspnPlayerProfileJsonModel;
import com.google.gson.Gson;
import org.jsoup.nodes.Document;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Brian on 2/7/15.
 */
public class EspnPlayerProfilePageIdScraper {

    public static Map<Integer, Integer> getPlayerFantasyIdMappedToHisEspnId(Document document, int playerEspnIdNotFantasyId) throws NullPointerException {
        Map<Integer, Integer> playerEspnIdMappedToHisFantasyId = new HashMap<>(1);
        playerEspnIdMappedToHisFantasyId.put(new Gson().fromJson(document.select("div#fantasy-content").text(), EspnPlayerProfileJsonModel.class).playerId, playerEspnIdNotFantasyId);
        return playerEspnIdMappedToHisFantasyId;
    }
}
