package com.aiteu.training.teach4.dialog;

import android.content.DialogInterface;

public interface IDialog {

    void setTitle(String string);

    void setMessage(String message);

    void setPositive(String positive, DialogInterface.OnClickListener positiveListener);

    void setNegative(String negative, DialogInterface.OnClickListener negativeListener);
}
