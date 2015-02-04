package com.btanabe2.fbdu.dp.data.scrapers;

import com.btanabe2.fbdu.dm.models.PositionsEntity;
import com.btanabe2.fbdu.dp.data.models.EspnPositionEligibilityModel;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Brian on 2/2/15.
 */
public class EspnProjectionsPageScraper {

    public static List<EspnPositionEligibilityModel> getEspnPlayerPositionEligibilities(Document document, List<PositionsEntity> positionsEntities) {
        List<EspnPositionEligibilityModel> players = new ArrayList<>(50);
        extractPlayersOnPage(document).forEach(p -> players.addAll(extractPositionEligibilityAndPopulateEspnPositionEligibilityModels(p, extractPlayerId(p), positionsEntities)));
        return players;
    }

    private static Elements extractPlayersOnPage(Document document) {
        return document.select("td.playertablePlayerName");
    }

    private static int extractPlayerId(Element playerElement) {
        try {
            return Integer.parseInt(playerElement.select("a").attr("playerId"));
        } catch (Exception ex) {
            return 0;
        }
    }

    private static List<EspnPositionEligibilityModel> extractPositionEligibilityAndPopulateEspnPositionEligibilityModels(Element playerElement, int espnPlayerId, List<PositionsEntity> positionsEntities) {
        List<EspnPositionEligibilityModel> positionEligibilityModels = new ArrayList<>();

        // String positionAndTeamString = playerElement.text().replace(name, "").replace(",", "").replace("\u00a0"," ").replaceAll("[^a-zA-Z /]", "").trim();

//        String positionAndTeamString = playerElement.text().replace(",", "").replace("\u00a0"," ").replaceAll("[^a-zA-Z /]", "").trim();
//        if(positionAndTeamString.toLowerCase().contains("lebron james") || positionAndTeamString.toLowerCase().contains("james harden") || positionAndTeamString.toLowerCase().contains("pau gasol")) {
//            System.out.println(espnPlayerId + " " + positionAndTeamString);
//        }

        Stream.of(playerElement.text().trim().replace(",", "").replace("\u00a0", " ").split(" ")).filter(w -> positionsEntities.stream().anyMatch(p -> p.getAbbreviation().equals(w))).forEach(p -> positionEligibilityModels.add(new EspnPositionEligibilityModel(espnPlayerId, positionsEntities.stream().filter(e -> e.getAbbreviation().equals(p)).limit(1).collect(Collectors.toList()).get(0).getId())));

        return positionEligibilityModels;
    }
}
