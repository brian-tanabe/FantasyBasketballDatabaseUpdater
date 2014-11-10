package com.btanabe2.fbdu.dp.fixtures;

import org.jsoup.nodes.Document;

/**
 * Created by BTanabe on 11/10/2014.
 */
public class NumberFirePageFixture {

    public static Document getNumberFireRankingsPageDocument(){
        return FileDocumentor.getDocumentFromFileHtml("./DataProvider/src/test/resources/webpages/number-fire-pages/number-fire-rankings.html");
    }
}
