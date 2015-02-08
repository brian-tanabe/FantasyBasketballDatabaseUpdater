package com.btanabe2.fbdu.dp.json.playerProfilePage.fantasy;

/**
 * Created by Brian on 2/7/15.
 */
public class EspnPlayerProfileJsonModel {
    public int playerId;
    public double percentChange;
    public Object averageDraftPosition;
    public double percentOwned;
    public double playerRaterSEASON;
    public double playerRater7DAY;
    public String fullName;
    public double playerRater30DAY;
    public double playerRater15DAY;
    public int positionRank;

    class mostRecentNews {
        public String news;
        public String spin;
        public String date;
    }

    class seasonOutlook {
        public String outlook;
        public int seasonId;
        public String date;
    }
}
