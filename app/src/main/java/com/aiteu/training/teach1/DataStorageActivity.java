package com.aiteu.training.teach1;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.aiteu.training.R;
import com.aiteu.training.base.BaseActionBarActivity;

/**
 * 主要讲解数据存储
 */
public class DataStorageActivity extends BaseActionBarActivity {

    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_storage);
        findViewById(R.id.pref_save_btn).setOnClickListener(this);
        findViewById(R.id.pref_get_btn).setOnClickListener(this);
        mEditText = findViewById(R.id.editText);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        final int id = v.getId();
        if(id == R.id.pref_save_btn) {
            String text = mEditText.getText().toString();
            SharedPreferences pref = getSharedPreferences("record.pref", Context.MODE_PRIVATE);
            pref.edit().putString("record_feel", text).commit();
        }else if(id == R.id.pref_get_btn){
            SharedPreferences pref = getSharedPreferences("record.pref", Context.MODE_PRIVATE);
            String text = pref.getString("record_feel", "");
            mEditText.setText(text);
        }
    }
}
