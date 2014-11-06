package com.btanabe2.fbdu.dp.fixtures;

import org.jsoup.nodes.Document;

/**
 * Created by BTanabe on 11/6/2014.
 */
public class BasketballReferencePageFixture {

    public static Document getBasketballReferenceTeamsPage(){
        return FileDocumentor.getDocumentFromFileHtml("./DataProvider/src/test/resources/webpages/basketball-reference-pages/basketball-reference-teams-page.html");
    }

    public static Document getBasketballReferenceStandingsPage(){
        return FileDocumentor.getDocumentFromFileHtml("./DataProvider/src/test/resources/webpages/basketball-reference-pages/basketball-reference-standings-page.html");
    }

    public static Document getBasketballReferenceAtlantaFranchisePage(){
        return FileDocumentor.getDocumentFromFileHtml("./DataProvider/src/test/resources/webpages/basketball-reference-pages/basketball-reference-atl-page.html");
    }

    public static Document getBasketballReferenceTorontoFranchisePage(){
        return FileDocumentor.getDocumentFromFileHtml("./DataProvider/src/test/resources/webpages/basketball-reference-pages/basketball-reference-tor-page.html");
    }
}
