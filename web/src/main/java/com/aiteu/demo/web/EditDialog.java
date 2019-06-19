package com.aiteu.demo.web;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.widget.EditText;

public class EditDialog extends DialogFragment {

    private OnEditClickListener mListener;
    private EditText mEditTxt;
    private String mTitle;

    public void show(FragmentManager fm, String title, OnEditClickListener listener) {
        mListener = listener;
        this.mTitle = title;
        show(fm, "EditDialog");
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        final EditText et = new EditText(getContext());
        builder.setView(et);
        builder.setTitle(mTitle);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(mListener != null) {
                    mListener.onEdit(et.getText().toString().trim());
                }
            }
        });
        return builder.create();
    }

    public interface OnEditClickListener {
        void onEdit(String text);
    }
}
