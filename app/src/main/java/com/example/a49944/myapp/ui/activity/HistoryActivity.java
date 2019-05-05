package com.example.a49944.myapp.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import com.example.a49944.myapp.R;
import com.example.a49944.myapp.adapter.MeHistoryRecyclerAdapter;
import com.example.a49944.myapp.bean.discover.JuHeBean;
import com.example.a49944.myapp.bean.mine.HistoryData;
import com.example.a49944.myapp.constant.ConfigConstant;
import com.example.a49944.myapp.sdk.ConfigManager;
import com.example.a49944.myapp.utils.LogUtils;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    private static final String TAG = HistoryActivity.class.getSimpleName();
    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private TextView mTvHistory;
    private FloatingActionButton mFabCLean;
    private List<HistoryData> mList;
    //    private ConfigManager mConfigManager;
    private Realm mRealm;
    private MeHistoryRecyclerAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        initView();
        initData();
    }

    private void initView() {
        mToolbar = findViewById(R.id.toolbar_web);
        mRecyclerView = findViewById(R.id.recycler_history);
        mTvHistory = findViewById(R.id.tv_history);
        mFabCLean = findViewById(R.id.fab_clean);
    }

    private void initData() {
//        mConfigManager = ConfigManager.getInstance();
        //实例化Realm
        mRealm = Realm.getDefaultInstance();

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mFabCLean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                //清除缓存
                mRealm.beginTransaction();
                mRealm.where(HistoryData.class).findAll().deleteAllFromRealm();
                mRealm.commitTransaction();
                mList = new ArrayList<>();
                mAdapter.refreshAdapter(mList);
                mAdapter.notifyDataSetChanged();
                mTvHistory.setVisibility(View.VISIBLE);
            }
        });
        mList = new ArrayList<>();
        mRealm.beginTransaction();
        RealmResults<HistoryData> historyDataList = mRealm.where(HistoryData.class).findAll();
        mRealm.commitTransaction();
        if (historyDataList.size() > 0) {
            mList = historyDataList;
            //Collections.reverse(mList);
            mTvHistory.setVisibility(View.GONE);
        }

//        List<JuHeBean.ResultBean.DataBean> historyData = mConfigManager.getHistoryData(ConfigConstant.ME_HISTORY);
//        if (historyData != null){
//            mList = historyData;
//            mTvHistory.setVisibility(View.GONE);
//        }
        mAdapter = new MeHistoryRecyclerAdapter(this, mList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mRealm != null) {
            mRealm.close();
        }
    }
}
