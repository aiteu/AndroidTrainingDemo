package com.aiteu.training.teach1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.aiteu.training.R;
import com.aiteu.training.base.BaseActionBarActivity;

public class Demo1Activity extends BaseActionBarActivity {

    private TextView mInfoTxt;
    private Person mPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo1);
        findViewById(R.id.button).setOnClickListener(this);
        mInfoTxt = findViewById(R.id.editText);
        mPerson = new Person();
        mPerson.name = "David";
        mPerson.gender = "男";
        mPerson.isMarried = true;
        mPerson.likes = new String[]{"羽毛球","唱歌"};
        showPerson();
    }

    private void showPerson(){
        mInfoTxt.setText(String.format("默认信息如下：\n\n%s", mPerson.toString()));
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        final int id = v.getId();
        if(id == R.id.button) {
            Intent intent = new Intent(getContext(), Demo1FormActivity.class);
            intent.putExtra("person", mPerson);
            startActivityForResult(intent, 0);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0 && resultCode == RESULT_OK && data != null) {
            mPerson = (Person) data.getSerializableExtra("person");
            showPerson();
        }
    }
}
