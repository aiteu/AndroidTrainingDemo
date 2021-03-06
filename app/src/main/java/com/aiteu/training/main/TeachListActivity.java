package com.aiteu.training.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.aiteu.training.R;
import com.aiteu.training.base.BaseActionBarActivity;
import com.aiteu.training.base.Callback;
import com.aiteu.training.main.biz.TeachItem;
import com.aiteu.training.utils.Opts;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.ArrayList;
import java.util.List;

public abstract class TeachListActivity extends BaseActionBarActivity implements MultiItemTypeAdapter.OnItemClickListener {

    private RecyclerView mRecyclerView;
    private MainAdapter mMainAdapter;
    private List<TeachItem> mTeachItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teach_list);
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mTeachItems = new ArrayList<>(0);
        mMainAdapter = new MainAdapter(getContext(), mTeachItems);
        mRecyclerView.setAdapter(mMainAdapter);
        mMainAdapter.setOnItemClickListener(this);
        loadData();
    }

    protected abstract int getXmlId();

    private void loadData(){
        MainDataSources.create().load(getXmlId(), new Callback<List<TeachItem>>() {
            @Override
            public void onSuccess(List<TeachItem> list) {
                if(!Opts.isEmpty(list)) {
                    mTeachItems.clear();
                    mTeachItems.addAll(list);
                    mMainAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onError() {
                Toast.makeText(getContext(), "数据出错", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
        TeachItem item = mTeachItems.get(position);
        Class clazz = Opts.optClass(item.className);
        if(clazz != null) {
            Intent intent = new Intent(getContext(), clazz);
            intent.putExtra("title", item.title);
            startActivity(intent);
        }
    }

    @Override
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
        return false;
    }
}
