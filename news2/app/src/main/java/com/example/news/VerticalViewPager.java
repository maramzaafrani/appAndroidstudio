package com.example.news;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class VerticalViewPager extends ViewPager {
    public VerticalViewPager(@NonNull Context context) {
        super(context);
    }

    public VerticalViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        
        setPageTransformer(true,new VerticalPagerTransformer());
        setOverScrollMode(OVER_SCROLL_NEVER);

    }
    private class VerticalPagerTransformer implements ViewPager.PageTransformer{

        @Override
        public void transformPage(@NonNull View page, float position) {

            if(position<-1){
                page.setAlpha(0);

            }else if (position<=0){
                page.setAlpha(1);
                page.setTranslationX(page.getWidth() * -position);

                float yPosition = position * page.getHeight();
                page.setTranslationY(yPosition);
                page.setScaleX(1);
                page.setScaleY(1);
            }
            else if(position<=1){
                page.setTranslationX(page.getWidth() * -position);
                float scale=0.75f+(1-0.75f)*(1-Math.abs(position));
                page.setScaleX(scale);
                page.setScaleY(scale);
            }else{
                page.setAlpha(0);
            }


        }
    }
    private MotionEvent swapXYCordinates(MotionEvent event){
        float width=getWidth();
                float heigt=getHeight();
                float newX=(event.getY()/heigt)*width;
                float newY=(event.getX()/width)*heigt;
                event.setLocation(newX,newY);
                return event;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercepted = super.onInterceptTouchEvent(swapXYCordinates(ev));
        swapXYCordinates(ev);
        return intercepted;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(swapXYCordinates(ev));
    }
}
