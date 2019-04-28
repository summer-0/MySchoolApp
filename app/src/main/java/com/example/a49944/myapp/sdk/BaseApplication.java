package com.example.a49944.myapp.sdk;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * Created by unengchan on 2018/2/9.
 * 主动创建的appication
 */

public class BaseApplication extends Application {

    public static BaseApplication mApp; // 整个应用的实例对象
    public Context mAppContext;  // 全局应用上下文
    public Handler mAppHandler;  // 整个应用的handler
    public ThreadPoolExecutor mAppThreadPool;  // 应用线程池

    @Override
    public void onCreate() {
        super.onCreate();
        mAppContext = getApplicationContext();
        mApp = this;
        // 崩溃日志记录和重启应用
//        CrashHandler.getInstance().init(mAppContext);
        mAppHandler = new Handler();
        initThreadPool();
    }

    /**
     * 初始化线程池
     */
    private void initThreadPool() {
        // 根据CPU的核心数获取最佳核心线程数
        int corePoolSize = Runtime.getRuntime().availableProcessors() *2+1;
        // 线程池的最大线程数
        int maxPoolSize = corePoolSize * 2;
        LinkedBlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>();
        // 拒绝策列
        ThreadPoolExecutor.CallerRunsPolicy handler = new ThreadPoolExecutor.CallerRunsPolicy();
        mAppThreadPool = new ThreadPoolExecutor(corePoolSize, maxPoolSize,
                10, TimeUnit.SECONDS, workQueue,
                Executors.defaultThreadFactory(), handler);

        // 允许核心线程池超时回收
        mAppThreadPool.allowCoreThreadTimeOut(true);
    }

    /**
     * 获取app对象,本身是单例的
     *
     * @return
     */
    public static BaseApplication getInstance() {
        return mApp;
    }

    public Context getAppContext() {
        return mAppContext;
    }

    public Handler getAppHandler() {
        return mAppHandler;
    }

    public ExecutorService getAppThreadPool() {
        return mAppThreadPool;
    }
}
