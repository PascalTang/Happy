package com.pascal.pray.android.network;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by jianjiacheng on 03/01/2018.
 */

public interface RemoteControlService {

    @GET(ApiEndPoint.REMOTE_CONTROL_COMMANDS_V1)
    Observable<ForceUpdateDataModel> checkUpdateApi(@Query("id") String id,
                                                    @Query("key") String key);


}
