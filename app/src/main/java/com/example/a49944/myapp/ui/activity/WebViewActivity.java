package com.example.a49944.myapp.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import com.example.a49944.myapp.R;
import com.example.a49944.myapp.utils.LogUtils;

public class WebViewActivity extends AppCompatActivity {
    public static final String TAG = WebViewActivity.class.getSimpleName();
    private WebView mWebView;
    private TextView mTitle;
    private Toolbar mToolBar;
    private  String mUrl = "https://juejin.im/timeline/android";

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        final String study_url = intent.getStringExtra("study_url");
        String category = intent.getStringExtra("category");
        if (study_url != null && !study_url.isEmpty()){
            mUrl = study_url;
        }else {
            mUrl = url;
        }


        mWebView = findViewById(R.id.webview);
        mToolBar = findViewById(R.id.toolbar_web);
        mTitle = findViewById(R.id.tv_title_dis);
        mTitle.setText(category);
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        WebSettings settings = mWebView.getSettings();//用来设置webview的属性
        settings.setBuiltInZoomControls(true); //支持放大缩放
        settings.setJavaScriptEnabled(true); //支持Javascript

        mWebView.loadUrl(mUrl);

        //在webview网络请求时加过滤，除了规定的域名以外不请求网络
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                LogUtils.i(TAG, "地址：" + url);
                if (study_url !=null && !study_url.isEmpty()){
                    return super.shouldInterceptRequest(view, url);
                }
                //如果包含你需要加载的域名则加载，不包含则不加载
                else if (url.contains("http://mini.eastday.com/") || url.contains("imgmini.eastday.com/mobile")) {
                    return super.shouldInterceptRequest(view, url);
                } else {
                    //去掉广告
                    return new WebResourceResponse(null, null, null);
                }
            }
        });
    }
}
