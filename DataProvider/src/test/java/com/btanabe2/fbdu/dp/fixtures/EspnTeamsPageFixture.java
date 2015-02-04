package com.btanabe2.fbdu.dp.fixtures;

import com.btanabe2.fbdu.dp.helpers.FileDocumentor;
import org.jsoup.nodes.Document;

/**
 * Created by brian on 11/6/14.
 */
public class EspnTeamsPageFixture {

    /**
     * Deprecated
     * <p>
     * I no longer want to play with Document objects.  Use strings instead!
     *
     * @return Document representing the ESPN teams page
     */
    @Deprecated
    public static Document getEspnTeamsPage() {
        return FileDocumentor.getDocumentFromFileHtml("./DataProvider/src/test/resources/webpages/espn-team-pages/espn-teams-page.html");
    }

    /**
     * Deprecated
     *
     * I no longer want to play with Document objects.  Use strings instead!
     *
     * @return
     *      Document representing the Boston Celtic's team page
     */
    @Deprecated
    public static Document getEspnBostonStadiumPage() {
        return FileDocumentor.getDocumentFromFileHtml("./DataProvider/src/test/resources/webpages/espn-team-pages/espn-boston-stadium-page.html");
    }

    /**
     * Deprecated
     *
     * I no longer want to play with Document objects.  Use strings instead!
     *
     * @return
     *      Document representing the ESPN Fantasy Basketball home page
     */
    @Deprecated
    public static Document getEspnFantasyBasketballHomepagePage() {
        return FileDocumentor.getDocumentFromFileHtml("./DataProvider/src/test/resources/webpages/espn-fantasy-pages/espn-fantasy-homepage.html");
    }
}
