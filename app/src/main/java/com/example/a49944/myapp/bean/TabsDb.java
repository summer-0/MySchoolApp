package com.example.a49944.myapp.bean;

import com.example.a49944.myapp.R;

public class TabsDb {
    public static String[] getTabsTxt(){
        String[] tabs={"校园","发现","学习","我的"};
        return tabs;
    }
    public static int[] getTabsImg(){
        int[] ids={R.mipmap.ic_tabbar_mainframe2,R.mipmap.ic_tabbar_discover2,R.mipmap.ic_tabbar_studyhl2,R.mipmap.ic_tabbar_me2};
        return ids;
    }
    public static int[] getTabsImgLight(){
        int[] ids={R.mipmap.ic_tabbar_mainframehl2,R.mipmap.ic_tabbar_discoverhl2,R.mipmap.ic_tabbar_study2,R.mipmap.ic_tabbar_mehl2};
        return ids;
    }
}
