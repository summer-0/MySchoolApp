package com.example.a49944.myapp.adapter;

import android.os.Bundle;

public final class ViewPagerInfo {
    public final String tag;
    public final Class<?> clazz;
    public final Bundle bundle;
    public final String title;
    public ViewPagerInfo(String _title, String _tag, Class<?> _class, Bundle _bundle){
        title = _title;
        tag = _tag;
        clazz = _class;
        bundle = _bundle;
    }
}
