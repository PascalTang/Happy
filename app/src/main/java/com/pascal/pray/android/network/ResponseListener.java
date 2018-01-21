package com.pascal.pray.android.network;


public interface ResponseListener {
    void onResponse(String resp);
    void onError(String err);
}

