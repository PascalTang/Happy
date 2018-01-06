package com.pascal.pray.android.utils.ui;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by Pascal on 20/12/2017.
 */

public class CustomFontEditText extends android.support.v7.widget.AppCompatEditText {
    public CustomFontEditText(Context context,@Nullable AttributeSet attrs) {
        super(context, attrs);
        TypefaceManager.setTypeface(this, context, attrs);
    }
}
