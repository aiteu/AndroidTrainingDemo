package com.aiteu.training.teach4;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.aiteu.training.R;
import com.aiteu.training.base.BaseActionBarActivity;
import com.aiteu.training.utils.Opts;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MessageDemoActivity extends BaseActionBarActivity {

    private Button mSendBtn;
    private EditText mMessageEdit;
    private TextView mAnswerTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_message_demo);

        mSendBtn = findViewById(R.id.send_btn);
        mMessageEdit = findViewById(R.id.message_edit);
        mAnswerTxt = findViewById(R.id.answer_txt);
        mSendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
    }

    private void sendMessage(){
        String message = mMessageEdit.getText().toString();
        if(!Opts.isEmpty(message)) {
            mMessageEdit.setText("");
            EventBus.getDefault().post(message);
        }
    }

    /**
     * 接受消息，方法名字可以随意
     * @param message
     */
    @Subscribe
    public void onReceiverMessage(String message) {
        if(Opts.isEquals("A", message) || Opts.isEquals("a", message)) {
            mAnswerTxt.setText("我叫David，你好呀！");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
