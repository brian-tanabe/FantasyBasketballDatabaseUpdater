package com.btanabe2.fbdu.dp.data.scrapers;

import com.btanabe2.fbdu.dp.data.models.NumberFireNbaTeamModel;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by BTanabe on 11/11/2014.
 */
public class NumberFireJsonPageScraper {

    public List<NumberFireNbaTeamModel> getNumberFireNbaTeamModels(Document document) {
        JSONObject playersObject = new JSONObject(findProjectionJavascript(document.select("script[type=text/javascript]"))).getJSONObject("teams");
        List<Map<String, String>> teamAttributeMapList = getAllJsonObjectsKeyToValueMap(playersObject);

        List<NumberFireNbaTeamModel> nbaTeamModels = new ArrayList<>(teamAttributeMapList.size());
        nbaTeamModels.addAll(teamAttributeMapList.stream().map(nbaTeamAttributeMap -> new NumberFireNbaTeamModel(nbaTeamAttributeMap.getOrDefault("abbrev", "UNKNOWN TEAM"), Integer.parseInt(nbaTeamAttributeMap.getOrDefault("id", "-1")), Integer.parseInt(nbaTeamAttributeMap.getOrDefault("espn_id", "-1")))).collect(Collectors.toList()));

        return nbaTeamModels;
    }

    public List<Map<String, String>> getProjectionAttributeMapList(Document document) {
        return getProjectionAttributeMapList(document, "players");
    }

    public List<Map<String, String>> getProjectionAttributeMapList(Document document, String key) {
        String playersJavascriptString = findProjectionJavascript(document.select("script[type=text/javascript]"));
        JSONObject playersJsonObject = new JSONObject(playersJavascriptString);
        Object playersObject = playersJsonObject.get(key);
        return playersObject instanceof JSONArray ? getAllJsonArrayObjectsKeyToValueMap((JSONArray) playersObject) : getAllJsonObjectsKeyToValueMap((JSONObject) playersObject);
    }

    private List<Map<String, String>> getAllJsonArrayObjectsKeyToValueMap(JSONArray allObjects) {
        List<Map<String, String>> keyToValueMapList = new ArrayList<>(allObjects.length());
        for (int index = 0; index < allObjects.length(); index++) {
            keyToValueMapList.add(parseProjectionGroup(allObjects.getJSONObject(index)));
        }

        return keyToValueMapList;
    }

    private List<Map<String, String>> getAllJsonObjectsKeyToValueMap(JSONObject allObjects) {
        List<Map<String, String>> keyToValueMapList = new ArrayList<>(allObjects.keySet().size());
        for (String key : (Set<String>) allObjects.keySet()) {
            keyToValueMapList.add(parseProjectionGroup(allObjects.getJSONObject(key)));
        }

        return keyToValueMapList;
    }

    private Map<String, String> parseProjectionGroup(JSONObject projectionGroup) {
        Map<String, String> projectionMap = new LinkedHashMap<>(projectionGroup.keySet().size());

        for (int mapKeyIndex = 0; mapKeyIndex < projectionGroup.names().length(); mapKeyIndex++) {
            String keyString = projectionGroup.names().getString(mapKeyIndex);
            String valueString = projectionGroup.getString(keyString);

            projectionMap.put(keyString, valueString);
        }

        return projectionMap;
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
