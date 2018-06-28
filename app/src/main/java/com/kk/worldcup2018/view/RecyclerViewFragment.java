package com.kk.worldcup2018.view;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;

import com.kk.worldcup2018.data.WorldCupFetcher;
import com.kk.worldcup2018.view.support.RecyclerViewUtils;

import javax.inject.Inject;

public abstract class RecyclerViewFragment extends Fragment {

    @Inject
    protected
    WorldCupFetcher worldCupFetcher;

    protected RecyclerView recyclerView;

    protected abstract void injectDependencies();

    protected void addDecorationsToRecyclerView() {
        RecyclerViewUtils.addDivider(getContext(), recyclerView);
    }

}
