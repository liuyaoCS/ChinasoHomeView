package com.chinaso.test;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

/**
 * Created by Administrator on 2016/6/30 0030.
 */
public class MyPullToRefreshScrollView extends PullToRefreshScrollView {
    private static final String TAG ="MyPTRScrollView" ;

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
    private boolean mIsDisable;
    public void setDisable(boolean flag){
        mIsDisable =flag;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.i(TAG, "onTouchEvent action:ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i(TAG, "onTouchEvent action:ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.i(TAG, "onTouchEvent action:ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.i(TAG, "onTouchEvent action:ACTION_CANCEL");
                break;
        }
        if(!mIsDisable){
            return super.onTouchEvent(event);
        }else{
            return false;
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.i(TAG, "onInterceptTouchEvent action:ACTION_DOWN");
                // return true;
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i(TAG, "onInterceptTouchEvent action:ACTION_MOVE");
                //return true;
                break;
            case MotionEvent.ACTION_UP:
                Log.i(TAG, "onInterceptTouchEvent action:ACTION_UP");
                //return true;
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.i(TAG, "onInterceptTouchEvent action:ACTION_CANCEL");
                break;
        }

        if(!mIsDisable){
            return super.onInterceptTouchEvent(event);
        }else{
            return false;
        }
    }
}
