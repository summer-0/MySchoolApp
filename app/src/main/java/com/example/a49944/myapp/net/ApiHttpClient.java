package com.example.a49944.myapp.net;

import com.example.a49944.myapp.utils.LogUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class ApiHttpClient {
    private static final String TAG = ApiHttpClient.class.getName();
    /**
     * 导航
     * https://www.wanandroid.com/navi/json
     * 方法：GET
     * 参数：无
     */
    private static String API_URL = "https://www.wanandroid.com/";
    private static String API_URL_LOCAL = "http://192.168.17.38:8080/%s";
    public static AsyncHttpClient client;

    public ApiHttpClient() {
    }

    public static void setHttpClient(AsyncHttpClient c) {
        client = c;
    }

    public static AsyncHttpClient getHttpClient() {
        return client;
    }

    /**
     * 设置主url
     *
     * @param apiUrl
     */
    public static void setApiUrl(String apiUrl) {
        API_URL = apiUrl;
    }

    /**
     * 得到完整的url
     *
     * @param partUrl : 部分url
     * @return
     */
    public static String getAbsoluteApiUrl(String partUrl) {
        String url = String.format(API_URL+partUrl);
        LogUtils.i(TAG, url);
        return url;
    }

    public static String getLocalAbsoluteApiUrl(String partUrl) {
        String url = String.format(API_URL_LOCAL+partUrl);
        return url;
    }

    public static void get(String partUrl, AsyncHttpResponseHandler handler) {
        client.get(getAbsoluteApiUrl(partUrl), handler);
        LogUtils.i(TAG, new StringBuilder("GET").append(partUrl).toString());
    }

    public static void get(String partUrl, RequestParams params, AsyncHttpResponseHandler handler) {
        client.get(getAbsoluteApiUrl(partUrl), params, handler);
        LogUtils.i(TAG, new StringBuilder("GET").append(partUrl).append("&").append(params).toString());
    }

    /**
     * 获取局域网服务器数据
     *
     * @param partUrl
     * @param handler
     */
    public static void getLocal(String partUrl, AsyncHttpResponseHandler handler) {
        client.get(getLocalAbsoluteApiUrl(partUrl), handler);
        LogUtils.i(TAG, new StringBuilder("GET").append(partUrl).toString());
    }

    /**
     * 获取局域网服务器数据
     *
     * @param partUrl
     * @param params
     * @param handler
     */
    public static void getLocal(String partUrl, RequestParams params, AsyncHttpResponseHandler handler) {
        client.get(getLocalAbsoluteApiUrl(partUrl), params, handler);
        LogUtils.i(TAG, new StringBuilder("GET").append(partUrl).append("&").append(params).toString());
    }

    public static void getDirect(String url, AsyncHttpResponseHandler handler) {
        client.get(url, handler);
        LogUtils.i(TAG, new StringBuilder("GET").append(url).toString());
    }

    /**
     * post请求
     * @param partUrl
     * @param handler
     */
    public static void post(String partUrl, AsyncHttpResponseHandler handler) {
        client.post(getAbsoluteApiUrl(partUrl), handler);
        LogUtils.i(TAG, new StringBuilder("POST ").append(partUrl).toString());
    }
    public static void post(String partUrl, RequestParams params, AsyncHttpResponseHandler handler) {
        client.post(getAbsoluteApiUrl(partUrl), params, handler);
        LogUtils.i(TAG, new StringBuilder("POST ").append(partUrl).append("&")
                .append(params).toString());
    }
    public static void postDirect(String url, RequestParams params, AsyncHttpResponseHandler handler) {
        client.post(url, params, handler);
        LogUtils.i(TAG, new StringBuilder("POST ").append(url).append("&").append(params)
                .toString());
    }

    /**
     * put请求
     * @param partUrl
     * @param handler
     */
    public static void put(String partUrl, AsyncHttpResponseHandler handler) {
        client.put(getAbsoluteApiUrl(partUrl), handler);
        LogUtils.i(TAG, new StringBuilder("PUT ").append(partUrl).toString());
    }
    public static void put(String partUrl, RequestParams params, AsyncHttpResponseHandler handler) {
        client.put(getAbsoluteApiUrl(partUrl), params, handler);
        LogUtils.i(TAG, new StringBuilder("PUT ").append(partUrl).append("&").append(params).toString());
    }

}
