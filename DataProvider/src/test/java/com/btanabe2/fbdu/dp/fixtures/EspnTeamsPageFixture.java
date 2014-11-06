package com.btanabe2.fbdu.dp.fixtures;

import org.jsoup.nodes.Document;

/**
 * Created by brian on 11/6/14.
 */
public class EspnTeamsPageFixture {

    public static Document getEspnTeamsPage(){
        return FileDocumentor.getDocumentFromFileHtml("./DataProvider/src/test/resources/webpages/espn-team-pages/espn-teams-page.html");
    }

    public static Document getEspnBostonStadiumPage(){
        return FileDocumentor.getDocumentFromFileHtml("./DataProvider/src/test/resources/webpages/espn-team-pages/espn-boston-stadium-page.html");
    }
}
