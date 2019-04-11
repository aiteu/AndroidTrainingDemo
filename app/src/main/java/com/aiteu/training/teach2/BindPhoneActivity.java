package com.aiteu.training.teach2;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;

import com.aiteu.training.R;
import com.aiteu.training.base.BaseActionBarActivity;

public class BindPhoneActivity extends BaseActionBarActivity implements PhoneCallback {

    private TextView mPhoneTxt;
    private FillPhoneNumberFragment mFillPhoneFragment;
    private ValidPhoneNumberFragment mValidPhoneFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_phone);
        findViewById(R.id.bind_btn).setOnClickListener(this);
        mPhoneTxt = findViewById(R.id.phone_num);
        mPhoneTxt.setText("尚未填写手机号");
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        final int id = v.getId();
        if(id == R.id.bind_btn) {
            showFirstStep();
        }
    }

    private void showFirstStep(){
        if(mFillPhoneFragment == null) {
            mFillPhoneFragment = FillPhoneNumberFragment.getInstance();
        }
        mFillPhoneFragment.addCallback(this);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction tr = fm.beginTransaction();
        if(mValidPhoneFragment != null && mValidPhoneFragment.isAdded()) {
            tr.remove(mValidPhoneFragment);
        }
        if(mFillPhoneFragment.isAdded()) {
            tr.show(mFillPhoneFragment);
        }else{
            tr.add(R.id.container, mFillPhoneFragment, "phone");
        }
        tr.addToBackStack(null);
        tr.commit();
    }

    @Override
    public void onFillPhone(String phone) {
        if(mValidPhoneFragment == null) {
            mValidPhoneFragment = ValidPhoneNumberFragment.getInstance();
        }
        Bundle bundle = new Bundle();
        bundle.putString("phone", phone);
        mValidPhoneFragment.setArguments(bundle);
        mValidPhoneFragment.addCallback(this);
        FragmentManager fm = getSupportFragmentManager();
        fm.popBackStack();
        FragmentTransaction tr = fm.beginTransaction();
        if(mValidPhoneFragment.isAdded()) {
            tr.show(mValidPhoneFragment);
        }else{
            tr.add(R.id.container, mValidPhoneFragment, "phone");
        }
        tr.addToBackStack(null);
        tr.commit();
    }

    @Override
    public void onSuccess(String phone) {
        mPhoneTxt.setText(phone);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction tr = fm.beginTransaction();
        fm.popBackStack(); //pop fragment
        if(mValidPhoneFragment.isAdded()) {
            tr.remove(mValidPhoneFragment);
        }
        if(mFillPhoneFragment.isAdded()) {
            tr.remove(mFillPhoneFragment);
        }
        tr.commit();
    }
}
