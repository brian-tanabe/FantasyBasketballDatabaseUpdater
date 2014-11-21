package com.btanabe2.fbdu.dp.fixtures;

import com.btanabe2.fbdu.dm.models.NbaTeamEntity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by BTanabe on 11/21/2014.
 */
public class NbaTeamEntityFixture {

    public static List<NbaTeamEntity> getMockNbaTeams(){
        Map<String, Integer> abbreviationToTeamMap = new LinkedHashMap<>(30);
        abbreviationToTeamMap.put("ATL", 1610612737);
        abbreviationToTeamMap.put("BOS", 1610612738);
        abbreviationToTeamMap.put("CLE", 1610612739);
        abbreviationToTeamMap.put("NOP", 1610612740);
        abbreviationToTeamMap.put("CHI", 1610612741);
        abbreviationToTeamMap.put("DAL", 1610612742);
        abbreviationToTeamMap.put("DEN", 1610612743);
        abbreviationToTeamMap.put("GSW", 1610612744);
        abbreviationToTeamMap.put("HOU", 1610612745);
        abbreviationToTeamMap.put("LAC", 1610612746);
        abbreviationToTeamMap.put("LAL", 1610612747);
        abbreviationToTeamMap.put("MIA", 1610612748);
        abbreviationToTeamMap.put("MIL", 1610612749);
        abbreviationToTeamMap.put("MIN", 1610612750);
        abbreviationToTeamMap.put("BKN", 1610612751);
        abbreviationToTeamMap.put("NYK", 1610612752);
        abbreviationToTeamMap.put("ORL", 1610612753);
        abbreviationToTeamMap.put("IND", 1610612754);
        abbreviationToTeamMap.put("PHI", 1610612755);
        abbreviationToTeamMap.put("PHX", 1610612756);
        abbreviationToTeamMap.put("POR", 1610612757);
        abbreviationToTeamMap.put("SAC", 1610612758);
        abbreviationToTeamMap.put("SAS", 1610612759);
        abbreviationToTeamMap.put("OKC", 1610612760);
        abbreviationToTeamMap.put("TOR", 1610612761);
        abbreviationToTeamMap.put("UTA", 1610612762);
        abbreviationToTeamMap.put("MEM", 1610612763);
        abbreviationToTeamMap.put("WAS", 1610612764);
        abbreviationToTeamMap.put("DET", 1610612765);
        abbreviationToTeamMap.put("CHA", 1610612766);

        List<NbaTeamEntity> nbaTeams = new ArrayList<>(30);
        abbreviationToTeamMap.keySet().forEach(k -> nbaTeams.add(new NbaTeamEntity(abbreviationToTeamMap.get(k), k)));

        return nbaTeams;
    }
}
