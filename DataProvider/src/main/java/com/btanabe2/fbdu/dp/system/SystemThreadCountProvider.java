package com.btanabe2.fbdu.dp.system;

/**
 * Created by brian on 2/9/15.
 */
public class SystemThreadCountProvider {

    public static int getNumberOfAvailableProcessors() {
        return Runtime.getRuntime().availableProcessors();
    }

}
