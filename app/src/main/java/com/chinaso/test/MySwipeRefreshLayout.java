package com.chinaso.test;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by ly on 2016/6/29.
 */
public class MySwipeRefreshLayout extends SwipeRefreshLayout {
    public MySwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MySwipeRefreshLayout(Context context) {
        super(context);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        boolean ret=super.onInterceptTouchEvent(ev);
//        Log.i("ly","pullrefresh onInterceptTouchEvent->"+ret);
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

//        boolean ret=super.onTouchEvent(ev);
//        Log.i("ly","pullrefresh onTouchEvent->"+ret);
        return super.onTouchEvent(ev);
    }
}
