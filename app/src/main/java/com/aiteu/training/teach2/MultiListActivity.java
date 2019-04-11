package com.aiteu.training.teach2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

import com.aiteu.training.R;
import com.aiteu.training.base.BaseActionBarActivity;
import com.aiteu.training.utils.Opts;

public class MultiListActivity extends BaseActionBarActivity {

    private RadioGroup mStyleGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_list);
        mStyleGroup = findViewById(R.id.style_group);
        mStyleGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.radioButton) {
                    showFragment(PureRecyclerFragment.class);
                }else if(checkedId == R.id.radioButton2) {

                }else if(checkedId == R.id.radioButton3) {

                }else if(checkedId == R.id.radioButton4) {

                }
            }
        });
    }

    private void showFragment(Class clazz){
        Fragment fragment = getFragment(clazz);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction tr = fm.beginTransaction();
        if(fragment.isAdded()) {
            tr.show(fragment);
        }else{
            tr.add(R.id.container, fragment, fragment.getClass().getName());
        }
        tr.addToBackStack(null);
        tr.commit();
    }

    private Fragment getFragment(Class clazz){
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentByTag(clazz.getName());
        if(!Opts.isNull(fragment)){
            return fragment;
        }
        return Fragment.instantiate(getContext(), clazz.getName());
    }
}
