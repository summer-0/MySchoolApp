package com.example.a49944.myapp.ui.fragment.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.a49944.myapp.R;
import com.example.a49944.myapp.adapter.ViewPagerFragmentAdapter;

public abstract class BaseViewPagerFragment extends Fragment {
    protected TabLayout mTabLayout;
    protected ViewPager mViewPager;
    protected ViewPagerFragmentAdapter mAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //填充并返回一个公共的包含tablayout和viewpager的界面
        return inflater.inflate(R.layout.fragment_base_viewpager, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTabLayout = view.findViewById(R.id.tab_layout);
        mViewPager = view.findViewById(R.id.viewpager);
        // 封装adapter, 注意这里是继承的FragmentStatePagerAdapter, 并且传入的是getChildFragmentManager()
        // mTabLayout, ViewPager, 在Adapter内部进行一系列的初始化.
        mAdapter = new ViewPagerFragmentAdapter(getChildFragmentManager(), mTabLayout, mViewPager);
        //设置viewpager左右两边保留页面的个数，这里为空实现，子类开源重写方法进行设置
        setScreenPageLimit();
        // 通过ViewPageFragmentAdapter设置Tab选项及内容, 抽象方法, 由子类重写进行实现.
        onSetupTabAdapter(mAdapter);
        requestData();
    }

    protected void setScreenPageLimit() {
    }
    protected abstract void onSetupTabAdapter(ViewPagerFragmentAdapter adapter);
    protected void requestData(){}
}
