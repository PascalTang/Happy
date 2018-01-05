package com.pascal.pray.android;

import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;
import android.widget.ProgressBar;

public class ActivityMainActivity extends Activity  {

    private TextView tv;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.tv);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
    }

}
