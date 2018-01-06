package com.pascal.pray.android.utils.network;


public interface ResponseListener {
    void onResponse(String resp);
    void onError(String err);
}

