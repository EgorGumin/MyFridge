package com.lymno.myfridge.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.lymno.myfridge.R;

/**
 * Created by Andre on 24.10.2015.
 */
public class BucketView extends View {


    private float mProgress;


    public BucketView(Context context) {
        super(context);
    }

    public BucketView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BucketView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setProgress(float progress) {
        this.mProgress = progress;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        final int w = canvas.getWidth(),
                h = canvas.getHeight();
        Rect rect = new Rect(0, 0, w, h);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(0xFFFFFFFF);
        canvas.drawRect(rect, paint);

        paint.setColor(getContext().getResources().getColor(R.color.colorPrimary));
        rect.top = (int) (rect.bottom * (1 - mProgress));
        canvas.drawRect(rect, paint);
    }
}
