package com.aiteu.training.teach2;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aiteu.training.utils.ResourceUtil;

public class GridRecyclerFragment extends PureRecyclerFragment {

    @Override
    protected RecyclerView.LayoutManager createLayoutManager() {
        return new GridLayoutManager(getContext(), 2);
    }

    @Override
    protected RecyclerView.ItemDecoration createItemDecoration() {
        int space = ResourceUtil.dip2px(getContext(), 15);
        return new RecycleViewDivider(2, space, true);
    }
}
