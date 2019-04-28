package com.example.a49944.myapp.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.example.a49944.myapp.ui.fragment.DefaultDisTabFragment;
import com.example.a49944.myapp.ui.fragment.DefaultStudyTabFragment;
import com.example.a49944.myapp.utils.LogUtils;

import java.util.List;

/**
 * Created by summer_h on 2019/4/17 21:10
 */
public class StudyViewPagerAdapter extends FragmentStatePagerAdapter {
    public static final String TAG = StudyViewPagerAdapter.class.getSimpleName();
    private List<String> mList;
    private int[] mListTags;
    public StudyViewPagerAdapter(FragmentManager fm, List<String> list, int[] listTags) {
        super(fm);
        mList = list;
        mListTags = listTags;
    }

    @Override
    public Fragment getItem(int i) {
        LogUtils.i(TAG, " i= "+i +" -> "+mListTags[i]);
        //LogUtils.i(TAG, ""+mListTags[i]);
        return new DefaultStudyTabFragment(mListTags[i]);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mList.get(position);
    }
}
