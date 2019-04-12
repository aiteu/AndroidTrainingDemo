package com.aiteu.training.teach2;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.aiteu.training.R;
import com.aiteu.training.base.BaseActionBarActivity;
import com.aiteu.training.base.Callback;
import com.aiteu.training.teach2.biz.UserItem;
import com.aiteu.training.teach2.model.Teach2Model;
import com.aiteu.training.teach2.view.CircleLayout;
import com.aiteu.training.utils.Opts;
import com.aiteu.training.utils.ResourceUtil;
import com.bumptech.glide.Glide;

import java.util.List;

public class CustomViewGroupActivity extends BaseActionBarActivity {

    private CircleLayout mCircleLayout;
    private List<UserItem> mDataList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_layout);
        mCircleLayout = findViewById(R.id.circle_layout);
        mDataList = Teach2Model.create().getStarList();
        if(Opts.isEmpty(mDataList)) {
            Teach2Model.create().load(new Callback<List>() {
                @Override
                public void onSuccess(List list) {
                    showCircle();
                }

                @Override
                public void onError() {

                }
            });
        }else{
            showCircle();
        }
    }

    private void showCircle(){
        if(Opts.isEmpty(mDataList)) return;
        int size = ResourceUtil.dip2px(getContext(), 100);
        for(int i=0;i < mDataList.size();i++){
            UserItem item = mDataList.get(i);
            ImageView img = new ImageView(getContext());
            img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            img.setLayoutParams(new ViewGroup.LayoutParams(size, size));
            Glide.with(getContext()).load(item.image.url).into(img);
            mCircleLayout.addView(img);
        }
    }
}
