package com.aiteu.training.demo1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.aiteu.training.R;
import com.aiteu.training.base.BaseActionBarActivity;
import com.aiteu.training.demo1.biz.Person;
import com.aiteu.training.utils.Opts;

import java.util.ArrayList;
import java.util.List;

public class Demo1FormActivity extends BaseActionBarActivity {

    private EditText mNameEdit;
    private RadioGroup mGenderGroup;
    private Person mPerson;
    private List<String> mLikeList;
    private SwitchCompat mMarrySwitcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPerson = (Person) getIntent().getSerializableExtra("person");
        if(mPerson == null) {
            mPerson = new Person();
        }
        setContentView(R.layout.activity_demo1_form);
        findViewById(R.id.button2).setOnClickListener(this);
        mNameEdit = findViewById(R.id.et_name);
        mGenderGroup = findViewById(R.id.gender_group);
        ((CheckBox)findViewById(R.id.checkbox1)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    mLikeList.add(buttonView.getText().toString());
                }else{
                    mLikeList.remove(buttonView.getText().toString());
                }
            }
        });
        ((CheckBox)findViewById(R.id.checkbox2)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    mLikeList.add(buttonView.getText().toString());
                }else{
                    mLikeList.remove(buttonView.getText().toString());
                }
            }
        });
        ((CheckBox)findViewById(R.id.checkbox3)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    mLikeList.add(buttonView.getText().toString());
                }else{
                    mLikeList.remove(buttonView.getText().toString());
                }
            }
        });
        ((CheckBox)findViewById(R.id.checkbox4)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    mLikeList.add(buttonView.getText().toString());
                }else{
                    mLikeList.remove(buttonView.getText().toString());
                }
            }
        });
        mMarrySwitcher = (SwitchCompat)findViewById(R.id.marry_switcher);
        showPerson();
    }

    private void showPerson(){
        if(!Opts.isEmpty(mPerson.name)) {
            mNameEdit.setText(mPerson.name);
        }
        if("女".equals(mPerson.gender)) {
            mGenderGroup.check(R.id.gender_female);
        }else{
            mGenderGroup.check(R.id.gender_male);
        }
        mLikeList = new ArrayList<>(0);
        if(!Opts.isEmpty(mPerson.likes)) {
            for(int i=0;i < mPerson.likes.length;i++) {
                String value = mPerson.likes[i];
                if(value.equals("羽毛球")) {
                    ((CheckBox)findViewById(R.id.checkbox1)).setChecked(true);
                }else if(value.equals("游泳")) {
                    ((CheckBox)findViewById(R.id.checkbox2)).setChecked(true);
                }else if(value.equals("跳舞")) {
                    ((CheckBox)findViewById(R.id.checkbox3)).setChecked(true);
                }else if(value.equals("唱歌")) {
                    ((CheckBox)findViewById(R.id.checkbox4)).setChecked(true);
                }
            }
        }
        mMarrySwitcher.setChecked(mPerson.isMarried);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        final int id = v.getId();
        if(id == R.id.button2) {
            mPerson.name = mNameEdit.getText().toString().trim();
            RadioButton genderRadio = mGenderGroup.findViewById(mGenderGroup.getCheckedRadioButtonId());
            mPerson.gender = genderRadio.getText().toString();
            mPerson.likes = mLikeList.toArray(new String[0]);
            mPerson.isMarried = mMarrySwitcher.isChecked();

            Intent intent = getIntent();
            intent.putExtra("person", mPerson);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
