package com.pascal.pray.android.utils.sharedpreference;

import com.google.gson.Gson;
import com.pascal.pray.android.global.BaseApplication;
import com.pascal.pray.android.global.LanguageEnum;
import com.pascal.pray.android.model.ExampleModel;

/**
 * Created by pascal on 2017/12/31.
 * 將model轉成json 存進shared
 */

public class ModelUtils {
    private static Gson mGson = new Gson();

    public static void setExampleModel(ExampleModel model) {
        SaveDataUtil.putString(BaseApplication.getAppContext(), ExampleModel.class.toString(), mGson.toJson(model));
    }

    public static ExampleModel getExampleModel() {
        String jsonModel = SaveDataUtil.getString(BaseApplication.getAppContext(), ExampleModel.class.toString(), null);
        return mGson.fromJson(jsonModel, ExampleModel.class);
    }

    public static void setUsername( String username) {
        SaveDataUtil.putString(BaseApplication.getAppContext(), "username", username);
    }

    public static String getUsername() {
        return SaveDataUtil.getString(BaseApplication.getAppContext(), "username", "");
    }

    // Language
    public static void setLanguage(String language) {
        SaveDataUtil.putString(BaseApplication.getAppContext(), "language", language);
    }

    public static String getLanguage() {
        return SaveDataUtil.getString(BaseApplication.getAppContext(), "language", LanguageEnum.lan_default.toString());
    }
}
