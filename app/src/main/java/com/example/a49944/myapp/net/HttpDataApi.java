package com.example.a49944.myapp.net;

import com.example.a49944.myapp.utils.LogUtils;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class HttpDataApi {
    private static final String TAG = HttpDataApi.class.getName();
    /**
     * 登录
     * @param username
     * @param password
     * @param handler
     */
    public static void login(String username, String password, AsyncHttpResponseHandler handler){
        RequestParams params = new RequestParams();
        params.put("username", username);
        params.put("pwd", password);
        params.put("keep_login", 1);
        String loginUrl = "";
        ApiHttpClient.post(loginUrl, params, handler);
    }
    /**
     * 导航
     * https://www.wanandroid.com/navi/json
     * 方法：GET
     * 参数：无
     */
    /**
     * 获取玩Android导航列表
     * @param catalog
     * @param page
     * @param handler
     */
    public static void getNavigationList(int catalog, int page, AsyncHttpResponseHandler handler){
        RequestParams params = new RequestParams();
        LogUtils.i(TAG, "getNavigationList--> catalog："+catalog + " page: "+page);
        String path = "navi/json";
        ApiHttpClient.get(path, handler);
    }
}
