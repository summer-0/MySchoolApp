package com.example.a49944.myapp.sdk;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;

import java.io.File;
import java.util.concurrent.ExecutorService;

/**
 * Created by unengchan on 2018/2/9.
 * app辅助类
 */

public class AppUtils {

    public static double mPressedTime;

    private AppUtils() {
    }

    /**
     * 获取应用上下文
     *
     * @return 全局上下文
     */
    public static Context getAppContext() {
        return BaseApplication.getInstance().getAppContext();
    }

    /**
     * 获取hanlder
     *
     * @return android.os.Handler
     */
    public static Handler getHandler() {
        return BaseApplication.getInstance().getAppHandler();
    }

    /**
     * 获取线程池
     *
     * @return 缓冲线程池
     */
    public static ExecutorService getThreadPool() {
        return BaseApplication.getInstance().getAppThreadPool();
    }



    /**
     * 获取应用的包名
     *
     * @return 包名
     */
    public static String getPackageName() {
        String packageName = getAppContext().getPackageName();
        return packageName;
    }

    /**
     * 获取应用的版本名，例如1.1.1
     *
     * @return 版本名
     */
    public static String getVersionName() {
        String versionName = "1.0.00";
        PackageInfo packageInfo = getPackageInfo();
        if (packageInfo != null) {
            versionName = packageInfo.versionName;
        }
        return versionName;
    }

    /**
     * 获取应用的版本号，例如 1
     *
     * @return 版本号
     */
    public static int getVersionCode() {
        int versionCode = 0;
        PackageInfo packageInfo = getPackageInfo();
        if (packageInfo != null) {
            versionCode = packageInfo.versionCode;
        }
        return versionCode;
    }

    /**
     * 获取包信息
     *
     * @return 包信息实例
     */
    private static PackageInfo getPackageInfo() {
        PackageManager packageManager = getAppContext().getPackageManager();
        PackageInfo packageInfo = null;
        try {
            packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
//            LogUtils.d(AppUtils.class.getSimpleName(),e.getMessage());
        }
        return packageInfo;
    }


    /**
     * 安装apk文件，并启动
     */
    public static void installApk(String apkFilePath){
        File apkFile = new File(apkFilePath);
        if (!apkFile.exists()) {
            return;
        }

        // 通过Intent安装APK文件
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.setDataAndType(Uri.parse("file://" + apkFile.toString()),
                "application/vnd.android.package-archive");
        getAppContext().startActivity(i);

        android.os.Process.killProcess(android.os.Process.myPid());
//        如果没有android.os.Process.killProcess(android.os.Process.myPid());最后不会提示完成、打开。
//        如果没有i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);这一步的话，最后安装好了，点打开，是不会打开新版本应用的。
    }




    private static String TAG="*********-"+AppUtils.class.getSimpleName() +"-*************";

}
