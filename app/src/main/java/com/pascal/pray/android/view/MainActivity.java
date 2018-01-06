package com.pascal.pray.android.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.crashlytics.android.Crashlytics;
import com.pascal.pray.android.R;
import com.pascal.pray.android.base.BaseActivity;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);
    }
}
