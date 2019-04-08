package com.example.a49944.myapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

public class WebViewActivity extends AppCompatActivity {
    private WebView mWebView;
    private ProgressBar mProgressBar;
    private static final int MAX_VALUE = 100;
    private static final String BLOG_URL = "https://juejin.im/timeline/android";
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        mWebView = findViewById(R.id.webview);
        mProgressBar = findViewById(R.id.pb_progress);

        WebSettings settings = mWebView.getSettings();//用来设置webview的属性
        settings.setBuiltInZoomControls(true); //支持放大缩放
        settings.setJavaScriptEnabled(true); //支持Javascript
        mWebView.loadUrl(BLOG_URL);
        mWebView.setWebChromeClient(mWebChromeClient);
    }
    private WebChromeClient mWebChromeClient = new WebChromeClient(){
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            mProgressBar.setProgress(newProgress);//加载过程中跟新进度
            if (newProgress == MAX_VALUE){
                mProgressBar.setVisibility(View.GONE); //如果进度条达到最大值，则隐藏进度条
            }
            super.onProgressChanged(view, newProgress);
        }
    };

}
