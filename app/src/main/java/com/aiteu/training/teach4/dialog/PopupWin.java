package com.aiteu.training.teach4.dialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.aiteu.training.R;

public class PopupWin extends PopupWindow {

    public PopupWin(Context context) {
        super(context);
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        View view = LayoutInflater.from(context).inflate(R.layout.popup_tips_layout, null);
        setContentView(view);
        setBackgroundDrawable(new ColorDrawable(Color.BLUE));
        setTouchable(true);
        setFocusable(true);
        setOutsideTouchable(true);
    }
}
