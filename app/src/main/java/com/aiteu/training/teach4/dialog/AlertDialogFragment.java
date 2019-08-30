package com.aiteu.training.teach4.dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aiteu.training.R;

public class AlertDialogFragment extends DialogFragment implements IDialog {

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_alert_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((TextView) view.findViewById(R.id.title)).setText(title);
        ((TextView) view.findViewById(R.id.message)).setText(message);
        final TextView positiveBtn = view.findViewById(R.id.positive_btn);
        positiveBtn.setText(positiveButton);
        positiveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if(positiveListener != null) {
                    positiveListener.onClick(getDialog(), 0);
                }
            }
        });
        final TextView negativeBtn = view.findViewById(R.id.negative_btn);
        negativeBtn.setText(negativeButton);
        negativeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if(negativeListener != null) {
                    negativeListener.onClick(getDialog(), 0);
                }
            }
        });
    }
}
