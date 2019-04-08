package com.example.a49944.myapp.utils;

import android.util.Log;

public class LogUtils {
    //Log开关控制
    public static boolean isDebug = true;
    /**
     * i级别的log输出
     * @param tag
     * @param msg
     */
    public static void i(String tag, String msg){
        if (isDebug){
            Log.i(tag, msg);
        }
    }

    /**
     * i级别的log输出 (类名信息)
     * @param obj
     * @param msg
     */
    public static void i(Object obj, String msg){
        if (isDebug){
            Log.i(obj.getClass().getSimpleName(), msg);
        }
    }

    /**
     * e级别的log输出
     * @param tag
     * @param msg
     */
    public static void e(String tag, String msg){
        if (isDebug){
            Log.e(tag, msg);
        }
    }
    /**
     * e级别的log输出 (类名信息)
     * @param obj
     * @param msg
     */
    public static void e(Object obj, String msg){
        if (isDebug){
            Log.e(obj.getClass().getSimpleName(), msg);
        }
    }
}
