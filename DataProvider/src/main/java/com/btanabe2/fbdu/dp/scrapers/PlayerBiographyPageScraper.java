package com.btanabe2.fbdu.dp.scrapers;

import com.btanabe2.fbdu.dm.models.PlayerBiographyEntity;
import org.jsoup.nodes.Document;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by BTanabe on 11/18/2014.
 */
public class PlayerBiographyPageScraper {

    public List<PlayerBiographyEntity> scrapePlayerBiographiesFromPage(Document page) throws ParseException {

        NumberFireJsonPageScraper scraper = new NumberFireJsonPageScraper();
        List<Map<String, String>> playerAttributeMapObjects = scraper.getProjectionAttributeMapList(page, "players");
        List<PlayerBiographyEntity> playerEntities = new ArrayList<PlayerBiographyEntity>(playerAttributeMapObjects.size());
        for(Map<String, String> playerAttributes : playerAttributeMapObjects){
            PlayerBiographyEntity player = new PlayerBiographyEntity();

            player.setName(String.format("%s %s", playerAttributes.get("first_name"), playerAttributes.get("last_name")));

            if(player.getName().equals("Ty Lawson")){
                System.out.print("");
            }

            player.setEspnid(Integer.parseInt(playerAttributes.get("espn_id")));
            player.setYahooid(Integer.parseInt(playerAttributes.get("yahoo_id")));
            player.setBirthday(new Date(new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH).parse(playerAttributes.getOrDefault("dob", "1900-01-01")).getTime()));   // TODO This does not always have a value
            player.setExperience(Integer.parseInt(playerAttributes.get("experience")));
            player.setNumberfireid(Integer.parseInt(playerAttributes.get("id")));

            playerEntities.add(player);
        }

        return playerEntities;
    }
}
