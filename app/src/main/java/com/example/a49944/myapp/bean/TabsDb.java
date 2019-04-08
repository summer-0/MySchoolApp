package com.example.a49944.myapp.bean;

import com.example.a49944.myapp.R;

public class TabsDb {
    public static String[] getTabsTxt(){
        String[] tabs={"校园","发现","学习","我的"};
        return tabs;
    }
    public static int[] getTabsImg(){
        int[] ids={R.mipmap.ic_tabbar_campus,R.mipmap.ic_tabbar_discover,R.mipmap.ic_tabbar_studyhl,R.mipmap.ic_tabbar_me};
        return ids;
    }
    public static int[] getTabsImgLight(){
        int[] ids={R.mipmap.ic_tabbar_campushl,R.mipmap.ic_tabbar_discoverhl,R.mipmap.ic_tabbar_study,R.mipmap.ic_tabbar_mehl};
        return ids;
    }
}
