package com.aiteu.demo.web;

import android.os.Build;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.io.File;

public class WebUtils {

    public static void setWebSettings(WebView webView){
        final WebSettings webSettings = webView.getSettings();
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(false);
        webSettings.setDisplayZoomControls(false);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //适配5.0不允许http和https混合使用情况
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
            webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
        webView.setInitialScale(101);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            webSettings.setAllowFileAccessFromFileURLs(false); //通过 file url 加载的 Javascript 读取其他的本地文件 .建议关闭
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        } else {
            webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        }
        webSettings.setNeedInitialFocus(true);
        File rootDir = webView.getContext().getCacheDir();
        File webDir = new File(rootDir, "web-cache");
        if(!webDir.exists()){
            webDir.mkdir();
        }
        webSettings.setAppCachePath(webDir.getPath());
    }
}
