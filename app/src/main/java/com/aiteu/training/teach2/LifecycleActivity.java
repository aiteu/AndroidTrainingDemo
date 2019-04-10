package com.aiteu.training.teach2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.aiteu.training.R;
import com.aiteu.training.base.BaseActionBarActivity;
import com.aiteu.training.utils.LogUtils;

/**
 * Android lifecycle and fragment lifecycle
 */
public class LifecycleActivity extends BaseActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_container);
        LogUtils.d("----------onCreate----------");
        Fragment fragment = LifecycleFragment.getIsntance();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction tr = fm.beginTransaction();
        tr.add(R.id.container, fragment, "lifecycle");
//        tr.addToBackStack("back"); //can back
        tr.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtils.d("----------onStart----------");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.d("----------onResume----------");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtils.d("----------onPause----------");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtils.d("----------onStop----------");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.d("----------onDestroy----------");
    }
}
