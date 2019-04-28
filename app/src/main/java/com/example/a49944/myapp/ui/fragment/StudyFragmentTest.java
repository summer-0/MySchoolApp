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
import com.example.a49944.myapp.adapter.StudyViewPagerAdapter;

import java.util.Arrays;
import java.util.List;

public class StudyFragmentTest extends Fragment {
    private static final String TAG = StudyFragmentTest.class.getName();
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private List<String> mStudyTab;
    private int[] mArrayTag;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_study, null);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
    }

    private void initView(View view) {
        mTabLayout = view.findViewById(R.id.tab_layout);
        mViewPager = view.findViewById(R.id.viewpager);
    }

    private void initData() {
        mArrayTag = getResources().getIntArray(R.array.study_int);
        mStudyTab = Arrays.asList(getResources().getStringArray(R.array.study_tab));
        StudyViewPagerAdapter adapter = new StudyViewPagerAdapter(getChildFragmentManager(), mStudyTab, mArrayTag);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
