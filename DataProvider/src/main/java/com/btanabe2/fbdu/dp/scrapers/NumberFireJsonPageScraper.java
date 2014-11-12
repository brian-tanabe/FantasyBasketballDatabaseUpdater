package com.btanabe2.fbdu.dp.scrapers;

import org.json.JSONObject;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;

/**
 * Created by BTanabe on 11/11/2014.
 */
public class NumberFireJsonPageScraper {

    public List<Map<String, String>> getProjectionAttributeMapList(Document document) {
        JSONObject playersObject = new JSONObject(findProjectionJavascript(document.select("script[type=text/javascript]"))).getJSONObject("players");
        List<Map<String, String>> playerProjectionMapList = new ArrayList<>(playersObject.keySet().size());
        Set<String> keys = playersObject.keySet();
        for (String key : keys) {
            JSONObject playerProjectionJsonArray = playersObject.getJSONObject(key);
            Map<String, String> playerProjectionMap = new LinkedHashMap<>(playerProjectionJsonArray.names().length());
            for (int mapKeyIndex = 0; mapKeyIndex < playerProjectionJsonArray.names().length(); mapKeyIndex++) {

                String keyString = playerProjectionJsonArray.names().getString(mapKeyIndex);
                String valueString = playerProjectionJsonArray.getString(keyString);

                playerProjectionMap.put(keyString, valueString);
            }

            playerProjectionMapList.add(playerProjectionMap);
        }

        return playerProjectionMapList;
    }

    private String findProjectionJavascript(Elements scriptElements) {
        for (Element script : scriptElements) {
            if (script.html().contains("NF_DATA"))
                return extractJustTheJsonFromJavascript(script.html());
        }

        return "";
    }

    private String extractJustTheJsonFromJavascript(String javascriptJson) {
        return javascriptJson.substring(javascriptJson.indexOf("NF_DATA = ") + "NF_DATA = ".length(), javascriptJson.indexOf("};") + "}:".length());
    }
}
