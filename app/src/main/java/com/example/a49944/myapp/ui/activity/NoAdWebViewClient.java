package com.example.a49944.myapp.ui.activity;

import android.content.Context;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.example.a49944.myapp.utils.ADFilterTool;

/**
 * Created by summer_h on 2019/4/21 20:46
 */
public class NoAdWebViewClient extends WebViewClient {
    private String mHomeUrl;
    private Context mContext;
    public NoAdWebViewClient(Context context, String homeUrl){
        mHomeUrl = homeUrl;
        mContext = context;
    }


    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
        if (!url.contains(mHomeUrl)){
            if (!ADFilterTool.hasAd(mContext,url)){
                return super.shouldInterceptRequest(view, url); //正常加载
            }else {
                return new WebResourceResponse(null, null,null); //含有广告屏蔽掉
            }
        }else {
            return super.shouldInterceptRequest(view, url);
        }
    }

    /**
     * 在点击请求的是链接是才会调用，重写此方法返回true表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边。
     * @param view
     * @param request
     * @return
     */
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        return super.shouldOverrideUrlLoading(view, request);
    }
}
