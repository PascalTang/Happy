package com.pascal.pray.android;

import com.google.gson.Gson;
import com.pascal.pray.android.network.ApiEndPoint;
import com.pascal.pray.android.network.JsonData;
import com.pascal.pray.android.utils.config.AppConfig;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;

import java.io.IOException;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

/**
 * Created by Pascal on 2018/1/14.
 */

public class NetTest {
    @Mock
    private JsonData mJsonData;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void newWork() throws IOException , InterruptedException{
        MockWebServer server = new MockWebServer();
        server.enqueue(new MockResponse());
        server.start();

        Mockito.when(mJsonData.getStatusCode()).thenReturn(200);

        HttpUrl httpUrl = server.url(AppConfig.getOneDNA() + ApiEndPoint.REMOTE_CONTROL_COMMANDS_V1 + "?id=0&key=dev_mydnavn_dialog_update_app");
        String bodyOfRequest = sendGetRequest(new OkHttpClient() , httpUrl);

        JsonData jsonData = new Gson().fromJson(bodyOfRequest, JsonData.class);
        Assert.assertEquals(jsonData.getStatusCode() , mJsonData.getStatusCode());

    }

    private String sendGetRequest(OkHttpClient okHttpClient , HttpUrl url) throws  IOException{
        okhttp3.Request request= new Request.Builder()
                .get()
                .url(url)
                .build();
        Response response = okHttpClient.newCall(request).execute();
        return response.body().string();
    }

    private String sendPostRequest(OkHttpClient okHttpClient , HttpUrl url) throws  IOException{
        RequestBody formBody = new FormBody.Builder()
                .add("id", "0")
                .add("key", "dev_mydnavn_dialog_update_app")
                .build();

        okhttp3.Request request= new Request.Builder()
                .post(formBody)
                .url(url)
                .build();

        Response response = okHttpClient.newCall(request).execute();
        return response.body().string();
    }
}
