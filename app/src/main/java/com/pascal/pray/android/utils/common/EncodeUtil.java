package com.pascal.pray.android.utils.common;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by pascal on 2017/7/10.
 */

public class EncodeUtil {

    public static String urlEncode(String value){
        try {
            return  URLEncoder.encode(value, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String base64Encode(String text){
        if (text.isEmpty()) return "";
        byte[] data = new byte[0];
        try {
            data = text.getBytes("UTF-8");
            String base64 = Base64.encodeToString(data, Base64.DEFAULT).trim();
            return base64;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String base64Decode(String base64){
        byte[] data = Base64.decode(base64, Base64.DEFAULT);
        try {
            return new String(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
