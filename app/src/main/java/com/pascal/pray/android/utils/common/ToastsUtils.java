package com.pascal.pray.android.utils.common;

import android.content.Context;
import android.widget.Toast;
import com.pascal.pray.android.BuildConfig;

public class ToastsUtils {
    public static Context sContext;

    public static void init(Context context) {
        sContext = context.getApplicationContext();
    }

    private static void check() {
        if (sContext == null) {
            throw new NullPointerException(
                    "Must initial call ToastUtils.register(Context context) in your " +
                            "<? " +
                            "extends Application class>");
        }
    }

    public static void showShort(int resId) {
        check();
        if (!BuildConfig.BUILD_TYPE.equalsIgnoreCase("debug")) return;
        Toast.makeText(sContext, resId, Toast.LENGTH_SHORT).show();
    }

    public static void showShort(String message) {
        check();
        if (!BuildConfig.BUILD_TYPE.equalsIgnoreCase("debug")) return;
        Toast.makeText(sContext, message, Toast.LENGTH_SHORT).show();
    }

    public static void showLong(int resId) {
        check();
        if (!BuildConfig.BUILD_TYPE.equalsIgnoreCase("debug")) return;
        Toast.makeText(sContext, resId, Toast.LENGTH_LONG).show();
    }

    public static void showLong(String message) {
        check();
        if (!BuildConfig.BUILD_TYPE.equalsIgnoreCase("debug")) return;
        Toast.makeText(sContext, message, Toast.LENGTH_LONG).show();
    }
}
