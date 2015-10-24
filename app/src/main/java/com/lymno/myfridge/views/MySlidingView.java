package com.lymno.myfridge.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;

/**
 * Created by Andre on 25.10.2015.
 */
public class MySlidingView extends SlidingUpPanelLayout {


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (ev.getActionMasked()!=MotionEvent.ACTION_MOVE){
            return super.onTouchEvent(ev);
        }
        return false;
    }

    public MySlidingView(Context context) {
        super(context);
    }

    public MySlidingView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MySlidingView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
}
