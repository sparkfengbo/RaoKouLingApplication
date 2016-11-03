package com.raokouling.sparkfengbo.raokoulingapplication.widget;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by fengbo on 2016/11/2.
 */

/**
 * 解决 {@link SwipeRefreshLayout} 滑动冲突
 * 在 SDK 14 以下，如果第一个子View是 {@link android.widget.AbsListView} 类型可能会引起滑动冲突
 */

public class ScrollChildSwipeRefreshLayout extends SwipeRefreshLayout {

    private View mScrollUpView;

    public ScrollChildSwipeRefreshLayout(Context context) {
        super(context);
    }

    public ScrollChildSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean canChildScrollUp() {
        if(mScrollUpView != null){
            return ViewCompat.canScrollVertically(mScrollUpView, -1);
        }
        return super.canChildScrollUp();
    }


    public void setScrollUpView(View view){
        mScrollUpView = view;
    }
}
