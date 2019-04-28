package com.example.a49944.myapp.sdk;

import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/**
 * Created by unengchan on 2018/2/9.
 * 空对象辅助类
 */

public class EmptyUtils {

    private EmptyUtils(){}

    /**
     * 判断对象是否为空
     * @param obj
     * @return
     */
    public static boolean isEmpty(Object obj){
        if (obj == null) {
            return true;
        }
        // string字符串
        if (obj instanceof String && ((String) obj).trim().length() == 0) {
            return true;
        }
        // 集合
        if (obj instanceof Collection && ((Collection) obj).isEmpty()) {
            return true;
        }
        if (obj instanceof Map && ((Map) obj).isEmpty()) {
            return true;
        }
        //数组
        if (obj.getClass().isArray() && Array.getLength(obj) == 0) {
            return true;
        }
        // 不常用
        if (obj instanceof SparseArray && ((SparseArray) obj).size() == 0) {
            return true;
        }
        if (obj instanceof SparseBooleanArray && ((SparseBooleanArray) obj).size() == 0) {
            return true;
        }
        if (obj instanceof SparseIntArray && ((SparseIntArray) obj).size() == 0) {
            return true;
        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
//            if (obj instanceof SparseLongArray && ((SparseLongArray) obj).size() == 0) {
//                return true;
//            }
//        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//            if (obj instanceof android.util.LongSparseArray
//                    && ((android.util.LongSparseArray) obj).size() == 0) {
//                return true;
//            }
//        }
//        if (obj instanceof LongSparseArray && ((LongSparseArray) obj).size() == 0) {
//            return true;
//        }
        return false;
    }

    /**
     * 判断是否为空
     * @param obj
     * @return
     */
    public static boolean isNotEmpty(Object obj){
        return !isEmpty(obj);
    }
}
