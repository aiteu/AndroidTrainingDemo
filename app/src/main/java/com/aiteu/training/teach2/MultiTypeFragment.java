package com.aiteu.training.teach2;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aiteu.training.R;
import com.aiteu.training.teach2.biz.UserItem;
import com.aiteu.training.utils.Opts;
import com.aiteu.training.utils.ResourceUtil;
import com.bumptech.glide.Glide;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

public class MultiTypeFragment extends GridRecyclerFragment {

    @Override
    protected RecyclerView.Adapter createAdapter() {
        return new MyMultiItemTypeAdapter(getContext(), getDataList());
    }

    @Override
    protected RecyclerView.ItemDecoration createItemDecoration() {
        return new MyItemDecoration();
    }

    class MyMultiItemTypeAdapter extends MultiItemTypeAdapter<UserItem> {
        final int TYPE_NORMAL = 0;
        final int TYPE_GRID = 1;

        public MyMultiItemTypeAdapter(Context context, List<UserItem> datas) {
            super(context, datas);
            addItemViewDelegate(TYPE_NORMAL, new NormalTypeDelegate());
            addItemViewDelegate(TYPE_GRID, new GridTypeDelegate());
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
            RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
            if(manager instanceof GridLayoutManager) {
                final GridLayoutManager gridManager = ((GridLayoutManager) manager);
                gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        return (getItemViewType(position) == TYPE_NORMAL ? gridManager.getSpanCount() : 1);
                    }
                });
            }
        }
    }

    class NormalTypeDelegate implements ItemViewDelegate<UserItem> {
        @Override
        public int getItemViewLayoutId() {
            return R.layout.item_star_list;
        }

        @Override
        public boolean isForViewType(UserItem item, int position) {
            return Opts.isEquals("normal", item.type);
        }

        @Override
        public void convert(ViewHolder holder, UserItem userItem, int position) {
            ImageView stImg = holder.getView(R.id.star_img);
            TextView stName = holder.getView(R.id.star_name);
            stName.setText(userItem.name);
            Glide.with(getContext()).load(userItem.image.url).into(stImg);
        }
    }

    class GridTypeDelegate extends NormalTypeDelegate {
        @Override
        public boolean isForViewType(UserItem item, int position) {
            return !Opts.isEquals("normal", item.type);
        }
    }

    class MyItemDecoration extends RecyclerView.ItemDecoration {
        final int space = ResourceUtil.dip2px(getContext(), 15);
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.set(space/2, space/2, space/2, space/2);
        }
    }
}
