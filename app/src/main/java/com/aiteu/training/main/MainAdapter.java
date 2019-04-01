package com.aiteu.training.main;

import android.content.Context;

import com.aiteu.training.R;
import com.aiteu.training.main.biz.TeachItem;
import com.aiteu.training.utils.LogUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

public class MainAdapter extends CommonAdapter<TeachItem> {

    public MainAdapter(Context context, List<TeachItem> datas) {
        super(context, R.layout.item_teach_layout, datas);
    }

    @Override
    protected void convert(ViewHolder holder, TeachItem teachItem, int position) {
        holder.setText(R.id.textView, String.format("%d、%s", position+1, teachItem.title));
    }
}
