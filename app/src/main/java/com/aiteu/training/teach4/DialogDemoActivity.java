package com.aiteu.training.teach4;

import android.os.Bundle;
import android.support.v4.widget.PopupWindowCompat;
import android.view.Gravity;
import android.view.View;

import com.aiteu.training.R;
import com.aiteu.training.base.BaseActionBarActivity;
import com.aiteu.training.teach4.dialog.AlertDialogFragment;
import com.aiteu.training.teach4.dialog.LoadingDialogFragment;
import com.aiteu.training.teach4.dialog.PopupWin;

public class DialogDemoActivity extends BaseActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_demo);
        findViewById(R.id.alert_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });

        findViewById(R.id.loading_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoadingDialog();
            }
        });

        findViewById(R.id.pop_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopDialog();
            }
        });
    }

    private void showAlertDialog(){
        AlertDialogFragment dialog = new AlertDialogFragment();
        dialog.setTitle("提示");
        dialog.setMessage("该弹出框是用自定义视图做的");
        dialog.setPositive("知道了", null);
        dialog.setNegative("取消", null);
        dialog.show(getSupportFragmentManager(), "alert");
    }

    private void showLoadingDialog(){
        LoadingDialogFragment dialog = new LoadingDialogFragment();
        dialog.setMessage("使用系统默认样式");
        dialog.show(getSupportFragmentManager(), "loading");
    }

    private void showPopDialog(){
        View anchorView = findViewById(R.id.pop_btn);
        PopupWin popupWin = new PopupWin(getContext());
        PopupWindowCompat.showAsDropDown(popupWin, anchorView, 0, 0, Gravity.RIGHT);
    }
}
