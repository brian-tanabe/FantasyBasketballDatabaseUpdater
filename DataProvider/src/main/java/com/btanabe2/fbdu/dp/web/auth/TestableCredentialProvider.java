package com.btanabe2.fbdu.dp.web.auth;

/**
 * Created by brian on 11/14/14.
 */
public class TestableCredentialProvider implements CredentialProviderI {

    public String getUserName() {
        return "";
    }

    public String getPassword() {
        return "";
    }

    public String getLoginUrl() {
        return "http://www.brian-tanabe.com";
    }
}
