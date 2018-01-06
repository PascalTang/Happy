package com.pascal.pray.android.utils.ui;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by pascal on 2017/11/17.
 */

public class BasicCustomDrawView extends View {
    private static final int DEFAULT_STROKE = 2;

    protected static final int UNDONE_LINE_COLOR = 2660;
    protected static final int BLACK = 2661;
    protected static final int WHITE = 2657;

    protected static final int FOOD_PRIMARY_COLOR = 2663;
    protected static final int FOOD_SEC_PRIMARY_COLOR = 2662;
    protected static final int FOOD_PRIMARY_DARK_COLOR = 2664;
    protected static final int FOOD_DONE_LINE_COLOR = 2666;

    protected static final int CHART_DARK_GREY = 2667;
    protected static final int CHART_GREY = 2668;
    protected static final int CHART_LIGHT_GREY = 2669;

    protected static final int STEPS_PRIMARY_COLOR = 2690;
    protected static final int STEPS_CHART_SEC_PRIMARY_COLOR = 2691;

    protected static final int WEIGHT_PRIMARY_COLOR = 2701;
    protected static final int WEIGHT_PRIMARY_DARK_COLOR = 2702;
    protected static final int WEIGHT_SEC_PRIMARY_DARK_COLOR = 2703;
    protected static final int WEIGHT_DONE_LINE_COLOR = 2704;

    protected Paint mPaint;
    protected int mWidth, mHeight;

    public BasicCustomDrawView(Context context) {
        super(context);
        initPaint();
    }

    public BasicCustomDrawView(Context context ,  AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        this.mWidth = w;
        this.mHeight = h;
        super.onSizeChanged(w, h, oldw, oldh);
    }

    protected void initPaint() {
        mPaint = new Paint();
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setTextAlign(Paint.Align.CENTER);
    }

    protected void setPaint(int color, float textSize ) {
        setPaint(color , textSize , DEFAULT_STROKE);
    }

    protected void setPaint(int color, float textSize , float strokeWidth) {
//        Typeface typeface = TypefaceManager.getInstance(getContext()).getTypeface("arial_unicode_bold.ttf");

        switch (color) {
            case WHITE:
//                mPaint.setColor(getResources().getColor(R.color.white));
                break;
            case BLACK:
//                mPaint.setColor(getResources().getColor(R.color.black));
                break;

            case UNDONE_LINE_COLOR:
//                mPaint.setColor(getResources().getColor(R.color.mainDashboardViewUndoneLine));
                break;

            case CHART_DARK_GREY:
//                mPaint.setColor(getResources().getColor(R.color.chartDarkGrey));
                break;

            case CHART_GREY:
//                mPaint.setColor(getResources().getColor(R.color.chartGrey));
                break;

            case CHART_LIGHT_GREY:
//                mPaint.setColor(getResources().getColor(R.color.chartLightGrey));
                break;

            case FOOD_PRIMARY_COLOR:
//                mPaint.setColor(getResources().getColor(R.color.mainFoodPrimary));
//                typeface = TypefaceManager.getInstance(getContext()).getTypeface("arial_unicode_bold.ttf");;
                break;
            case FOOD_SEC_PRIMARY_COLOR:
//                mPaint.setColor(getResources().getColor(R.color.mainFoodSecPrimary));
                break;
            case FOOD_PRIMARY_DARK_COLOR:
//                mPaint.setColor(getResources().getColor(R.color.mainFoodDashboardViewPrimaryDark));
                break;
            case FOOD_DONE_LINE_COLOR:
//                mPaint.setColor(getResources().getColor(R.color.mainFoodPrimary));
                break;

            case STEPS_CHART_SEC_PRIMARY_COLOR:
//                mPaint.setColor(getResources().getColor(R.color.mainStepsChartYellow));
//                typeface = TypefaceManager.getInstance(getContext()).getTypeface("arial_unicode_bold.ttf");
                break;
            case STEPS_PRIMARY_COLOR:
//                mPaint.setColor(getResources().getColor(R.color.mainStepsChartBlue));
                break;

            case WEIGHT_PRIMARY_COLOR:
//                mPaint.setColor(getResources().getColor(R.color.mainWeightDashboardViewPrimary));
//                typeface = TypefaceManager.getInstance(getContext()).getTypeface("arial_unicode_bold.ttf");
                break;
            case WEIGHT_PRIMARY_DARK_COLOR:
//                mPaint.setColor(getResources().getColor(R.color.mainWeightDashboardViewPrimaryDark));
                break;
            case WEIGHT_SEC_PRIMARY_DARK_COLOR:
//                mPaint.setColor(getResources().getColor(R.color.mainWeightDashboardViewSecPrimaryDark));
                break;
            case WEIGHT_DONE_LINE_COLOR:
//                mPaint.setColor(getResources().getColor(R.color.mainWeightDashboardViewPrimary));
                break;
        }

        mPaint.setStrokeWidth(strokeWidth);
        mPaint.setTextSize((float) textSize * getResources().getDisplayMetrics().scaledDensity);
//        mPaint.setTypeface(typeface);

    }

    protected int getTextWidth(String text, Paint paint) {
        return getRect(text , paint ).width();
    }

    protected int getTextHeight(String text, Paint paint) {
        return getRect(text , paint ).height();
    }

    private Rect getRect(String text, Paint paint){
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);
        return bounds;
    }
}
