package com.aiteu.training.teach4;

import android.Manifest;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.aiteu.training.R;
import com.aiteu.training.base.BaseActionBarActivity;
import com.aiteu.training.utils.LogUtils;
import com.aiteu.training.utils.ResourceUtil;
import com.bumptech.glide.Glide;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PermissionDemoActivity extends BaseActionBarActivity {
    private static final int READ_EXTERNAL_PERMISSION = 1;

    private RecyclerView mListView;
    private List<HashMap<String, String>> mPhotoList;
    private CommonAdapter<HashMap<String, String>> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_demo);
        mListView = findViewById(R.id.list_view);
        mListView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        GridSpacingItemDecoration mItemDecoration = new GridSpacingItemDecoration(3, ResourceUtil.dip2px(getContext(), 5), true);
        mItemDecoration.setExceptFirst(false);
        mListView.addItemDecoration(mItemDecoration);
        mPhotoList = new ArrayList<>(0);
        mAdapter = new CommonAdapter<HashMap<String, String>>(getContext(), R.layout.item_photo_list_layout, mPhotoList) {
            @Override
            protected void convert(ViewHolder holder, HashMap<String, String> stringStringHashMap, int position) {
                ImageView itemImage = holder.getView(R.id.image_view);
                String url = stringStringHashMap.get("path");
                Glide.with(getContext()).load(Uri.fromFile(new File(url))).into(itemImage);
            }
        };
        mListView.setAdapter(mAdapter);

        //请求读取外部存储权限
        if(Build.VERSION.SDK_INT >= 23) {
            //6.0之后才有的权限申请
            int result = PermissionChecker.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE);
            if (result == PermissionChecker.PERMISSION_GRANTED) {
                //已经获得权限直接加载图库
                loadPictures();
            } else {
                //还没有权限，申请权限
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    //被用户拒绝了，需要弹框说明权限的作用

                } else {
                    //第一次直接请求权限
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, READ_EXTERNAL_PERMISSION);
                }
            }
        }else{
            loadPictures();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == READ_EXTERNAL_PERMISSION) {
            if(grantResults.length > 0 && grantResults[0] == PermissionChecker.PERMISSION_GRANTED) {
                loadPictures();
            }else{
                finish();
            }
        }
    }

    private void loadPictures(){
        new LoadPhotoTask(getContext()){
            @Override
            protected void onPostExecute(List list) {
                super.onPostExecute(list);
                mPhotoList.clear();
                if(list != null) {
                    LogUtils.d("photo size : " + list.size());
                    mPhotoList.addAll(list);
                }
                mAdapter.notifyDataSetChanged();
            }
        }.execute();
    }
}
