package com.pascal.pray.android.utils.common;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;

import java.util.HashMap;

/**
 * Created by pascal on 12/9/16.
 */

public class KeyboardUtil implements ViewTreeObserver.OnGlobalLayoutListener{
    private static boolean isShow = false;

    public static void showKeyboard(Context context){
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
        isShow = true;
    }

    public static void hideKeyboard(Activity activity){
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(activity.findViewById(android.R.id.content).getWindowToken(), 0);
        isShow = false;
    }

    /**
     * Touch somewhere to hide
     * setOnTouchListener , and transfer view into
     */
    public static void hideKeyboard(Activity activity , View view){
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        isShow = false;
    }



    @Override
    public void onGlobalLayout()
    {
        Rect r = new Rect();
        //r will be populated with the coordinates of your view that area still visible.
        mRootView.getWindowVisibleDisplayFrame(r);

        int heightDiff = mRootView.getRootView().getHeight() - (r.bottom - r.top);
        float dp = heightDiff/ mScreenDensity;

        if(mCallback != null)
            mCallback.onToggleSoftKeyboard(dp > 200);
    }

    public interface SoftKeyboardToggleListener
    {
        void onToggleSoftKeyboard(boolean isVisible);
    }

    private SoftKeyboardToggleListener mCallback;
    private View mRootView;
    private float mScreenDensity = 1;
    private static HashMap<SoftKeyboardToggleListener, KeyboardUtil> sListenerMap = new HashMap<>();



    public static void addKeyboardToggleListener(Activity act, SoftKeyboardToggleListener listener)
    {
        removeKeyboardToggleListener(listener);

        sListenerMap.put(listener, new KeyboardUtil(act, listener));
    }

    public static void removeKeyboardToggleListener(SoftKeyboardToggleListener listener)
    {
        if(sListenerMap.containsKey(listener))
        {
            KeyboardUtil k = sListenerMap.get(listener);
            k.removeListener();

            sListenerMap.remove(listener);
        }
    }

    public static void removeAllKeyboardToggleListeners()
    {
        for(SoftKeyboardToggleListener l : sListenerMap.keySet())
            sListenerMap.get(l).removeListener();

        sListenerMap.clear();
    }

    private void removeListener()
    {
        mCallback = null;

        mRootView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
    }

    private KeyboardUtil(Activity act, SoftKeyboardToggleListener listener)
    {
        mCallback = listener;

        mRootView = ((ViewGroup) act.findViewById(android.R.id.content)).getChildAt(0);
        mRootView.getViewTreeObserver().addOnGlobalLayoutListener(this);

        mScreenDensity = act.getResources().getDisplayMetrics().density;
    }
}
