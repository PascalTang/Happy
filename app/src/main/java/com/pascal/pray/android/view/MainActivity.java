package com.pascal.pray.android.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.pascal.pray.android.BuildConfig;
import com.pascal.pray.android.R;
import com.pascal.pray.android.base.BaseActivity;
import com.pascal.pray.android.contract.ParyContract;
import com.pascal.pray.android.presenter.ParyPresenter;


public class MainActivity extends BaseActivity implements ParyContract.View , View.OnClickListener{
    private ParyContract.Presenter mPresenter ;
    private TextView mDataTV;
    private Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter = new ParyPresenter(this , this);

        mDataTV = (TextView)findViewById(R.id.tv_data);
        mButton = (Button)findViewById(R.id.btn_add);
        mButton.setOnClickListener(this);

    }


    @Override
    public void setText(String s) {
        mDataTV.setText(s);
    }

    @Override
    public void onClick(View view) {
        mPresenter.addSting(mDataTV.getText().toString());
    }
}



