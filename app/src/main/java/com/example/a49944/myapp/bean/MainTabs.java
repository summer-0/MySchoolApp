package com.example.a49944.myapp.bean;

import com.example.a49944.myapp.R;
import com.example.a49944.myapp.ui.fragment.*;

public enum MainTabs {
    CAMPUS(0, R.string.tab_bottom_campus, R.drawable.selector_tab_img_campus, CampusFragment.class),
    DISCOVER(1, R.string.tab_bottom_discover, R.drawable.selector_tab_img_discover, DiscoverFragment.class),
    STUDY(2, R.string.tab_bottom_study, R.drawable.selector_tab_img_study, StudyFragmentTest.class),
    ME(3, R.string.tab_bottom_me, R.drawable.selector_tab_img_me, MeFragment.class);

    private int idx;
    private int resName;
    private int resIcon;
    private Class<?> clz;

    private MainTabs(int idx, int resName, int resIcon, Class<?> clz){
        this.idx = idx;
        this.resName = resName;
        this.resIcon = resIcon;
        this.clz = clz;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public int getResName() {
        return resName;
    }

    public void setResName(int resName) {
        this.resName = resName;
    }

    public int getResIcon() {
        return resIcon;
    }

    public void setResIcon(int resIcon) {
        this.resIcon = resIcon;
    }

    public Class<?> getClz() {
        return clz;
    }

    public void setClz(Class<?> clz) {
        this.clz = clz;
    }
}
