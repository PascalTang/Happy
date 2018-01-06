package com.pascal.pray.android.global;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.crashlytics.android.Crashlytics;
import com.facebook.stetho.Stetho;
import com.pascal.pray.android.utils.config.AppConfig;
import com.pascal.pray.android.utils.sharedpreference.ModelUtils;
import io.fabric.sdk.android.Fabric;

/**
 * Created by pascal on 2017/12/21.
 */

public class BaseApplication extends MultiDexApplication {

    private static Context sApplicationContext;

    @Override
    public void onCreate() {
        super.onCreate();

        sApplicationContext = getApplicationContext();

        enabledFabric();

        ResourceService.init(getApplicationContext());

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }

    public static Context getAppContext() {
        return sApplicationContext;
    }

    public static void enabledFabric() {
        if (AppConfig.getFabricEnable()) {
            Fabric.with(getAppContext(), new Crashlytics());
            Crashlytics.setUserIdentifier(getCrashlyticsUserID());
        }
    }

    private static String getCrashlyticsUserID() {
        return ModelUtils.getUsername();
    }
}
