package com.aiteu.training.teach2;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aiteu.training.R;
import com.aiteu.training.teach2.biz.UserItem;
import com.aiteu.training.utils.ResourceUtil;
import com.bumptech.glide.Glide;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

public class StaggeredRecyclerFragment extends GridRecyclerFragment {

    @Override
    protected RecyclerView.LayoutManager createLayoutManager() {
        return new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
    }

    protected RecyclerView.Adapter createAdapter(){
        return new CommonAdapter<UserItem>(getContext(), R.layout.item_star_list, getDataList()){
            @Override
            protected void convert(ViewHolder holder, UserItem userItem, int position) {
                ViewGroup.LayoutParams params = holder.getConvertView().getLayoutParams();
                params.height = userItem.image.h;
                holder.getConvertView().setLayoutParams(params);

                ImageView stImg = holder.getView(R.id.star_img);
                TextView stName = holder.getView(R.id.star_name);
                stName.setText(userItem.name);
                Glide.with(getContext()).load(userItem.image.url).into(stImg);
            }
        };
    }

    @Override
    protected RecyclerView.ItemDecoration createItemDecoration() {
        final int space = ResourceUtil.dip2px(getContext(), 15);
        return new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                StaggeredGridLayoutManager.LayoutParams params =
                        (StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams();
                if (params.getSpanIndex() % 2 == 0) {
                    outRect.left = space;
                    outRect.right = space / 2;
                } else {
                    outRect.left = space / 2;
                    outRect.right = space;
                }
                outRect.top = space;
                outRect.bottom = space;
            }
        };
    }
}
