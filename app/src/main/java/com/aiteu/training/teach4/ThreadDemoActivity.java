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

public class ThreadDemoActivity extends BaseActionBarActivity {

    private static final int TIME_COUNT = 10;

    private TextView mTimeTxt;
    private Button mStartBtn;
    private Button mStopBtn;
    private int mCurrentTime = TIME_COUNT;
    private boolean mThreadPaused = false;
    //消息队列
    private Handler mHandler = new Handler(){
        @Override
        public void dispatchMessage(Message msg) {
            switch (msg.what) {
                case 0 :
                    onTimerEnd();
                    break;
                case 1:
                    onTimerUpdate();
                    break;
                case 2:
                    onTimerStopped();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_demo);
        mTimeTxt = findViewById(R.id.time_txt);
        mStartBtn = findViewById(R.id.start_btn);
        mStopBtn = findViewById(R.id.stop_btn);
        mStartBtn.setEnabled(true);
        mStopBtn.setEnabled(false);
        mTimeTxt.setText(String.valueOf(mCurrentTime));
        mStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer();
            }
        });
        mStopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mThreadPaused = true;
            }
        });
    }

    private void onTimerEnd(){
        LogUtils.d("refresh_button: "+Thread.currentThread().getName());
        mStartBtn.setEnabled(true);
        mStopBtn.setEnabled(false);
    }

    private void onTimerUpdate(){
        LogUtils.d("refresh_time: "+Thread.currentThread().getName());
        mTimeTxt.setText(String.valueOf(mCurrentTime));
    }

    private void onTimerStopped() {
        LogUtils.d("线程结束了");
        mStartBtn.setEnabled(true);
        mStopBtn.setEnabled(false);
    }

    private void startTimer() {
        mStartBtn.setEnabled(false);
        mStopBtn.setEnabled(true);
        mThreadPaused = false;
        mCurrentTime = TIME_COUNT;
        mTimeTxt.setText(String.valueOf(mCurrentTime));
        Thread timerThread = new Thread(TIME_RUNNABLE);
        timerThread.setName("timer_count_thread");
        timerThread.start();
    }

    final Runnable TIME_RUNNABLE = new Runnable() {
        @Override
        public void run() {
            //使用标志位终止线程
            while(!mThreadPaused) {
                try{
                    Thread.sleep(1000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                mCurrentTime --;
                LogUtils.d("timer_count: "+Thread.currentThread().getName());

                if(mCurrentTime < 0) {
                    //方法１：在主线程更新ui
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            onTimerEnd();
//                        }
//                    });
                    //方法２：通过消息队列更新ui
                    mHandler.sendEmptyMessage(0);
                    return;
                }
                //1
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        onTimerUpdate();
//                    }
//                });
                //2
                mHandler.sendEmptyMessage(1);
            }
            LogUtils.d("线程结束了");
            if(mThreadPaused) {
                //1
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        onTimerStopped();
//                    }
//                });
                //2
                mHandler.sendEmptyMessage(2);
            }
        }
    };
}
