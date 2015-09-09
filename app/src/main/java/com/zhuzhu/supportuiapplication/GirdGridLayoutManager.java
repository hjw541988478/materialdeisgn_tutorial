package com.zhuzhu.supportuiapplication;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Administrator on 2015/8/4.
 */
public class GirdGridLayoutManager extends GridLayoutManager {

    public GirdGridLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }


    /**
     * 高度随内容自适应
     *
     * @param recycler
     * @param state
     * @param widthSpec
     * @param heightSpec
     */


    @Override
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
        for (int i = 0; i < state.getItemCount(); i++) {
            View view = recycler.getViewForPosition(i);
            if (view != null) {
                measureChild(view, widthSpec, heightSpec);
                int measuredWidth = View.MeasureSpec.getSize(widthSpec);
                int measuredHeight = view.getMeasuredHeight();
                setMeasuredDimension(measuredWidth, measuredHeight * 3);
            }
        }
        if (state.getItemCount() == 0)
            setMeasuredDimension(View.MeasureSpec.getSize(widthSpec), View.MeasureSpec.getSize(heightSpec));
    }

}
