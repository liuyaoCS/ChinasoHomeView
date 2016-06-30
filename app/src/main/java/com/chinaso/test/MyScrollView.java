package com.chinaso.test;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by ly on 2016/6/29.
 */
public class MyScrollView extends ScrollView{
    boolean mLocked=false;
    public void setLocked(boolean locked){
        mLocked = locked;
    }

    public boolean isLocked(){
        return mLocked;
    }
    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context) {
        super(context);
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
