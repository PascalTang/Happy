package com.pascal.pray.android.utils.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Pascal on 20/12/2017.
 */


public class TypefaceManager {
    public static void setTypeface(View view , Context context, AttributeSet attrs){
        if (view == null || context == null || attrs ==null) return;
        try {
            String packageName = "http://schemas.android.com/apk/res-auto";
            String font = attrs.getAttributeValue(packageName, "custom_font");
            if(!font.contains(".")) {
                font += ".ttf";
            }

            if (view instanceof TextView || view instanceof EditText ||view instanceof Button) {
                ((TextView) view).setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/"+font));
            }

        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
