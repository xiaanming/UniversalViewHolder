package com.example.extra;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class FullWidthFixedViewLayout extends FrameLayout {
	int widthPixels = 0;
	
    public FullWidthFixedViewLayout(Context context) {
        this(context, null);
    }
    
    
    
    public FullWidthFixedViewLayout(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		
		widthPixels = getResources().getDisplayMetrics().widthPixels;
	}



	public FullWidthFixedViewLayout(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}



	@Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		ViewGroup viewParent = (ViewGroup) getParent();
		
		System.out.println("viewParent = " + viewParent);
		
        int targetWidth = widthPixels;
        widthMeasureSpec = MeasureSpec.makeMeasureSpec(targetWidth,
                MeasureSpec.getMode(widthMeasureSpec));
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
