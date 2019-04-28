package com.example.a49944.myapp.utils;

import android.content.Context;
import android.content.res.Resources;
import com.example.a49944.myapp.R;

/**
 * Created by summer_h on 2019/4/21 20:56
 */
public class ADFilterTool {
    public static boolean hasAd(Context context, String url) {
        Resources res = context.getResources();
        String[] adUrls = res.getStringArray(R.array.adBlockUrl);
        for (String adUrl : adUrls) {
            if (url.contains(adUrl)){
                return true;
            }
        }
        return false;
    }
}
