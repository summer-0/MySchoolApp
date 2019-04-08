package com.example.a49944.myapp.cache;

import android.content.Context;
import android.util.Config;
import com.example.a49944.myapp.bean.json.NaviBean;
import com.example.a49944.myapp.utils.LogUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CacheManager {
    private static final String TAG = CacheManager.class.getName();
    private static final String STUDY = "cache_study";
    private static final String DISCOVER = "cache_discover";
    private static final String OTHER = "cache_other";

    /**
     * 缓存数据
     * @param context
     * @param list
     * @param tag
     */
    public static void saveStudyData(Context context, List<NaviBean> list, String tag){
        File cacheFile = new File(context.getCacheDir(), tag);
        if (!cacheFile.exists()){ //如果改缓存文件不存在，则创建
            try {
                cacheFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(cacheFile));
            oos.writeObject(list);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static List<NaviBean> getStudyData(Context context, String tag){
        List<NaviBean> list = new ArrayList<>();
        File cacheFile = new File(context.getCacheDir(), tag);
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(cacheFile));
            list = (List<NaviBean>) ois.readObject();
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static <T> void setCacheData(Context context, List<T> list, int type, String tag){
        File file = context.getCacheDir();
        File cache = null;
        String name;
        if (tag.equals("study")){
            name = STUDY;
            cache = new File(file, tag);
        }else if (tag.equals("discover")){
            name = DISCOVER;
            cache = new File(file, tag);
        }else {
            name = OTHER;
            cache = new File(file, tag);
        }

        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(cache));
            oos.writeObject(list);
            LogUtils.i(TAG, STUDY);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static <T> List<T> getCacheData(Context context, int type, String tag){
        File file = context.getCacheDir();
        String name;
        File cache = null;
        List<T> list = null;
        if (tag.equals("study")){
            name = STUDY;
            cache = new File(file ,name);
            if (!cache.exists()){
                return null;
            }
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(cache));
                list = (List<T>) ois.readObject();
                return list;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (tag.equals("discover")){
            name = DISCOVER;
            cache = new File(file, name);
            if (!cache.exists()){
                return null;
            }
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(cache));
                list = (List<T>) ois.readObject();
                return list;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
       return null;
    }
}
