package com.btanabe2.fbdu.dp.web.auth;

import com.btanabe2.fbdu.dp.web.WebConstants;

/**
 * Created by brian on 11/14/14.
 */
public class EspnCredentialProvider implements CredentialProviderI {
    private static String username = "Dusty1955";
    private static String password = "snickers";

    public String getUserName() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getLoginUrl() {
        return WebConstants.getEspnLoginUrl(username, password);
    }
}
