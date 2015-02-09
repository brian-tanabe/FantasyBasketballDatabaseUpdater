package com.btanabe2.fbdu.du.web;

import com.btanabe2.fbdu.dp.web.SecureWebRequest;
import com.btanabe2.fbdu.dp.web.auth.EspnCredentialProvider;

import java.io.IOException;

/**
 * Created by brian on 2/8/15.
 */
public class EspnWebRequestProvider {
    private static SecureWebRequest instance;

    public static SecureWebRequest getInstance() throws IOException {
        return instance == null ? createSecureWebRequestAndLogIn(new EspnCredentialProvider()) : instance;
    }

    private static SecureWebRequest createSecureWebRequestAndLogIn(EspnCredentialProvider credentials) throws IOException {
        instance = new SecureWebRequest();
        instance.login(credentials);
        return instance;
    }
}
