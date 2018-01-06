package com.pascal.pray.android.utils.common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by jianjiacheng on 24/12/2017.
 */

public class NetUtils {
    /**
     * Checking whether network is available
     * @param context
     * @return true, if network connected, otherwise false.
     */
    public static boolean isNetworkAvailable(Context context) {
        boolean netstate = false;
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {

            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {

                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {

                        netstate = true;
                        break;
                    }
                }
            }
        }
        return netstate;
    }
}
