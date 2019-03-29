package com.aiteu.training.demo1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.aiteu.training.R;
import com.aiteu.training.base.BaseActionBarActivity;
import com.aiteu.training.demo1.biz.Person;

public class Demo1Activity extends BaseActionBarActivity {

    private TextView mInfoTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo1);
        findViewById(R.id.button).setOnClickListener(this);
        mInfoTxt = findViewById(R.id.editText);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        final int id = v.getId();
        if(id == R.id.button) {
            startActivityForResult(new Intent(getContext(), Demo1FormActivity.class), 0);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0 && resultCode == RESULT_OK && data != null) {
            Person person = (Person) data.getSerializableExtra("person");
            mInfoTxt.setText(String.format("你的信息如下：\n\n%s", person.toString()));
        }
    }
}
