package com.btanabe2.fbdu.du.web;

import com.btanabe2.fbdu.dp.web.SecureWebRequest;
import com.btanabe2.fbdu.dp.web.auth.EspnCredentialProvider;

import java.io.IOException;

/**
 * Created by brian on 2/8/15.
 */
public class EspnWebRequestProvider {

    public static SecureWebRequest createSecureWebRequestAndLogIn(EspnCredentialProvider credentials) throws IOException {
        SecureWebRequest webRequest = new SecureWebRequest();
        webRequest.login(credentials);
        return webRequest;
    }
}
