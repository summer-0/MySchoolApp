package com.example.a49944.myapp.sdk;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by unengchan on 2017/12/28.
 * SharedPreferences的辅助类
 */

public class SPUtils {
    private static SharedPreferences sp;
    private static String spName = "app_config";

    private SPUtils() {
    }

    /**
     * 保存数据到sp里面，根据传入的值自动判断类型。
     *
     * @param key
     * @param value
     */
    public static void putValue(String key, Object value) {
        if (sp == null) {
            sp = AppUtils.getAppContext().getSharedPreferences(spName, Context.MODE_PRIVATE);
        }
        String type = value == null ? "" : value.getClass().getSimpleName();
        SharedPreferences.Editor editor = sp.edit();
        try {
            if ("Boolean".equals(type)) {
                editor.putBoolean(key, (Boolean) value);
            } else if ("String".equals(type)) {
                editor.putString(key, ((String) value));
            } else if ("Integer".equals(type)) {
                editor.putInt(key, (Integer) value);
            } else if ("Long".equals(type)) {
                editor.putLong(key, (Long) value);
            } else if ("Float".equals(type)) {
                editor.putFloat(key, (Float) value);
            }
        } catch (Exception e) {
            editor.remove(key);
        }
        editor.apply();
    }

    /**
     * 根据key去获取值
     *
     * @param key
     * @param defaultValue 默认值，不能为空
     * @return
     */
    public static Object getValue(String key, Object defaultValue) {
        if (sp == null) {
            sp = AppUtils.getAppContext().getSharedPreferences(spName, Context.MODE_PRIVATE);
        }
        try {
            String type = defaultValue.getClass().getSimpleName();
            if ("Boolean".equals(type)) {
                return sp.getBoolean(key, (Boolean) defaultValue);
            }
            if ("String".equals(type)) {
                return sp.getString(key, (String) defaultValue);
            }
            if ("Integer".equals(type)) {
                return sp.getInt(key, (Integer) defaultValue);
            }
            if ("Long".equals(type)) {
                return sp.getLong(key, (Long) defaultValue);
            }
            if ("Float".equals(type)) {
                return sp.getFloat(key, (Float) defaultValue);
            }
        } catch (Exception e) {
            sp.edit().remove(key).apply();
        }
        return defaultValue;
    }

    /**
     * 保存集合数据
     *
     * @param key
     * @param dataList
     * @param <T>
     */
    public static <T> void setDataList(String key, List<T> dataList) {
        if (EmptyUtils.isEmpty(dataList)) {
            return;
        }
        Gson gson = new Gson();
        String jsonStr = gson.toJson(dataList);
        putValue(key, jsonStr);
    }

    /**
     * 获取集合数据
     *
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> getDataList(String key, Class<T> clazz) {
        String jsonStr = (String) getValue(key, "");
        if (EmptyUtils.isEmpty(jsonStr)) {
            return null;
        }
        List<T> dataList = new ArrayList<>();
        Gson gson = new Gson();
        JsonArray array = new JsonParser().parse(jsonStr).getAsJsonArray();
        for (JsonElement elem : array) {
            dataList.add(gson.fromJson(elem, clazz));
        }
        return dataList;
    }

    /**
     * 通过key删除某个键值
     *
     * @param key
     */
    public static void removebyKey(String key) {
        if (sp == null) {
            return;
        }
        sp.edit().remove(key).apply();
    }

    public static void clearAll() {
        if (sp == null) {
            return;
        }
        sp.edit().clear().apply();
    }
}
