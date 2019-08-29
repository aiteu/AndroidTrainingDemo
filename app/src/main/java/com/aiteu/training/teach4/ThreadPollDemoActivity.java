package com.aiteu.training.teach4;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.aiteu.training.R;
import com.aiteu.training.base.BaseActionBarActivity;
import com.aiteu.training.utils.LogUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPollDemoActivity extends BaseActionBarActivity {

    private static final int THREAD_POLL_SIZE = 2;
    private static final int MONEY_COUNT = 100;
    private static final int MSG_MAC_START = 1;
    private static final int MSG_MAC_UPDATE = 2;
    private static final int MSG_MAC_END = 3;

    private ExecutorService mThreadPoll;

    private TextView mMoney1Txt, mMoney2Txt, mMoney3Txt;
    private Button mStart1Btn, mStart2Btn, mStart3Btn;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_MAC_START:
                    if(msg.arg1 == 1) {
                        mStart1Btn.setEnabled(false);
                    }else if(msg.arg1 == 2) {
                        mStart2Btn.setEnabled(false);
                    }else if(msg.arg1 == 3) {
                        mStart3Btn.setEnabled(false);
                    }
                    break;
                case MSG_MAC_UPDATE:
                    if(msg.arg1 == 1) {
                        updateMoney1Txt(msg.arg2);
                    }else if(msg.arg1 == 2) {
                        updateMoney2Txt(msg.arg2);
                    }else if(msg.arg1 == 3) {
                        updateMoney3Txt(msg.arg2);
                    }
                    break;
                case MSG_MAC_END:
                    if(msg.arg1 == 1) {
                        mStart1Btn.setEnabled(true);
                    }else if(msg.arg1 == 2) {
                        mStart2Btn.setEnabled(true);
                    }else if(msg.arg1 == 3) {
                        mStart3Btn.setEnabled(true);
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_poll_demo);

        mMoney1Txt = findViewById(R.id.money_1_txt);
        mMoney2Txt = findViewById(R.id.money_2_txt);
        mMoney3Txt = findViewById(R.id.money_3_txt);
        mStart1Btn = findViewById(R.id.start_1_btn);
        mStart2Btn = findViewById(R.id.start_2_btn);
        mStart3Btn = findViewById(R.id.start_3_btn);
        updateMoney1Txt(0);
        updateMoney2Txt(0);
        updateMoney3Txt(0);

        mStart1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStart1Btn.setEnabled(false);
                mThreadPoll.submit(new MoneyMac(1));
            }
        });
        mStart2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStart2Btn.setEnabled(false);
                mThreadPoll.submit(new MoneyMac(2));
            }
        });
        mStart3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStart3Btn.setEnabled(false);
                mThreadPoll.submit(new MoneyMac(3));
            }
        });

        mThreadPoll = Executors.newFixedThreadPool(THREAD_POLL_SIZE);
    }

    private void updateMoney1Txt(int money){
        mMoney1Txt.setText(String.valueOf(money));
    }

    private void updateMoney2Txt(int money){
        mMoney2Txt.setText(String.valueOf(money));
    }

    private void updateMoney3Txt(int money){
        mMoney3Txt.setText(String.valueOf(money));
    }

    private class MoneyMac implements Runnable {

        private int mMacNo;
        private int mMoney;

        public MoneyMac (int macNo){
            this.mMacNo = macNo;
            this.mMoney = 0;
        }

        @Override
        public void run() {
            Message startMsg = mHandler.obtainMessage();
            startMsg.what = MSG_MAC_START;
            startMsg.arg1 = mMacNo;
            mHandler.sendMessage(startMsg);
            LogUtils.d(Thread.currentThread().getName()+mMacNo+": start");
            while(mMoney < MONEY_COUNT) {
                try{
                    Thread.sleep(10);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                mMoney++;
                Message msg = mHandler.obtainMessage();
                msg.what = MSG_MAC_UPDATE;
                msg.arg1 = mMacNo;
                msg.arg2 = mMoney;
                mHandler.sendMessage(msg);
                LogUtils.d(Thread.currentThread().getName()+mMacNo+": update " + mMoney);
            }
            Message msg = mHandler.obtainMessage();
            msg.what = MSG_MAC_END;
            msg.arg1 = mMacNo;
            msg.arg2 = mMoney;
            mHandler.sendMessage(msg);
            LogUtils.d(Thread.currentThread().getName()+mMacNo+": end");
        }
    }
}
