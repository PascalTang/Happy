package com.pascal.pray.android.utils.ui;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by Pascal on 20/12/2017.
 */

public class CustomFontTextView extends android.support.v7.widget.AppCompatTextView {
    public CustomFontTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypefaceManager.setTypeface(this, context, attrs);
    }
}
