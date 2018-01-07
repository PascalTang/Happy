package com.pascal.pray.android.view;

import android.os.Bundle;

import com.pascal.pray.android.BuildConfig;
import com.pascal.pray.android.R;
import com.pascal.pray.android.base.BaseActivity;
import com.pascal.pray.android.network.AppSchedulerProvider;
import com.pascal.pray.android.network.ForceUpdateDataModel;
import com.pascal.pray.android.network.ForceUpdateModel;
import com.pascal.pray.android.network.RemoteControlService;
import com.pascal.pray.android.network.ServiceFactory;
import io.reactivex.Observable;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        triggerRemoteControlApi(new ForceUpdateModel("0", "dev_mydnavn_dialog_update_app"));
    }

    public Observable<ForceUpdateDataModel> triggerRemoteControlApi(ForceUpdateModel forceUpdateModel) {
        RemoteControlService remoteControlService = ServiceFactory.createServiceFrom(RemoteControlService.class, BuildConfig.BASE_URL_OneDNA);

        return remoteControlService
                .checkUpdateApi(forceUpdateModel.getId(), forceUpdateModel.getKey())
                .subscribeOn(AppSchedulerProvider.io());
    }
}



