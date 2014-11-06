package com.btanabe2.fbdu.dp.fixtures;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;

/**
 * Created by brian on 11/6/14.
 */
public class FileDocumentor {

    public static Document getDocumentFromFileHtml(String path){
        try {
            return Jsoup.parse(new File(path), "UTF8");
        } catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
}
