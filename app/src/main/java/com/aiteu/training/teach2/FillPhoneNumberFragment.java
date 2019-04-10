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
import com.aiteu.training.utils.Opts;

public class FillPhoneNumberFragment extends Fragment {

    private EditText mPhoneEdit;
    private PhoneCallback callback;

    public static FillPhoneNumberFragment getInstance() {
        return new FillPhoneNumberFragment();
    }

    public void addCallback(PhoneCallback callback){
        this.callback = callback;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fill_phone, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPhoneEdit = view.findViewById(R.id.phone_edit);
        view.findViewById(R.id.submit_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = mPhoneEdit.getText().toString().trim();
                if(Opts.isEmpty(phone)) {
                    Toast.makeText(getContext(), "Phone number not empty!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(callback != null) {
                    callback.onFillPhone(phone);
                }
            }
        });
    }
}
