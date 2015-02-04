package com.btanabe2.fbdu.dp.data.scrapers;

import com.btanabe2.fbdu.dm.models.PositionsEntity;
import com.btanabe2.fbdu.dp.models.EspnPositionEligibilityModel;
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
        Elements playerElements = extractPlayersOnPage(document);
        for (Element playerElement : playerElements) {
            int espnPlayerId = extractPlayerId(playerElement);
            players.addAll(extractPositionEligibilityAndPopulateEspnPositionEligibilityModels(playerElement, espnPlayerId, positionsEntities));
        }

        return players;
    }

    private static Elements extractPlayersOnPage(Document document) {
        return document.select("td.playertablePlayerName");
    }

    private static int extractPlayerId(Element playerElement) {
        try {
            String playerIdString = playerElement.select("a").attr("playerId");
            return Integer.parseInt(playerIdString);
        } catch (Exception ex) {
            return 0;
        }
    }

    private static List<EspnPositionEligibilityModel> extractPositionEligibilityAndPopulateEspnPositionEligibilityModels(Element playerElement, int espnPlayerId, List<PositionsEntity> positionsEntities) {
        List<EspnPositionEligibilityModel> positionEligibilityModels = new ArrayList<>();

//        String positionAndTeamString = playerElement.text().replace(name, "").replace(",", "").replace("\u00a0"," ").replaceAll("[^a-zA-Z /]", "").trim();
        Stream.of(playerElement.text().trim().replace(",", "").replace("\u00a0", " ").split(" ")).filter(w -> positionsEntities.stream().anyMatch(p -> p.getAbbreviation().equals(w))).forEach(p -> positionEligibilityModels.add(new EspnPositionEligibilityModel(espnPlayerId, positionsEntities.stream().filter(e -> e.getAbbreviation().equals(p)).limit(1).collect(Collectors.toList()).get(0).getId())));

        return positionEligibilityModels;
    }
}
