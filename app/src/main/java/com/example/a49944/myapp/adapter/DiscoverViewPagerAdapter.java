package com.example.a49944.myapp.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.example.a49944.myapp.ui.fragment.DefaultDisTabFragment;
import com.example.a49944.myapp.utils.LogUtils;

import java.util.List;

/**
 * Created by summer_h on 2019/4/17 21:10
 */
public class DiscoverViewPagerAdapter extends FragmentStatePagerAdapter {
    public static final String TAG = DiscoverViewPagerAdapter.class.getSimpleName();
    private Context mContext;
    private List<String> mList;
    private List<String> mListTags;
    public DiscoverViewPagerAdapter(FragmentManager fm, List<String> list, List<String> listTags) {
        super(fm);
        mList = list;
        mListTags = listTags;
    }

    @Override
    public Fragment getItem(int i) {
        LogUtils.i(TAG, " i= "+i);
        return new DefaultDisTabFragment(mListTags.get(i));
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
