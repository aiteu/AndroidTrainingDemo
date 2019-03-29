package com.aiteu.training.demo1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
        Toast.makeText(getContext(), "sss", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        final int id = v.getId();
        if(id == R.id.button) {
            startActivityForResult(new Intent(getContext(), Demo1FormActivity.class), 0);


            SharedPreferences pref = getContext().getSharedPreferences("user_info", Context.MODE_PRIVATE);
            pref.edit().putString("user_name", "liwei").putInt("age", 22).commit();

            String userName = pref.getString("user_name", "");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0 && resultCode == RESULT_OK && data != null) {
            Person person = (Person) data.getSerializableExtra("person");
            mInfoTxt.setText(String.format("你的信息如下：\n\n%s", person.toString()));
            mInfoTxt.setTextSize(12);
        }
    }
}
