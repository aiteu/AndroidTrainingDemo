package com.aiteu.training.teach2.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.aiteu.training.R;
import com.aiteu.training.utils.ResourceUtil;

public class CircleLayout extends ViewGroup {

    private int mRadius = 100;

    public CircleLayout(Context context) {
        super(context);
    }

    public CircleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CircleLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CircleLayout);
        mRadius = ResourceUtil.dip2px(context, mRadius);
        mRadius = ta.getDimensionPixelOffset(R.styleable.CircleLayout_radius, mRadius);
        ta.recycle();
    }

    public int getRadius() {
        return mRadius;
    }

    public void setRadius(int mRadius) {
        this.mRadius = mRadius;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int count = getChildCount();
        if(count > 0) {
            View childView = getChildAt(0);
            LayoutParams params = childView.getLayoutParams();
            int realWidth = params.width + mRadius * 2 + getPaddingLeft() + getPaddingRight();
            int realHeight = params.height + mRadius * 2 + getPaddingTop() + getPaddingBottom();
            int measuredWidth = MeasureSpec.makeMeasureSpec(realWidth, MeasureSpec.EXACTLY);
            int measuredHeight = MeasureSpec.makeMeasureSpec(realHeight, MeasureSpec.EXACTLY);
            setMeasuredDimension(measuredWidth, measuredHeight);
        }else{
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int width = getWidth();
        int height = getHeight();
        int centerX = width / 2;
        int centerY = height / 2;
        int count = getChildCount();
        final float perAngle = 360.0f / count;
        for(int i=0;i < count; i++){
            View childView = getChildAt(i);
            LayoutParams params = childView.getLayoutParams();
            float angle = perAngle * i;
            int childX = (int)(centerX + mRadius * Math.cos(angle * Math.PI / 180));
            int childY = (int)(centerY + mRadius * Math.sin(angle * Math.PI / 180));
            childView.layout(childX - params.width/2, childY - params.height/2, childX + params.width/2, childY + params.height/2);
        }
    }
}
