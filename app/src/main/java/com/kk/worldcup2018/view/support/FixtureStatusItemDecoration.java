package com.kk.worldcup2018.view.support;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.kk.worldcup2018.view.fixtures.FixturesRecyclerViewAdapter;

public class FixtureStatusItemDecoration extends RecyclerView.ItemDecoration {
    private Drawable timedDrawable;
    private Drawable inPlayDrawable;
    private Drawable finishedDrawable;

    public FixtureStatusItemDecoration(Drawable timedDrawable, Drawable inPlayDrawable, Drawable finishedDrawable) {
        this.timedDrawable = timedDrawable;
        this.inPlayDrawable = inPlayDrawable;
        this.finishedDrawable = finishedDrawable;
    }

    @Override
    public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        int drawableLeft = 0;
        int drawableRight = timedDrawable.getIntrinsicWidth();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount - 1; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int drawableTop = child.getTop() + params.topMargin;
            int drawableBottom = child.getBottom() - params.bottomMargin;
            int position = parent.getChildAdapterPosition(child);
            int viewType = parent.getAdapter().getItemViewType(position);
            timedDrawable.setBounds(drawableLeft, drawableTop, drawableRight, drawableBottom);
            inPlayDrawable.setBounds(drawableLeft, drawableTop, drawableRight, drawableBottom);
            finishedDrawable.setBounds(drawableLeft, drawableTop, drawableRight, drawableBottom);
            if (viewType == FixturesRecyclerViewAdapter.TIMED_VIEW_TYPE) {
                timedDrawable.draw(canvas);
            } else if (viewType == FixturesRecyclerViewAdapter.IN_PLAY_VIEW_TYPE) {
                inPlayDrawable.draw(canvas);
            } else if (viewType == FixturesRecyclerViewAdapter.FINISHED_VIEW_TYPE) {
                finishedDrawable.draw(canvas);
            }
        }
    }

}
