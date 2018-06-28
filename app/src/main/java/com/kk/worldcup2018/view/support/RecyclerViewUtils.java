package com.kk.worldcup2018.view.support;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;

import com.kk.worldcup2018.R;

public class RecyclerViewUtils {

    private RecyclerViewUtils() {
        throw new UnsupportedOperationException("Suppress default constructor for noninstantiability");
    }

    public static void addDivider(@Nullable Context context, @NonNull RecyclerView recyclerView) {
        if (context == null) {
            return;
        }
        Drawable divider = ContextCompat.getDrawable(context, R.drawable.divider);
        recyclerView.addItemDecoration(new DividerItemDecoration(divider));
    }

}
