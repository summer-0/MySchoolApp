package com.example.a49944.myapp;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import com.example.a49944.myapp.bean.MainTabs;
import com.example.a49944.myapp.bean.TabsDb;

public class MainActivity extends FragmentActivity {
    private FragmentTabHost mTabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();

       // getSupportFragmentManager().beginTransaction().add(R.id.main_content, FragmentManagerWrapper.getInstance().createFragment(LoginActivity.class)).commitAllowingStateLoss();
    }

    private void initView() {
        mTabHost = findViewById(R.id.tabHost);
    }

    private void initData() {
        mTabHost.setup(this, getSupportFragmentManager(), R.id.home_content);
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
        String[] tabStr = getResources().getStringArray(R.array.main_tab);
        for (int i=0; i<tabs.length; i++){
            View v = LayoutInflater.from(this).inflate(R.layout.foot_main_tab, null);
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

    /**
     * 从登录界面切换至主界面
     */
//    public void changeToHomeFragment(){
//        //getSupportFragmentManager().beginTransaction().replace(R.id.main_content, FragmentManagerWrapper.getInstance().createFragment(MainFragment.class)).commitAllowingStateLoss();
//    }

}
