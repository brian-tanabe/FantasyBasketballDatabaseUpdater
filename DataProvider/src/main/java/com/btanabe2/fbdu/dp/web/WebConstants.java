package com.btanabe2.fbdu.dp.web;

/**
 * Created by brian on 11/6/14.
 */
public class WebConstants {
    public static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.10; rv:32.0) Gecko/20100101 Firefox/32.0";

    ////////// SPORTSVU: //////////
    public static final String SPORTS_VU_ALL_PLAYERS_URL = "http://stats.nba.com/stats/commonallplayers?LeagueID=00&Season=2014-15&IsOnlyCurrentSeason=1";

    public static String getPlayerInfoFromSportsVu(int playerId){
        return String.format("http://stats.nba.com/stats/commonplayerinfo?PlayerID=%d", playerId);
    }

    ////////// ESPN: //////////
    public static final String ESPN_TEAMS_PAGE_URL = "http://espn.go.com/nba/teams";
    public static final String ESPN_FANTASY_BASKETBALL_HOMEPAGE = "http://games.espn.go.com/frontpage/basketball";

    public static String getEspnLoginUrl(String username, String password){
        return String.format("https://r.espn.go.com/members/login?count=0&SUBMIT=1&language=en&affiliateName=espn&regFormId=espn&username=%s&password=%s&appRedirect=https://r.espn.go.com/members/index", username, password);
    }

    ////////// BASKETBALL REFERENCE: //////////
    public static final String BASKETBALL_REFERENCE_TEAM_PAGE_URL = "http://www.basketball-reference.com/teams/";

    public static final String BASKETBALL_REFERENCE_STANDINGS_PAGE_URL = "http://www.basketball-reference.com/leagues/NBA_2015_standings.html";
    public static final String BASKETBALL_REFERENCE_ATL_PAGE_URL = "http://www.basketball-reference.com/teams/ATL/";
    public static final String BASKETBALL_REFERENCE_BOS_PAGE_URL = "http://www.basketball-reference.com/teams/BOS/";
    public static final String BASKETBALL_REFERENCE_BKN_PAGE_URL = "http://www.basketball-reference.com/teams/NJN/";
    public static final String BASKETBALL_REFERENCE_CHA_PAGE_URL = "http://www.basketball-reference.com/teams/CHA/";
    public static final String BASKETBALL_REFERENCE_CHI_PAGE_URL = "http://www.basketball-reference.com/teams/CHI/";
    public static final String BASKETBALL_REFERENCE_CLE_PAGE_URL = "http://www.basketball-reference.com/teams/CLE/";
    public static final String BASKETBALL_REFERENCE_DAL_PAGE_URL = "http://www.basketball-reference.com/teams/DAL/";
    public static final String BASKETBALL_REFERENCE_DEN_PAGE_URL = "http://www.basketball-reference.com/teams/DEN/";
    public static final String BASKETBALL_REFERENCE_DET_PAGE_URL = "http://www.basketball-reference.com/teams/DET/";
    public static final String BASKETBALL_REFERENCE_GSW_PAGE_URL = "http://www.basketball-reference.com/teams/GSW/";
    public static final String BASKETBALL_REFERENCE_HOU_PAGE_URL = "http://www.basketball-reference.com/teams/HOU/";
    public static final String BASKETBALL_REFERENCE_IND_PAGE_URL = "http://www.basketball-reference.com/teams/IND/";
    public static final String BASKETBALL_REFERENCE_LAC_PAGE_URL = "http://www.basketball-reference.com/teams/LAC/";
    public static final String BASKETBALL_REFERENCE_LAL_PAGE_URL = "http://www.basketball-reference.com/teams/LAL/";
    public static final String BASKETBALL_REFERENCE_MEM_PAGE_URL = "http://www.basketball-reference.com/teams/MEM/";
    public static final String BASKETBALL_REFERENCE_MIA_PAGE_URL = "http://www.basketball-reference.com/teams/MIA/";
    public static final String BASKETBALL_REFERENCE_MIL_PAGE_URL = "http://www.basketball-reference.com/teams/MIL/";
    public static final String BASKETBALL_REFERENCE_MIN_PAGE_URL = "http://www.basketball-reference.com/teams/MIN/";
    public static final String BASKETBALL_REFERENCE_NOH_PAGE_URL = "http://www.basketball-reference.com/teams/NOH/";
    public static final String BASKETBALL_REFERENCE_NYK_PAGE_URL = "http://www.basketball-reference.com/teams/NYK/";
    public static final String BASKETBALL_REFERENCE_OKC_PAGE_URL = "http://www.basketball-reference.com/teams/OKC/";
    public static final String BASKETBALL_REFERENCE_ORL_PAGE_URL = "http://www.basketball-reference.com/teams/ORL/";
    public static final String BASKETBALL_REFERENCE_PHI_PAGE_URL = "http://www.basketball-reference.com/teams/PHI/";
    public static final String BASKETBALL_REFERENCE_PHX_PAGE_URL = "http://www.basketball-reference.com/teams/PHX/";
    public static final String BASKETBALL_REFERENCE_POR_PAGE_URL = "http://www.basketball-reference.com/teams/POR/";
    public static final String BASKETBALL_REFERENCE_SAC_PAGE_URL = "http://www.basketball-reference.com/teams/SAC/";
    public static final String BASKETBALL_REFERENCE_SAS_PAGE_URL = "http://www.basketball-reference.com/teams/SAS/";
    public static final String BASKETBALL_REFERENCE_TOR_PAGE_URL = "http://www.basketball-reference.com/teams/TOR/";
    public static final String BASKETBALL_REFERENCE_UTA_PAGE_URL = "http://www.basketball-reference.com/teams/UTA/";
    public static final String BASKETBALL_REFERENCE_WAS_PAGE_URL = "http://www.basketball-reference.com/teams/WAS/";


    public static final String NUMBER_FIRE_SEASON_STATS_URL = "https://www.numberfire.com/nba/fantasy/season-stats";
    public static final String NUMBER_FIRE_DAILY_PROJECTION_URL = "https://www.numberfire.com/nba/fantasy/fantasy-basketball-projections";
    public static final String NUMBER_FIRE_YEARLY_PROJECTIONS_URL = "https://www.numberfire.com/nba/fantasy/yearly-projections";

    private static final String NUMBER_FIRE_REMAINING_PROJECTIONS_URL = "https://www.numberfire.com/nba/fantasy/remaining-projections";
    public static final String NUMBER_FIRE_REMAINING_PROJECTIONS_GUARDS_URL = NUMBER_FIRE_REMAINING_PROJECTIONS_URL + "/guards";
    public static final String NUMBER_FIRE_REMAINING_PROJECTIONS_FORWARDS_URL = NUMBER_FIRE_REMAINING_PROJECTIONS_URL + "/forwards";
    public static final String NUMBER_FIRE_REMAINING_PROJECTIONS_CENTERS_URL = NUMBER_FIRE_REMAINING_PROJECTIONS_URL + "/centers";
}
