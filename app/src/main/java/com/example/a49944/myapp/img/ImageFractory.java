package com.example.a49944.myapp.img;

import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;

/**
 * Created by summer_h on 2019/5/2 17:04
 * 对 glide 图片加载库进行封装
 */
public class ImageFractory {
    public static void MGlideYZM(Context contex, String url, String cookie, ImageView target, int resourceId) {
        //构造请求体的头部
        GlideUrl gurl = new GlideUrl(url, new LazyHeaders.Builder().addHeader("Cookie", cookie).build());
        Glide.with(contex)
                .load(gurl)
                .placeholder(resourceId)
                .error(resourceId)
                .fallback(resourceId)
                .centerCrop()
                .skipMemoryCache(true)//跳过内存缓存
                .diskCacheStrategy(DiskCacheStrategy.NONE)//跳过磁盘缓存
                .into(target);
    }
}
