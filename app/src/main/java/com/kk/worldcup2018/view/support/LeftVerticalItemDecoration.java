package com.kk.worldcup2018.view.support;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class LeftVerticalItemDecoration extends RecyclerView.ItemDecoration {
    private Drawable verticalDrawable;

    LeftVerticalItemDecoration(Drawable verticalDrawable) {
        this.verticalDrawable = verticalDrawable;
    }

    @Override
    public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        int drawableLeft = 0;
        int drawableRight = verticalDrawable.getIntrinsicWidth();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount - 1; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int drawableTop = child.getTop() + params.topMargin;
            int drawableBottom = child.getBottom() - params.bottomMargin;
            verticalDrawable.setBounds(drawableLeft, drawableTop, drawableRight, drawableBottom);
            verticalDrawable.draw(canvas);
        }
    }
}
