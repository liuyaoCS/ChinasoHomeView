package com.chinaso.test;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.webkit.WebView;

/**
 * Created by ly on 2016/6/29.
 */
public class MyWebView extends WebView {
    boolean mLocked=false;
    public void setLocked(boolean locked){
        mLocked = locked;
    }

    public boolean isLocked(){
        return mLocked;
    }
    public MyWebView(Context context) {
        super(context);
    }

    public MyWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (mLocked) {
            return false;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (!mLocked) {
                    return super.onTouchEvent(ev);
                }
                return false;
            default:
                return super.onTouchEvent(ev);
        }
    }
}
