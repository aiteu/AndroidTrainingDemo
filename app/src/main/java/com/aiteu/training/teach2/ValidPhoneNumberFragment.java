package com.aiteu.training.teach2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.aiteu.training.R;

public class ValidPhoneNumberFragment extends Fragment {

    private EditText mEditText;
    private PhoneCallback callback;

    public static ValidPhoneNumberFragment getInstance(){
        return new ValidPhoneNumberFragment();
    }

    public void addCallback(PhoneCallback callback){
        this.callback = callback;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_valid_phone, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mEditText = view.findViewById(R.id.code_edit);
        view.findViewById(R.id.submit_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = mEditText.getText().toString().trim();
                if(!"123321".equals(code)) {
                    Toast.makeText(getContext(), "code error", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(callback != null) {
                    callback.onSuccess(getArguments().getString("phone"));
                }
            }
        });
    }
}
