package com.kk.worldcup2018.view.support;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

public class LeftVerticalItemDecoration extends RecyclerView.ItemDecoration {
    private Drawable verticalDrawable1;
    private Drawable verticalDrawable2;
    private List<Integer> colors;

    public LeftVerticalItemDecoration(Drawable verticalDrawable1, Drawable verticalDrawable2, List<Integer> colors) {
        this.verticalDrawable1 = verticalDrawable1;
        this.verticalDrawable2 = verticalDrawable2;
        this.colors = colors;
    }

    @Override
    public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        int drawableLeft = 0;
        int drawableRight = verticalDrawable1.getIntrinsicWidth();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount - 1; i++) {
            View child = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(child);
            int viewType = parent.getAdapter().getItemViewType(position);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int drawableTop = child.getTop() + params.topMargin;
            int drawableBottom = child.getBottom() - params.bottomMargin;
            verticalDrawable1.setBounds(drawableLeft, drawableTop, drawableRight, drawableBottom);
            verticalDrawable2.setBounds(drawableLeft, drawableTop, drawableRight, drawableBottom);
            //Drawable wrappedDrawable = DrawableCompat.wrap(verticalDrawable);
            //setColor(wrappedDrawable, colors, viewType);
            //int color = colors.get(viewType);
            //verticalDrawable.setColorFilter(color, PorterDuff.Mode.MULTIPLY);
            if (viewType == 0) {
                verticalDrawable1.draw(canvas);
            } else {
                verticalDrawable2.draw(canvas);
            }
        }
    }

    private void setColor(Drawable wrappedDrawable, List<Integer> colors, int viewType) {
        if (viewType == 0) {
            DrawableCompat.setTint(wrappedDrawable, colors.get(0));
        } else {
            DrawableCompat.setTint(wrappedDrawable, colors.get(1));
        }
    }

}
