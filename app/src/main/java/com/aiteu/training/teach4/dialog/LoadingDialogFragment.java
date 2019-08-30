package com.aiteu.training.teach4.dialog;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

public class LoadingDialogFragment extends DialogFragment implements IDialog {

    private String title;
    private String message;
    private String positiveButton;
    private DialogInterface.OnClickListener positiveListener;
    private String negativeButton;
    private DialogInterface.OnClickListener negativeListener;

    @Override
    public void setTitle(String string) {
        this.title = string;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void setPositive(String positive, DialogInterface.OnClickListener positiveListener) {
        this.positiveButton = positive;
        this.positiveListener = positiveListener;
    }

    @Override
    public void setNegative(String negative, DialogInterface.OnClickListener negativeListener) {
        this.negativeButton = negative;
        this.negativeListener = negativeListener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return ProgressDialog.show(getContext(), title, message);
    }
}
