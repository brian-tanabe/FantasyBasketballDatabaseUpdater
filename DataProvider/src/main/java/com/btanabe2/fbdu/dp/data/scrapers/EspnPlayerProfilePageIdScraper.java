package com.btanabe2.fbdu.dp.data.scrapers;

import com.btanabe2.fbdu.dp.json.playerProfilePage.fantasy.EspnPlayerProfileJsonModel;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.jsoup.nodes.Document;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Brian on 2/7/15.
 */
public class EspnPlayerProfilePageIdScraper {

    public static Map<Integer, Integer> getPlayerFantasyIdMappedToHisEspnId(Document document, int playerEspnIdNotFantasyId) throws NullPointerException {
        Map<Integer, Integer> playerEspnIdMappedToHisFantasyId = new HashMap<>(1);
        playerEspnIdMappedToHisFantasyId.put(deserializeJsonObject(document.select("div#fantasy-content").text().trim()).playerId, playerEspnIdNotFantasyId);

        return playerEspnIdMappedToHisFantasyId;
    }

    private static EspnPlayerProfileJsonModel deserializeJsonObject(String jsonString) {
        Gson jsonParser = new Gson();
        JsonReader reader = new JsonReader(new StringReader(jsonString));
        reader.setLenient(true);
        return jsonParser.fromJson(reader, EspnPlayerProfileJsonModel.class);
    }
}
