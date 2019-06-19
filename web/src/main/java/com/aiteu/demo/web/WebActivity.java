package com.aiteu.demo.web;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ConsoleMessage;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class WebActivity extends AppCompatActivity {
    static final String TAG = "WebActivityDemo";
    private WebView webView;
    private TextView mConsoleTxt;
    private boolean isBig;
    private int bigHeight, normalHeight;
    private StringBuffer buf = new StringBuffer();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        webView = findViewById(R.id.web_view);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        webView.addJavascriptInterface(new LocalReader(), "reader");
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        mConsoleTxt = findViewById(R.id.console_txt);
        mConsoleTxt.setMovementMethod(ScrollingMovementMethod.getInstance());
        mConsoleTxt.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                isBig = !isBig;
                refreshHeight();
                return true;
            }
        });
        isBig = false;
        bigHeight = ResourceUtil.dip2px(this, 350);
        normalHeight = ResourceUtil.dip2px(this, 150);
        refreshHeight();

        webView.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if(!url.startsWith("http")){
                    return true;
                }
                return false;
            }

        });
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                log(consoleMessage.message());
                return super.onConsoleMessage(consoleMessage);
            }
        });
        webView.loadUrl("https://mobile.yangkeduo.com/duo_collection.html?");
    }

    private void refreshHeight(){
        ViewGroup.LayoutParams lp = mConsoleTxt.getLayoutParams();
        if(isBig) {
            lp.height = bigHeight;
        }else{
            lp.height = normalHeight;
        }
        mConsoleTxt.setLayoutParams(lp);
        mConsoleTxt.requestLayout();
    }

    private void log(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mConsoleTxt.append(message+"\n\n");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.web_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.view_source) {
            webView.loadUrl("javascript:reader.show(document.getElementsByTagName('html')[0].innerHTML)");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    class LocalReader {
        @JavascriptInterface
        public void show(String message) {
            log(message);
        }
    }
}
