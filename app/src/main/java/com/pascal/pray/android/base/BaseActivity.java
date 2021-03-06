package com.pascal.pray.android.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.pascal.pray.android.utils.language.LanguageUtil;

/**
 * Created by pascal on 2018/1/2.
 * 每個Activity都會用到的寫在這
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LanguageUtil.getContextWrapper(newBase));
    }
}
