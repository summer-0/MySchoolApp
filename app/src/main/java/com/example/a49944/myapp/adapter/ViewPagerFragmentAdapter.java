package com.example.a49944.myapp.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.example.a49944.myapp.R;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerFragmentAdapter extends FragmentStatePagerAdapter {
    private Context mContext;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private List<ViewPagerInfo> mTabs = new ArrayList<>();

    public ViewPagerFragmentAdapter(FragmentManager fm, TabLayout tabLayout, ViewPager viewPager) {
        super(fm);
        mContext = viewPager.getContext();
        mTabLayout = tabLayout;
        mViewPager = viewPager;
        //给viewpager设置adapter
        mViewPager.setAdapter(this);
        //关联tablayout和viewpager
        mTabLayout.setupWithViewPager(mViewPager);
    }

    public void addTab(String title, String tag, Class<?> clazz, Bundle bundle){
        ViewPagerInfo viewPagerInfo = new ViewPagerInfo(title, tag, clazz, bundle);
        addFragment(viewPagerInfo);
    }
    public void addAllTab(ArrayList<ViewPagerInfo> mTabs) {
        for (ViewPagerInfo viewPageInfo : mTabs) {
            addFragment(viewPageInfo);
        }
    }
    public void addFragment(ViewPagerInfo info){
        if (info == null){
            return;
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.base_viewpager_fragment_tab_item, null, false);
        TextView title = view.findViewById(R.id.tab_title);
        title.setText(info.title);
        TabLayout.Tab tab = mTabLayout.newTab();
        tab.setCustomView(view);
        mTabLayout.addTab(tab);
        mTabs.add(info);
        notifyDataSetChanged();
    }

    /**
     * 移除所有的tab
     */
    public void removeAll() {
        if (mTabs.isEmpty()) {
            return;
        }
        mTabLayout.removeAllTabs();
        mTabs.clear();
        notifyDataSetChanged();
    }
    @Override
    public Fragment getItem(int i) {
        ViewPagerInfo info = mTabs.get(i);
        return Fragment.instantiate(mContext, info.clazz.getName(), info.bundle);
    }

    @Override
    public int getCount() {
        return mTabs.size();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTabs.get(position).title;
    }
}
