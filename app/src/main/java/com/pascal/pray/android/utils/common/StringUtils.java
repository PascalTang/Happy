package com.pascal.pray.android.utils.common;

import android.text.TextUtils;
import android.util.Base64;

import java.io.UnsupportedEncodingException;

/**
 * Created by jianjiacheng on 22/12/2017.
 */

public class StringUtils {
    /**
     * Don't let anyone instantiate this class.
     */
    private StringUtils() {
        throw new Error("Do not need instantiate!");
    }

    /**
     * Returns true if the string is null or 0-length.
     *
     * @param str the string to be examined
     * @return true if str is null or zero length
     */
    public static boolean isEmpty(CharSequence str) {
        return TextUtils.isEmpty(str);
    }

    /**
     *
     * @param str
     * @param defValue
     * @return
     */
    public static int toInt(String str, int defValue) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
        }
        return defValue;
    }

    public static String base64Encode(String text) { //loggin
        if (text.isEmpty()) {
            return "";
        }
        byte[] data = new byte[0];
        try {
            data = text.getBytes("UTF-8");
            String base64 = Base64.encodeToString(data, Base64.DEFAULT).trim();
            return base64;
        } catch (UnsupportedEncodingException e) {
        }
        return "";
    }


}
