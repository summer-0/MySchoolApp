package com.example.a49944.myapp.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.example.a49944.myapp.R;
import com.example.a49944.myapp.ui.activity.WebViewActivity;
import com.example.a49944.myapp.adapter.StudyRecyclerAdapter;
import com.example.a49944.myapp.bean.json.NaviBean;
import com.example.a49944.myapp.net.ApiHttpClient;
import com.example.a49944.myapp.net.HttpDataApi;
import com.example.a49944.myapp.utils.LogUtils;
import com.example.a49944.myapp.utils.StudyNaviUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import cz.msebera.android.httpclient.Header;

import java.util.ArrayList;
import java.util.List;

public class StudyContentFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = StudyContentFragment.class.getName();
    private static final int WEBVIEW = 0;
    private String mParam;
    private int mCid;
    protected RecyclerView mRecyclerView;
    protected SwipeRefreshLayout mSwipeRefresh;
    private AsyncHttpClient client = new AsyncHttpClient();
    private ApiHttpClient apiHttpClient;
    private List<NaviBean> mNaviBean = new ArrayList<>();
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case WEBVIEW:
                    startWebViewActivity();
                    break;
            }
            //startWebViewActivity();
        }
    };

    private AsyncHttpResponseHandler mHandlers = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
            StringBuilder str = new StringBuilder();
            LogUtils.i(TAG, "success: " + responseBody.toString() + " " + str);
            String s = new String(responseBody);
            mNaviBean = jsonToBean(s);
        }
        @Override
        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            LogUtils.i(TAG, "failure: "+responseBody.toString());
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            //mParam = getArguments().getString("key");
            mCid = getArguments().getInt("key");
        }
    }

    private final Runnable mRefreshDone = new Runnable() {
        @Override
        public void run() {
            //结束刷新
            mSwipeRefresh.setRefreshing(false);
        }
    };


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.base_study_content, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mSwipeRefresh = view.findViewById(R.id.swipe_refresh);
        initView();
    }

    private void initView() {
        mSwipeRefresh.setOnRefreshListener(this);

      /*  List<NaviBean> naviBeans = CacheManager.getCacheData(getContext(), 0, "study");
        String[] str = {"Google开发者","Github","stackoverflow","掘金","CSDN","简书","开发技术周报","开发者头条","segmentfault",
                "androiddevtools","Google中文Blog","干货集中营","CodeKK","小专栏","国内大牛","国外大牛","Android源码",
                "Material Design","中文版","leetcode","google","mvn仓库","jcenter仓库","maven仓库"};
        List<String> list = Arrays.asList(str);*/

        List<String> list;
        list = StudyNaviUtils.getTitles(mCid, null);
        StudyRecyclerAdapter adapter = new StudyRecyclerAdapter(null);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        //给item设置点击事件
        adapter.setRecyclerItemClickListener(new StudyRecyclerAdapter.OnRecyclerItemClickListener() {
            @Override
            public void onItemClick() {
                mHandler.sendEmptyMessage(0);
            }
        });
    }
    //下拉刷新
    @Override
    public void onRefresh() {
        refresh();
    }



    private void refresh() {
        mHandler.removeCallbacks(mRefreshDone);
        mHandler.postDelayed(mRefreshDone, 1000);
    }
    private void requestData(){
        ApiHttpClient.setHttpClient(new AsyncHttpClient());
        HttpDataApi.getNavigationList(0, 0, mHandlers);
    }

    /**
     * json转为对象
     * @param s
     * @return
     */
    private List<NaviBean> jsonToBean(String s) {
        JSONObject jsonObject = JSON.parseObject(s); // jsonObject --> StudyTab
        JSONArray data = jsonObject.getJSONArray("data"); // data
        List<NaviBean> naviBeans = JSON.parseObject(data.toJSONString(), new TypeReference<List<NaviBean>>(){});
        return naviBeans;
    }
    private void startWebViewActivity(){
        Intent intent = new Intent(getActivity(), WebViewActivity.class);
        startActivity(intent);
    }
}
