package com.aiteu.training.teach4;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.aiteu.training.R;
import com.aiteu.training.base.BaseActionBarActivity;
import com.aiteu.training.utils.LogUtils;

import java.util.Random;

public class AsyncTaskDemoActivity extends BaseActionBarActivity {
    private static final long MB = 1048576;

    private Button mStartBtn;
    private ProgressBar mProgressBar;
    private TextView mDownloadedTxt, mTotalTxt;
    private long mTotalSize = 2 * MB;
    private long mCurrentSize;
    private boolean mTaskRunning = false;
    private DownloadTask mTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task_demo);
        mStartBtn = findViewById(R.id.start_btn);
        mProgressBar = findViewById(R.id.progress_bar);
        mDownloadedTxt = findViewById(R.id.download_size_txt);
        mTotalTxt = findViewById(R.id.total_size_txt);
        mStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mTaskRunning) {
                    mTaskRunning = true;
                    mTask = new DownloadTask();
                    mTask.execute("", "", "");
                }
            }
        });
        mTaskRunning = false;
        mTotalTxt.setText("文件大小："+String.valueOf(mTotalSize));
        mDownloadedTxt.setText("已下载:0");
    }

    class DownloadTask extends AsyncTask<String, Long, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mTotalTxt.setText("文件大小："+String.valueOf(mTotalSize));
            mDownloadedTxt.setText("已下载:0");
            mStartBtn.setEnabled(false);
            Toast.makeText(getContext(), "模拟下载开始", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected String doInBackground(String... strings) {
            //在此执行耗时操作，当先在子线程
            //随机数类
            Random random = new Random(1);
            while(mCurrentSize < mTotalSize){
                mCurrentSize += random.nextInt(800);
                try{
                    Thread.sleep(500);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                //将进度传递出去
                publishProgress(mCurrentSize);
                LogUtils.d(Thread.currentThread().getName()+"--run");
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Long... values) {
            super.onProgressUpdate(values);
            //更新任务状态，当前在主线程
            long size = values[0];
            mDownloadedTxt.setText("已下载: "+String.valueOf(size));
            int percent = (int) (size * 1.0f / mTotalSize);
            mProgressBar.setProgress(percent);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //任务执行完成，当前在主线程
            mStartBtn.setEnabled(true);
            mTaskRunning = false;
            Toast.makeText(getContext(), "下载完成", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //FIXME:注意所有的线程在不需要的需要关闭
        if(mTask != null) {
            mTask.cancel(true);
        }
    }
}
