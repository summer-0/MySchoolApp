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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.a49944.myapp.R;
import com.example.a49944.myapp.adapter.DiscoverRecyclerAdapter;
import com.example.a49944.myapp.bean.discover.JuHeBean;
import com.example.a49944.myapp.constant.AppConstant;
import com.example.a49944.myapp.net.NetRequestManager;
import com.example.a49944.myapp.sdk.ConfigManager;
import com.example.a49944.myapp.utils.LogUtils;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import java.util.ArrayList;
import java.util.List;

public class DefaultDisTabFragment extends Fragment {
    private static final String TAG = DefaultDisTabFragment.class.getSimpleName();
    private static final int SWIPE_REFRESH = 0;
    private String mTag;
    private RecyclerView mRecyclerView;
    private TextView mTextView;
    private SwipeRefreshLayout mSwipeRefresh;
    private ConfigManager mConfigManager;
    private List<JuHeBean.ResultBean.DataBean> mListBean;
    private DiscoverRecyclerAdapter mAdapter;
    //private Context mContext;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SWIPE_REFRESH:
                    mSwipeRefresh.setRefreshing(false);
                    break;
                default:
                    break;
            }
        }
    };

    public DefaultDisTabFragment() {
    }

    @SuppressLint("ValidFragment")
    public DefaultDisTabFragment(String tag) {
        mTag = tag;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //this.mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main_dis_tab_default, null);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
    }

    private void initView(View view) {
        mRecyclerView = view.findViewById(R.id.recycler_disTab);
        mTextView = view.findViewById(R.id.tv_loading);
        mSwipeRefresh = view.findViewById(R.id.swipeRefresh_dis);
    }

    private void initData() {
        // TODO: 2019/4/18
        mListBean = new ArrayList<>();
        LogUtils.i(TAG, mTag);
        mConfigManager = ConfigManager.getInstance();
        List<JuHeBean.ResultBean.DataBean> disTabList = mConfigManager.getDisTabList(mTag);
        if (disTabList != null && disTabList.size() > 0) {
            mListBean = disTabList;
            mTextView.setVisibility(View.GONE);
        }
        mAdapter = new DiscoverRecyclerAdapter(mListBean, getContext());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
        if (mListBean.size() <= 0) {
            //数据请求 并用sp保存
            LogUtils.i(TAG, "正在准备请求" + mTag + "数据");
            requestData();
        }

        mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mHandler.sendEmptyMessageDelayed(0, 3000);
                requestData();
            }
        });
    }

    private void requestData() {
        Observable<JuHeBean> observable = NetRequestManager.create(AppConstant.JUHE_HEAD).requestJuHeData(mTag);
        observable.subscribe(new Observer<JuHeBean>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.i(TAG, "onSubscribe");
            }
            @Override
            public void onNext(JuHeBean juHeBean) {
                LogUtils.i(TAG, "onNext");
                mListBean.clear();
                mListBean = juHeBean.getResult().getData();
                LogUtils.i(TAG, mListBean.get(0).getTitle());
                mAdapter.refreshDate(mListBean);
                mAdapter.notifyDataSetChanged();
                if (mListBean != null && mListBean.size() > 0) {
                    LogUtils.i(TAG, "sp保存数据");
                    mConfigManager.setDisTabList(mListBean, mTag);
                }
            }
            @Override
            public void onError(Throwable e) {
                LogUtils.i(TAG, "onError");
            }
            @Override
            public void onComplete() {
                LogUtils.i(TAG, "onComplete");
                mTextView.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
