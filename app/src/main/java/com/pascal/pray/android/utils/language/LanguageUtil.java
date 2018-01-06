package com.pascal.pray.android.utils.language;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;

import com.pascal.pray.android.global.LanguageEnum;
import com.pascal.pray.android.global.ResourceService;
import com.pascal.pray.android.utils.sharedpreference.ModelUtils;

import java.util.Locale;

/**
 * Created by pascal on 2018/1/2.
 */

public class LanguageUtil {
    private static ContextWrapper getContextWrapper(Context context, String language, String country) {
        Configuration config = context.getResources().getConfiguration();
//
        if (!language.isEmpty()) {
            Locale locale = new Locale(language, country);
            Locale.setDefault(locale);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                setSystemLocale(config, locale);
            } else {
                setSystemLocaleLegacy(config, locale);
            }

            context = context.createConfigurationContext(config);
        }
        return new ContextWrapper(context);

    }

    public static void setLanguageToSharedpreferenceAndRestart(Context context, String lan) {
        ModelUtils.setLanguage(lan);
        restartAPP(context);
    }

    public static ContextWrapper getContextWrapper(Context context) {

        String country = Locale.getDefault().getCountry();

        String lanShare = ModelUtils.getLanguage();
//        Log.i("ddd", "lanShare " + lanShare);

        String language = Locale.getDefault().getLanguage();
//        Log.i("ddd", "預設 language " + language);
//        Log.i("ddd", "預設 country " + country);

        if (lanShare.equals(LanguageEnum.lan_en.toString())) {
            language = Locale.US.getLanguage();
            country = Locale.US.getCountry();
        } else if (lanShare.equals(LanguageEnum.lan_thai.toString())) {
            //Locale 找不到thai 5.0才開始
            language = "th";
            country = "th";
        }

        return getContextWrapper(context, language, country);
    }


    @SuppressWarnings("deprecation")
    private static void setSystemLocaleLegacy(Configuration config, Locale locale) {
        config.locale = locale;
    }

    @TargetApi(Build.VERSION_CODES.N)
    private static void setSystemLocale(Configuration config, Locale locale) {
        config.setLocale(locale);
    }


    private static void restartAPP(Context context) {
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static LanguageEnum getCurrentLang() {
       return ("th".equalsIgnoreCase(ResourceService.getContext().getResources().getConfiguration().locale.toString().split("_")[0])) ?
               LanguageEnum.lan_thai:
               LanguageEnum.lan_en;
    }
}
