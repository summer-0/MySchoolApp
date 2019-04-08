package com.example.a49944.myapp.ui.fragment;

import android.support.v4.app.Fragment;

import java.util.HashMap;

public class FragmentManagerWrapper {
    //单例模式
    private static volatile FragmentManagerWrapper mInstance = null;
    public static FragmentManagerWrapper getInstance(){
        if (mInstance == null){
            synchronized (FragmentManagerWrapper.class){
                if (mInstance ==  null){ //双重锁
                    mInstance = new FragmentManagerWrapper();
                }
            }
        }
        return mInstance;
    }
    private HashMap<String, Fragment> mHashMap = new HashMap<>(); // String: fragment的类名
    public Fragment createFragment(Class<?> clazz){
        return createFragment(clazz, true);
    }
    public Fragment createFragment(Class<?> clazz, boolean isObtain){
        Fragment fragment = null;
        String className = clazz.getName(); //得到类名
        //判断Map中是否存有此fragment
        if (mHashMap.containsKey(className)){ // 若存在
            fragment = mHashMap.get(className);
        }else {  // 不存在
            try {
                fragment = (Fragment) Class.forName(className).newInstance(); //实例化fragment
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (isObtain){
            mHashMap.put(className, fragment);
        }
        return fragment;
    }
}
