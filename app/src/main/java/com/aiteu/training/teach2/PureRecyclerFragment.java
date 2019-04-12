package com.aiteu.training.teach2;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aiteu.training.R;
import com.aiteu.training.base.Callback;
import com.aiteu.training.teach2.biz.UserItem;
import com.aiteu.training.teach2.model.Teach2Model;
import com.aiteu.training.utils.Opts;
import com.bumptech.glide.Glide;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

public class PureRecyclerFragment extends Fragment {

    private RecyclerView mListView;
    private List mDataList = null;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.ItemDecoration mItemDecoration;
    private int mSpace;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_teach_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSpace = getResources().getDimensionPixelOffset(R.dimen.padding_medium);
        mListView = view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = createLayoutManager();
        mListView.setLayoutManager(layoutManager);
        if(mItemDecoration != null) {
            mListView.removeItemDecoration(mItemDecoration);
        }
        mItemDecoration = createItemDecoration();
        if(mItemDecoration != null) {
            mListView.addItemDecoration(mItemDecoration);
        }
        mDataList = Teach2Model.create().getStarList();
        mAdapter = createAdapter();
        mListView.setAdapter(mAdapter);
        if(Opts.isEmpty(mDataList)) {
            Teach2Model.create().load(new Callback<List>() {
                @Override
                public void onSuccess(List list) {
                    mAdapter.notifyDataSetChanged();
                }

                @Override
                public void onError() {

                }
            });
        }
    }

    public List getDataList() {
        return mDataList;
    }

    protected RecyclerView.LayoutManager createLayoutManager(){
        return new LinearLayoutManager(getContext());
    }

    protected RecyclerView.Adapter createAdapter(){
        return new CommonAdapter<UserItem>(getContext(), R.layout.item_star_list, mDataList){
            @Override
            protected void convert(ViewHolder holder, UserItem userItem, int position) {
                ImageView stImg = holder.getView(R.id.star_img);
                TextView stName = holder.getView(R.id.star_name);
                stName.setText(userItem.name);
                Glide.with(getContext()).load(userItem.image.url).into(stImg);
            }
        };
    }

    protected RecyclerView.ItemDecoration createItemDecoration(){
        return new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                int position = parent.getChildAdapterPosition(view);
                if(position == 0) {
                    outRect.top = mSpace;
                }
                outRect.left = mSpace;
                outRect.bottom = mSpace;
                outRect.right = mSpace;
            }
        };
    }
}
