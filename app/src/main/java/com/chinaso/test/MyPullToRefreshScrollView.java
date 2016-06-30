package com.chinaso.test;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

/**
 * Created by Administrator on 2016/6/30 0030.
 */
public class MyPullToRefreshScrollView extends PullToRefreshScrollView {
    public MyPullToRefreshScrollView(Context context) {
        super(context);
    }

    public MyPullToRefreshScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyPullToRefreshScrollView(Context context, Mode mode) {
        super(context, mode);
    }

    public MyPullToRefreshScrollView(Context context, Mode mode, AnimationStyle style) {
        super(context, mode, style);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {

//        if(!TestApp.mIsScrollAll) {
//            return false;
//        }
        return super.onInterceptTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        if(!TestApp.mIsScrollAll) {
//            return false;
//        }
        return super.onTouchEvent(event);
    }
}
