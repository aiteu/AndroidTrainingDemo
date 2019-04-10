package com.aiteu.training.main;

import android.os.Bundle;

import com.aiteu.training.R;

public class MainActivity extends TeachListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getSupportActionBar() != null) {
            //first activity disable back button
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
    }

    @Override
    protected int getXmlId() {
        return R.xml.teach_list;
    }
}
