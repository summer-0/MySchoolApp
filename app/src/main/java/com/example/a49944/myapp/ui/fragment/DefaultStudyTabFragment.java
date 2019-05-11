package com.example.a49944.myapp.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.a49944.myapp.R;
import com.example.a49944.myapp.adapter.DiscoverRecyclerAdapter;
import com.example.a49944.myapp.adapter.StudyRecyclerAdapter;
import com.example.a49944.myapp.adapter.StudyRecyclerAdapterTest;
import com.example.a49944.myapp.bean.discover.JuHeBean;
import com.example.a49944.myapp.bean.study.WanAndroidBean;
import com.example.a49944.myapp.constant.AppConstant;
import com.example.a49944.myapp.net.NetRequestManager;
import com.example.a49944.myapp.sdk.ConfigManager;
import com.example.a49944.myapp.utils.LogUtils;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import java.util.ArrayList;
import java.util.List;

public class DefaultStudyTabFragment extends Fragment {
    private static final String TAG = DefaultStudyTabFragment.class.getSimpleName();
    private static final int SWIPE_REFRESH = 0;
    private  int mTag;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private TextView mTextView;
    private List<WanAndroidBean.DataBean.ArticlesBean> mListBean;
    private ConfigManager mConfigManager;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SWIPE_REFRESH:
                    mSwipeRefreshLayout.setRefreshing(false);
                    break;
                default:
                    break;
            }
        }
    };
    private StudyRecyclerAdapterTest mAdapter;

    public DefaultStudyTabFragment() {
    }

    @SuppressLint("ValidFragment")
    public DefaultStudyTabFragment(int tag) {
        mTag = tag;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main_study_tab_default, null);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
    }

    private void initView(View view) {
        mSwipeRefreshLayout = view.findViewById(R.id.swipeRefresh_study);
        mRecyclerView = view.findViewById(R.id.recycler_studyTab);
        mTextView = view.findViewById(R.id.tv_loading);
    }

    private void initData() {
        mConfigManager = ConfigManager.getInstance();
        mListBean = new ArrayList<>();

        //这一步会影响速度（需要读取sp，并解析json）
        List<WanAndroidBean.DataBean.ArticlesBean> studyTabList = mConfigManager.getStudyTabList("study_" + mTag);
        if (studyTabList != null && studyTabList.size() > 0) {
            mListBean = studyTabList;
            mTextView.setVisibility(View.GONE);
        }
        mAdapter = new StudyRecyclerAdapterTest(mListBean, getContext());
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);
        if (mListBean.size() <= 0) {
            //数据请求 并用sp保存
            LogUtils.i(TAG, "正在准备请求" + mTag + "数据");
            requestData();
        }
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mHandler.sendEmptyMessageDelayed(0, 3000);
                requestData();
            }
        });
    }

    private void requestData() {
        Observable<WanAndroidBean> observable = NetRequestManager.create(AppConstant.WANANDROID_HEAD).requestStudyNaviData();
        observable.subscribe(new Observer<WanAndroidBean>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.i(TAG, "onSubscribe");
            }

            @Override
            public void onNext(WanAndroidBean wanAndroidBean) {
                LogUtils.i(TAG, "onNext");
                List<WanAndroidBean.DataBean> data = wanAndroidBean.getData();
                mListBean.clear();
                switch (mTag) {
                    case 272:
                        mListBean = data.get(0).getArticles();       //常用网站
                        break;
                    case 280:
                        mListBean = data.get(2).getArticles();       //开发社区
                        break;
                    case 276:
                        mListBean = data.get(5).getArticles();       //在线学习
                        break;
                    case 277:
                        mListBean = data.get(7).getArticles();       //开发平台
                        break;
                    case 274:
                        mListBean = data.get(3).getArticles();       //个人博客
                        break;
                    case 275:
                        mListBean = data.get(4).getArticles();       //常用工具
                        break;
                    case 281:
                        mListBean = data.get(1).getArticles();       //公司博客
                        break;
                    case 278:
                        mListBean = data.get(8).getArticles();       //互联网资讯
                        break;
                    case 290:
                        mListBean = data.get(18).getArticles();       //后端云
                        break;
                    case 299:
                        mListBean = data.get(20).getArticles();       //创意素材
                        break;
                    case 301:
                        mListBean = data.get(22).getArticles();       //快速开发
                        break;
                    default:
                        break;
                }
                mAdapter.refreshDate(mListBean);
                mAdapter.notifyDataSetChanged();
                mTextView.setVisibility(View.GONE);
                if (mListBean != null && mListBean.size() > 0) {
                    LogUtils.i(TAG, "正在用sp保存数据");
                    mConfigManager.setStudyTabList(mListBean, "study_" + mTag);
                  /*  new Thread(){
                        @Override
                        public void run() {
                            mConfigManager.setStudyTabList(mListBean, "study_"+mTag);
                        }
                    }.start();*/
                }

            }

            @Override
            public void onError(Throwable e) {
                LogUtils.i(TAG, "onError");
            }

            @Override
            public void onComplete() {
                LogUtils.i(TAG, "onComplete");
            }
        });


    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
