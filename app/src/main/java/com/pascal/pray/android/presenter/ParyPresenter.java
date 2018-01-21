package com.pascal.pray.android.presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.pascal.pray.android.BuildConfig;
import com.pascal.pray.android.contract.ParyContract;
import com.pascal.pray.android.global.BaseApplication;
import com.pascal.pray.android.network.ApiEndPoint;
import com.pascal.pray.android.network.AppSchedulerProvider;
import com.pascal.pray.android.network.ForceUpdateModel;
import com.pascal.pray.android.network.JsonData;
import com.pascal.pray.android.network.RemoteControlService;
import com.pascal.pray.android.network.ServiceFactory;
import com.pascal.pray.android.network.volley.NetworkUtil;
import com.pascal.pray.android.utils.config.AppConfig;
import com.pascal.pray.android.utils.sharedpreference.SaveDataUtil;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by Pascal on 2018/1/7.
 */

public class ParyPresenter implements ParyContract.Presenter {
    private ParyContract.View mView ;
    private Context mContext ;
    private Gson mGson = new Gson();
    public ParyPresenter(Context context , final ParyContract.View view){
        mContext = context;
        mView = view;
    }

    @Override
    public void triggerApi() {
//        Observable<JsonData> dataModelObservable = triggerRemoteControlApi(new ForceUpdateModel("0", "dev_mydnavn_dialog_update_app"));
//        dataModelObservable.observeOn(AppSchedulerProvider.ui())
//                .subscribe(new Consumer<JsonData>() {
//                    @Override
//                    public void accept(JsonData data) throws Exception {
//                        SaveDataUtil.putObject(BaseApplication.getAppContext(), "update", JsonData.class , data);
//                        Log.i("ccc","accept "+data.getData().get(0).getData().getMessages().getEn().getBody());
//                        Log.i("ccc","accept "+data.getStatusCode());
//
//                        mView.setText(data.getData().get(0).getData().getMessages().getEn().getBody());
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        Log.i("ccc","err ");
//                    }
//                });

        NetworkUtil.httpGet(mContext, AppConfig.getOneDNA() + ApiEndPoint.REMOTE_CONTROL_COMMANDS_V1,
                null, getParams(), new NetworkUtil.ResponseListener() {
                    @Override
                    public void onResponse(String responseString) {
                        JsonData jsonData = mGson.fromJson(responseString, JsonData.class);
                        SaveDataUtil.putObject(BaseApplication.getAppContext(),
                                JsonData.class.getName() , JsonData.class,jsonData);
                        mView.setText(jsonData.getData().get(0).getCreated_at());
                        Log.i("ccc","accept "+responseString);
                    }

                    @Override
                    public void onError(VolleyError error) {
                        Log.i("ccc","error "+error.toString());
                    }
                });
    }

    @Override
    public void addSting(String s) {
        mView.setText(s+"a");
    }

    public Observable<JsonData> triggerRemoteControlApi(ForceUpdateModel forceUpdateModel) {
        RemoteControlService remoteControlService = ServiceFactory.createServiceFrom(RemoteControlService.class, BuildConfig.BASE_URL_OneDNA);

        return remoteControlService
                .checkUpdateApi(forceUpdateModel.getId(), forceUpdateModel.getKey())
                .subscribeOn(AppSchedulerProvider.io());
    }

    private Map<String,String> getParams() {
        Map<String,String> map= new HashMap<>();
        map.put("id","0");
        map.put("key","dev_mydnavn_dialog_update_app");
        return map;
    }
}
