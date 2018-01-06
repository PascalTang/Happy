package com.pascal.pray.android.global;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings;
import android.support.annotation.StringRes;

public class ResourceService {
    private static Context sContext;
    private Activity mActivity;

    public static Context getContext() {
        return sContext;
    }

    public static void init(Context context) {
        sContext = context.getApplicationContext();
    }

    public static String getString(@StringRes int resId) {
        return (null == sContext) ? null : sContext.getString(resId);
    }

    public static String getUid() {
        return Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static ContentResolver getContentResolver() {
        return sContext.getContentResolver();
    }

    public Activity getActivity() {
        return mActivity;
    }

    public ResourceService setActivity(Activity activity) {
        mActivity = activity;
        return this;
    }
}
