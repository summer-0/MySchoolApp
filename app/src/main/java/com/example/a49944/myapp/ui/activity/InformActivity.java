package com.example.a49944.myapp.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.example.a49944.myapp.R;

/**
 * Created by summer_h on 2019/4/29 21:35
 */
public class InformActivity extends AppCompatActivity {
    private WebView mWebView;
    public static final String mUrl = "http://www.wyu.edu.cn/xnzx.jsp?haction=&wbtreeid=1100";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inform);
        initView();
        initData();
    }

    private void initView() {
        mWebView = findViewById(R.id.webview_inform);
    }

    private void initData() {
        WebSettings settings = mWebView.getSettings();//用来设置webview的属性
        settings.setBuiltInZoomControls(true); //支持放大缩放
        settings.setJavaScriptEnabled(true); //支持Javascript
        mWebView.loadUrl(mUrl);
        mWebView.setWebViewClient(new WebViewClient());
    }


}
