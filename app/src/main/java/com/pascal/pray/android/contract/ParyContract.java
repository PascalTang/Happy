package com.pascal.pray.android.contract;

import com.pascal.pray.android.network.JsonData;

/**
 * Created by Pascal on 2018/1/7.
 */

public interface ParyContract {
    interface Model {
    }

    interface View {
        void setText(String s);
    }

    interface Presenter {
        void triggerApi();
        void addSting(String s);
    }
}
