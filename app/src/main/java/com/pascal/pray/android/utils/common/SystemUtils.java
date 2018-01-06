package com.pascal.pray.android.utils.common;

import android.content.Context;
import android.provider.Settings;

/**
 * Created by pascal on 2017/12/31.
 */

public class SystemUtils {
    public static String getAndroidId(final Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }
}
