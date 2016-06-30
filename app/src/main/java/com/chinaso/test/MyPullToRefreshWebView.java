package com.chinaso.test;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.handmark.pulltorefresh.library.PullToRefreshWebView;

/**
 * Created by Administrator on 2016/6/30 0030.
 */
public class MyPullToRefreshWebView extends PullToRefreshWebView {
    public MyPullToRefreshWebView(Context context) {
        super(context);
    }

    public MyPullToRefreshWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyPullToRefreshWebView(Context context, Mode mode) {
        super(context, mode);
    }

    public MyPullToRefreshWebView(Context context, Mode mode, AnimationStyle style) {
        super(context, mode, style);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        if (getChildCount() > 0) {
//            View childView = getChildAt(0);
//            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
//        }
//        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),MeasureSpec.getSize(heightMeasureSpec));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
//        if (getChildCount() > 0) {
//            View childView = getChildAt(0);
//            childView.layout(0, 0, childView.getMeasuredWidth(), childView.getMeasuredHeight());
//        }
    }
}
