package com.btanabe2.fbdu.dp.fixtures;

import org.jsoup.nodes.Document;

/**
 * Created by brian on 11/6/14.
 */
public class EspnTeamsPageFixture {

    public static Document getEspnTeamsPageAsDocument(){
        return FileDocumentor.getDocumentFromFileHtml("./DataProvider/src/test/resources/webpages/espn-teams-page.html");
    }
}
