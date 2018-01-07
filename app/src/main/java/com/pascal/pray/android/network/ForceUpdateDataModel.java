package com.pascal.pray.android.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

//接收到的資料
public class ForceUpdateDataModel {
    @SerializedName("statusCode")
    @Expose
    public Integer statusCode;

    @SerializedName("statusMessage")
    @Expose
    public String statusMessage;

    @SerializedName("data")
    @Expose
    public List<Datum> data = null;
}
