package com.btanabe2.fbdu.dp.web.auth;

/**
 * Created by brian on 11/14/14.
 */
public interface CredentialProviderI {
    public String getUserName();
    public String getPassword();
    public String getLoginUrl();
}
