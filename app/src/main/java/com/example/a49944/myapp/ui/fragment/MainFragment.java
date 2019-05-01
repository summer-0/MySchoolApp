package com.example.a49944.myapp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import com.example.a49944.myapp.R;
import com.example.a49944.myapp.bean.MainTabs;
import com.example.a49944.myapp.bean.TabsDb;

public class MainFragment extends Fragment {
    private FragmentTabHost mTabHost;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_main, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTabHost = view.findViewById(R.id.tabHost);
        mTabHost.setup(getContext(), getFragmentManager(), R.id.home_content);
        mTabHost.getTabWidget().setDividerDrawable(null);
        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                updataTab();
            }
        });
        initTab();
    }

    private void updataTab() {
        TabWidget tabWidget = mTabHost.getTabWidget();
        for (int i=0; i<tabWidget.getChildCount(); i++){
            View view = tabWidget.getChildAt(i);
            ImageView imageView = view.findViewById(R.id.iv_Img);
            TextView textView = view.findViewById(R.id.tv_title);
            if (i == mTabHost.getCurrentTab()){
                textView.setTextColor(getResources().getColor(R.color.main_tab_checked));
                imageView.setImageResource(TabsDb.getTabsImgLight()[i]);
            }else {
                textView.setTextColor(getResources().getColor(R.color.main_tab_noChecked));
                imageView.setImageResource(TabsDb.getTabsImg()[i]);
            }
        }
    }

    private void initTab() {
        MainTabs[] tabs = MainTabs.values();
        String[] tabStr = getActivity().getResources().getStringArray(R.array.main_tab);
        for (int i=0; i<tabs.length; i++){
            View v = LayoutInflater.from(getActivity()).inflate(R.layout.foot_main_tab, null);
            TextView textView = v.findViewById(R.id.tv_title);
            ImageView imageView = v.findViewById(R.id.iv_Img);
            textView.setText(getString(tabs[i].getResName()));
            if (i==0){
                textView.setTextColor(getResources().getColor(R.color.main_tab_checked));
                imageView.setImageResource(TabsDb.getTabsImgLight()[i]);
            }else {
                imageView.setImageResource(TabsDb.getTabsImg()[i]);
            }
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(getString(tabs[i].getResName())).setIndicator(v);
            mTabHost.addTab(tabSpec, tabs[i].getClz(), null);
            mTabHost.setTag(i);
        }
    }

}
