package com.aiteu.training.teach2;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.aiteu.training.R;
import com.aiteu.training.utils.LogUtils;

public class LifecycleFragment extends Fragment {

    private ProgressBar mBar;

    public static LifecycleFragment getIsntance(){
        return new LifecycleFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        LogUtils.d("Fragment:++++++++++onAttach+++++++++");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.d("Fragment:++++++++++onCreate+++++++++");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogUtils.d("Fragment:++++++++++onCreateView+++++++++");
        return inflater.inflate(R.layout.fragment_web, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        FragmentManager fm = getChildFragmentManager();
        LogUtils.d("Fragment:++++++++++onViewCreated+++++++++");
        WebView webView = view.findViewById(R.id.webView);
        mBar = view.findViewById(R.id.progress_bar);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                mBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mBar.setVisibility(View.GONE);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }
        });
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                mBar.setProgress(newProgress);
            }
        });
        webView.loadUrl("https://www.jianshu.com/u/2aed4af77269");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtils.d("Fragment:++++++++++onActivityCreated+++++++++");
    }

    @Override
    public void onStart() {
        super.onStart();
        LogUtils.d("Fragment:++++++++++onStart+++++++++");
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.d("Fragment:++++++++++onResume+++++++++");
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtils.d("Fragment:++++++++++onPause+++++++++");
    }

    @Override
    public void onStop() {
        super.onStop();
        LogUtils.d("Fragment:++++++++++onStop+++++++++");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtils.d("Fragment:++++++++++onDestroyView+++++++++");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        LogUtils.d("Fragment:++++++++++onDetach+++++++++");
    }
}
