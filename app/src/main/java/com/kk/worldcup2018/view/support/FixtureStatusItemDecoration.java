package com.kk.worldcup2018.view.support;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.kk.worldcup2018.view.fixtures.FixturesRecyclerViewAdapter;

public class FixtureStatusItemDecoration extends RecyclerView.ItemDecoration {
    private final Drawable timedDrawable;
    private final Drawable inPlayDrawable;
    private final Drawable finishedDrawable;

    public FixtureStatusItemDecoration(Drawable timedDrawable, Drawable inPlayDrawable, Drawable finishedDrawable) {
        this.timedDrawable = timedDrawable;
        this.inPlayDrawable = inPlayDrawable;
        this.finishedDrawable = finishedDrawable;
    }

    @Override
    public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        int drawableLeft = 0;
        int drawableRight = parent.getPaddingLeft();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int drawableTop = child.getTop() + params.topMargin;
            int drawableBottom = child.getBottom() - params.bottomMargin;
            int viewType = determineViewType(parent, child);
            Drawable drawable = selectDrawable(viewType);
            drawable.setBounds(drawableLeft, drawableTop, drawableRight, drawableBottom);
            drawable.draw(canvas);
        }
    }

    private int determineViewType(RecyclerView parent, View child) {
        int position = parent.getChildAdapterPosition(child);
        return parent.getAdapter().getItemViewType(position);
    }

    private Drawable selectDrawable(int viewType) {
        if (viewType == FixturesRecyclerViewAdapter.TIMED_VIEW_TYPE) {
            return timedDrawable;
        } else if (viewType == FixturesRecyclerViewAdapter.IN_PLAY_VIEW_TYPE) {
            return inPlayDrawable;
        } else {
            return finishedDrawable;
        }
    }

}
