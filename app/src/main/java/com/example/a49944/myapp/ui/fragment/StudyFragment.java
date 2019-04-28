package com.example.a49944.myapp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.example.a49944.myapp.R;
import com.example.a49944.myapp.adapter.ViewPagerFragmentAdapter;
import com.example.a49944.myapp.bean.json.NaviBean;
import com.example.a49944.myapp.ui.fragment.base.BaseViewPagerFragment;
import com.example.a49944.myapp.utils.LogUtils;

import java.util.ArrayList;

public class StudyFragment extends BaseViewPagerFragment {
    private static final String TAG = StudyFragment.class.getName();
    private String mParam;
    private ArrayList<NaviBean> mNaviBeans;


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

    @Override
    protected void requestData() {
        //requestStudyData();
        LogUtils.i(TAG, "QINGQIUSHUJ--->");
    }
}
