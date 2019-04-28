package com.example.a49944.myapp.ui.fragment;

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
import com.example.a49944.myapp.adapter.DiscoverViewPagerAdapter;

import java.util.Arrays;
import java.util.List;

public class DiscoverFragment extends Fragment {
    public static final String TAG = DiscoverFragment.class.getSimpleName();
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_discover, null);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initView(view);
}

    private void initView(View view) {
        mTabLayout = view.findViewById(R.id.tablayout_dis);
        mViewPager = view.findViewById(R.id.viewpager_dis);
        String[] array = getResources().getStringArray(R.array.discover_tab);
        String[] arrayTag = getResources().getStringArray(R.array.discover_tab_tag);
        List<String> list = Arrays.asList(array);
        List<String> listTag = Arrays.asList(arrayTag);
        DiscoverViewPagerAdapter adapter = new DiscoverViewPagerAdapter(getChildFragmentManager(), list, listTag);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
        //mTabLayout.setTabsFromPagerAdapter(adapter);
    }

    /**
     * 加载数据
     */
    private void initData() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
