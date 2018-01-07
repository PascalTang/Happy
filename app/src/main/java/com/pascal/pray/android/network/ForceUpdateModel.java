package com.pascal.pray.android.network;

/**
 * Created by jianjiacheng on 03/01/2018.
 */
//傳送的資料
public class ForceUpdateModel {
    public String id;
    public String key;

    public ForceUpdateModel(String id, String key) {
        this.id = id;
        this.key = key;
    }

    public String getId() {
        return id;
    }

    public String getKey() {
        return key;
    }
}
