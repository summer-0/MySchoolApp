package com.example.a49944.myapp.ui.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.example.a49944.myapp.R;
import com.example.a49944.myapp.adapter.ViewPagerFragmentAdapter;
import com.example.a49944.myapp.bean.json.NaviBean;
import com.example.a49944.myapp.cache.CacheManager;
import com.example.a49944.myapp.net.ApiHttpClient;
import com.example.a49944.myapp.net.HttpDataApi;
import com.example.a49944.myapp.ui.fragment.base.BaseViewPagerFragment;
import com.example.a49944.myapp.utils.LogUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import cz.msebera.android.httpclient.Header;

import java.util.ArrayList;
import java.util.List;

public class StudyFragment extends BaseViewPagerFragment {
    private static final String TAG = StudyFragment.class.getName();
    private String mParam;
    private ArrayList<NaviBean> mNaviBeans;

    /*private AsyncHttpResponseHandler mHandlers = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
            StringBuilder str = new StringBuilder();
            String s = new String(responseBody);
            LogUtils.i(TAG, s);
           *//* ArrayList<NaviBean> naviBeans = jsonToBean(s);
            mNaviBeans = naviBeans;
            CacheManager.setCacheData(getContext(), mNaviBeans, 0, "study");
            LogUtils.i(TAG, "缓存数据");*//*
        }
        @Override
        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            LogUtils.i(TAG, "failure: "+responseBody.toString());
        }
    };*/

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            mParam = getArguments().getString("key");
        }
    }

    @Override
    protected void onSetupTabAdapter(ViewPagerFragmentAdapter adapter) {
        String[] mTitles = getResources().getStringArray(R.array.study_tab);
        int[] mtitles = getResources().getIntArray(R.array.study_int);
        int size = mTitles.length;
        for (int i=0; i<size; i++){
            adapter.addTab(mTitles[i], "study", StudyContentFragment.class, getBundle(mtitles[i]));
        }
    }
    private Bundle getBundle(int newType){
        Bundle bundle = new Bundle();
        bundle.putInt("key", newType);
        return bundle;
    }
    private Bundle getBundle(String newType){
        Bundle bundle = new Bundle();
        bundle.putString("key",newType);
        return bundle;
    }

    @Override
    protected void setScreenPageLimit() {
        mViewPager.setOffscreenPageLimit(3);
    }
    /*private void requestStudyData(){
        ApiHttpClient.setHttpClient(new AsyncHttpClient());
        HttpDataApi.getNavigationList(0, 0, mHandlers);
    }
    private ArrayList<NaviBean> jsonToBean(String s) {
        JSONObject jsonObject = JSON.parseObject(s); // jsonObject --> StudyTab
        JSONArray data = jsonObject.getJSONArray("data"); // data
        ArrayList<NaviBean> naviBeans = JSON.parseObject(data.toJSONString(), new TypeReference<ArrayList<NaviBean>>(){});
        for (NaviBean naviBean : naviBeans){
            LogUtils.i(TAG, naviBean.getName());
        }
        return naviBeans;
    }*/

    @Override
    protected void requestData() {
        //requestStudyData();
        LogUtils.i(TAG, "QINGQIUSHUJ--->");
    }
}
