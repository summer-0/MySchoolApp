package com.example.a49944.myapp.net;

import com.example.a49944.myapp.bean.TestBean;
import com.example.a49944.myapp.bean.discover.JuHeBean;
import com.example.a49944.myapp.bean.json.StudyNavi;
import com.example.a49944.myapp.bean.study.WanAndroidBean;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by summer_h on 2019/4/16 11:48
 */
public class NetRequestManager {
    /**
     * 单例模式
     */
    private static ApiRetrofit mApiRetrofit;
    private static Retrofit mRetrofit;
    private static volatile NetRequestManager instance = null;
    private NetRequestManager(){} //构造方法私有化
    public static NetRequestManager create(String headUrl){
        if (instance == null){
            synchronized (NetRequestManager.class){
                if (instance == null){
                    instance = new NetRequestManager();
                }
            }
        }
        mRetrofit = new Retrofit.Builder()
                .baseUrl(headUrl)             //url主地址
                .addConverterFactory(GsonConverterFactory.create()) //添加Gson转换器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // retrofit需要添加rxjava适配器
                .build();
        mApiRetrofit = mRetrofit.create(ApiRetrofit.class);
        return instance;
    }

    /**
     * retrofit_ rxjava请求Study的navi数据
     * @return  observable对象，含有StudyNavi数据对象
     */
    public Observable<WanAndroidBean> requestStudyNaviData(){
        return  mApiRetrofit.getStudyNaviData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public Call<WanAndroidBean> requestStudyData(){
        return  mApiRetrofit.getStudyData();
    }

    /**
     * 获取聚合新闻的头条模块
     * @return
     */
    public Observable<JuHeBean> requestJuHeData(String type){
        return  mApiRetrofit.getJuHe(type, HttpConstant.APP_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }




    public Call<ResponseBody> requestJuHeDataWithRetrofit(String type){
        Call<ResponseBody> call = mApiRetrofit.getRetrofitJuHe(type, HttpConstant.APP_KEY);
       //http://v.juhe.cn/toutiao/index?type=top&key=6badfa456fb120860cfd3c92d348ae81
        return call;
    }
    public Call<JuHeBean> requestJuHeRetrofitTest(String type){
        Call<JuHeBean> call = mApiRetrofit.getJuHeRetrofitTest(type, HttpConstant.APP_KEY);
        return call;
    }
    /**
     * 测试数据
     */
   public Call<TestBean> requestYuLeData(){
       Call<TestBean> call = mApiRetrofit.getYuLe();
       return call;
   }

}
