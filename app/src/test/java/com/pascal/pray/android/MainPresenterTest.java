package com.pascal.pray.android;

import android.support.annotation.NonNull;
import android.test.InstrumentationTestCase;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.pascal.pray.android.contract.ParyContract;
import com.pascal.pray.android.global.BaseApplication;
import com.pascal.pray.android.network.ApiEndPoint;
import com.pascal.pray.android.network.JsonData;
import com.pascal.pray.android.network.volley.NetworkUtil;
import com.pascal.pray.android.network.volley.VolleyClientSSLSocketFactory;
import com.pascal.pray.android.presenter.ParyPresenter;
import com.pascal.pray.android.utils.config.AppConfig;
import com.pascal.pray.android.utils.sharedpreference.SaveDataUtil;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import io.fabric.sdk.android.services.network.HttpRequest;
import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;

import static org.mockito.Mockito.verify;

/**
 * Created by Pascal on 2018/1/13.
 */

public class MainPresenterTest {
    @InjectMocks
    private ParyPresenter mMainPresenter;
    @Mock
    private ParyContract.View mMockMainView;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void presentDataFromApiTest() throws Exception {
        mMainPresenter.addSting("pascal");

        verify(mMockMainView).setText("pascala");
    }
}
