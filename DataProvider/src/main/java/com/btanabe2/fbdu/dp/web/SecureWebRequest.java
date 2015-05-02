package com.btanabe2.fbdu.dp.web;

import com.btanabe2.fbdu.dp.web.auth.CredentialProviderI;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by brian on 11/14/14.
 */
public class SecureWebRequest extends WebRequest {
    private HttpContext localContext = new BasicHttpContext();
    private CookieStore cookieStore = new BasicCookieStore();
    private HttpClient client = HttpClientBuilder.create().build();

    public SecureWebRequest(String loginUrl) throws IOException {
        login(loginUrl);
    }

    @Deprecated
    public SecureWebRequest(){
        localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
    }

    private void login(String loginUrl) throws IOException {
        getLoginCookiesFromServer(loginUrl);
    }

    @Deprecated
    public SecureWebRequest login(CredentialProviderI credentialProvider) throws IOException {
        getLoginCookiesFromServer(credentialProvider);
        return this;
    }

    @Deprecated
    private void getLoginCookiesFromServer(CredentialProviderI credentialProvider) throws IOException {
        getLoginCookiesFromServer(credentialProvider.getLoginUrl());
    }

    private void getLoginCookiesFromServer(String loginUrl) throws IOException {
        HttpPost post = new HttpPost(loginUrl);

        post.setHeader("Host", "r.espn.go.com");
        post.setHeader("User-Agent", WebConstants.USER_AGENT);
        post.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        post.setHeader("Accept-Language", "en-US,en;q=0.8");
        post.setHeader("Connection", "keep-alive");
        post.setHeader("Accept-Encoding", "gzip,deflate");

        client.execute(post, localContext);
        post.releaseConnection();
    }

    public Document getPageAsDocument(String url) throws IOException {
        HttpGet request = new HttpGet(url);
        request.setHeader("User-Agent", WebConstants.USER_AGENT);
        request.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        request.setHeader("Accept-Language", "en-US,en;q=0.8");

        HttpResponse response = client.execute(request, localContext);
        Document pageDocument = Jsoup.parse(response.getEntity().getContent(), "UTF8", url);
        request.releaseConnection();

        return pageDocument;
    }
}