package com.btanabe2.fbdu.dp.scrapers;

import com.btanabe2.fbdu.dm.models.PlayerBiographyEntity;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by BTanabe on 11/10/2014.
 */
public class NumberFireRankingScraper {
    private LinkedHashMap<Integer, PlayerBiographyEntity> numberFireIdToPlayerBiographyEntityMap = new LinkedHashMap<>();

    public void scrapeForPlayers(Document document) throws ParseException {
        Elements scriptElements = document.select("script[type=text/javascript]");
        String projectionJavascriptString = findProjectionJavascript(scriptElements);
        JSONObject jsonObject = new JSONObject(projectionJavascriptString);

        Map<Integer, Integer> numberFireTeamIdToEspnTeamIdMap = extractNumberFireTeamIdToEspnTeamIdFromTeamsJson(jsonObject.getJSONObject("teams"));
        extractPlayerDataFromJson(numberFireTeamIdToEspnTeamIdMap, jsonObject.getJSONArray("players"));

        System.out.print("");
    }

    private String findProjectionJavascript(Elements scriptElements){
        for(Element script : scriptElements){
            if(script.html().contains("NF_DATA"))
                return extractJustTheJsonFromJavascript(script.html());
        }

        return "";
    }

    private String extractJustTheJsonFromJavascript(String javascriptJson){
        return javascriptJson.substring(javascriptJson.indexOf("NF_DATA = ") + "NF_DATA = ".length(), javascriptJson.indexOf("};") + "}:".length());
    }

    private Map<Integer, Integer> extractNumberFireTeamIdToEspnTeamIdFromTeamsJson(JSONObject teamsJsonObject){
        Map<Integer, Integer> numberFireTeamIdToEspnTeamIdMap = new LinkedHashMap<>(30);

        for(String jsonElementName : teamsJsonObject.getNames(teamsJsonObject)){
            JSONObject jsonElement = teamsJsonObject.getJSONObject(jsonElementName);
            int numberFireId = jsonElement.getInt("id");
            int espnId = jsonElement.getInt("espn_id");

            numberFireTeamIdToEspnTeamIdMap.put(numberFireId, espnId);
        }

        return numberFireTeamIdToEspnTeamIdMap;
    }

    private void extractPlayerDataFromJson(Map<Integer, Integer> numberFireTeamIdToEspnTeamIdMap, JSONArray playersJson) throws ParseException {
        for(int index = 0; index < playersJson.length(); index++){
            JSONObject playerJsonObject = playersJson.getJSONObject(index);

            PlayerBiographyEntity playerBiography = new PlayerBiographyEntity();
            playerBiography.setEspnid(playerJsonObject.getInt("espn_id"));
            playerBiography.setYahooid(playerJsonObject.getInt("yahoo_id"));
            playerBiography.setExperience(playerJsonObject.getInt("experience"));
            playerBiography.setBirthday(new java.sql.Date(new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH).parse(playerJsonObject.getString("dob")).getTime()));
            playerBiography.setNbateamid(numberFireTeamIdToEspnTeamIdMap.get(playerJsonObject.getInt("team_id")));
//            playerBiography.setName(playerJsonObject.getString("name"));      // TODO change field name to string

            numberFireIdToPlayerBiographyEntityMap.put(playerJsonObject.getInt("id"), playerBiography);
        }


        System.out.print("");
    }

    public List<PlayerBiographyEntity> getPlayerBiographyEntitiesList(){
        return new ArrayList(numberFireIdToPlayerBiographyEntityMap.values());
    }

    public Map<Integer, PlayerBiographyEntity> getPlayerBiographyEntitiesListMap(){
        return numberFireIdToPlayerBiographyEntityMap;
    }
}
