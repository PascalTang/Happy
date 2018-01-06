package com.pascal.pray.android.utils.config;

import com.pascal.pray.android.BuildConfig;

public class AppConfig {

    public static String getProductCode() {
        return "mtl-bettercare";
    }

    public static boolean getFabricEnable(){
        return true;
    }

    public static String getIdentity() {
        return BuildConfig.BASE_URL_IDENTITY;
    }

    public static String getOneDNA() {
        return BuildConfig.BASE_URL_OneDNA;
    }

}
