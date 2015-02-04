package com.btanabe2.fbdu.dp.fixtures;

import com.btanabe2.fbdu.dp.helpers.FileDocumentor;
import org.jsoup.nodes.Document;

/**
 * Created by BTanabe on 11/10/2014.
 */
public class NumberFirePageFixture {

    public static Document getNumberFireRankingsPageDocument() {
        return FileDocumentor.getDocumentFromFileHtml("./DataProvider/src/test/resources/webpages/number-fire-pages/number-fire-rankings.html");
    }

    public static Document getNumberFireRemainingSeasonGuardsProjectionsPageDocument() {
        return FileDocumentor.getDocumentFromFileHtml("./DataProvider/src/test/resources/webpages/number-fire-pages/number-fire-remaining-season-projections-guards.html");
    }

    public static Document getNumberFireRemainingSeasonForwardsProjectionsPageDocument() {
        return FileDocumentor.getDocumentFromFileHtml("./DataProvider/src/test/resources/webpages/number-fire-pages/number-fire-remaining-season-projections-forwards.html");
    }

    public static Document getNumberFireRemainingSeasonCentersProjectionsPageDocument() {
        return FileDocumentor.getDocumentFromFileHtml("./DataProvider/src/test/resources/webpages/number-fire-pages/number-fire-remaining-season-projections-centers.html");
    }

    public static Document getNumberFireDailyProjectionsPageDocument() {
        return FileDocumentor.getDocumentFromFileHtml("./DataProvider/src/test/resources/webpages/number-fire-pages/number-fire-daily-projections.html");
    }
}
