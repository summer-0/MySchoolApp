package com.example.a49944.myapp.net;

import com.example.a49944.myapp.bean.TestBean;
import com.example.a49944.myapp.bean.discover.JuHeBean;
import com.example.a49944.myapp.bean.discover.TopBean;
import com.example.a49944.myapp.bean.json.StudyNavi;
import com.example.a49944.myapp.bean.study.WanAndroidBean;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

/**
 * Created by summer_h on 2019/4/16 11:35
 */
public interface ApiRetrofit {
    /** WanAndroid导航数据
     * https://www.wanandroid.com/navi/json
     * 方法：GET
     * 参数：无
     */
    @GET("navi/json")
    Observable<WanAndroidBean> getStudyNaviData();
    @GET("navi/json")
    Call<WanAndroidBean> getStudyData();

    /** 头条日榜榜单TOP100
     *  请求地址：https://api.newrank.cn/api/sync/toutiao/rank/day/top100
     *  请求方式：POST
     *  HTTP Header
     *      Content-Type  application/x-www-form-urlencoded;charset=utf-8
     *      Key  你在控制台获取的Key : 144b4b727f5d4a39af1128964
     * @return
     */
    @FormUrlEncoded
    @POST("rank/day/top100")
    Observable<ResponseBody> getTouTiaoTop(@Field("type")String type, @Field("date")String date, @Field("page") int page, @Field("size") int size);

    /** 新闻头条
     *  接口地址：http://v.juhe.cn/toutiao/index
     * 返回格式：json
     * 请求方式：get/post
     * 请求示例：http://v.juhe.cn/toutiao/index?type=top&key=APPKEY
     * appkey: 6badfa456fb120860cfd3c92d348ae81
     * 	类型,,top(头条，默认),shehui(社会),guonei(国内),guoji(国际),yule(娱乐),tiyu(体育)junshi(军事),keji(科技),caijing(财经),shishang(时尚)
     * @return
     */
    @GET("index")
    Observable<JuHeBean> getJuHe(@Query("type")String type, @Query("key")String key);

    @GET("index")
    Call<ResponseBody> getRetrofitJuHe(@Query("type")String type, @Query("key")String key);
    @GET("index")
    Call<JuHeBean> getJuHeRetrofitTest(@Query("type")String type, @Query("key")String key);

    /**
     * 娱乐类 - BA10TA81wangning
     *      电视 - BD2A86BEwangning
     *      电影 - BD2A9LEIwangning
     *      明星 - BD2AB5L9wangning
     *      音乐 - BD2AC4LMwangning
     * 体育类 - BA8E6OEOwangning
     * 财经类 - BA8EE5GMwangning
     * 军事类 - BAI67OGGwangning
     *      军情 - DE0CGUSJwangning
     * 健康类 - BDC4QSV3wangning
     */
    /** 娱乐类 - BA10TA81wangning
     * https://3g.163.com/touch/reconstruct/article/list/BA10TA81wangning/0-2.html
     */
    @GET("BA10TA81wangning/0-2.html")
    Call<TestBean> getYuLe();


    /** 阿凡达数据
     *  1. 科技
     *      AppKey： 373c07018e8d43d8aac027ec53674017
     *      接口地址：http://api.avatardata.cn/TechNews/Query
     *      返回格式：JSON/XML
     *      请求方式：GET/POST
     *      请求示例： http://api.avatardata.cn/TechNews/Query?key=373c07018e8d43d8aac027ec53674017&page=1&rows=10
     *      	key	String	是	应用APPKEY
     *  	    page	Int	是	请求页数，默认page=1
     *  	    rows	Int	是	返回记录条数，默认rows=20,最大50
     *  	    dtype	String	否	返回结果格式：可选JSON/XML，默认为JSON
     *  	    format	Boolean	否	当返回结果格式为JSON时，是否对其进行格式化，为了节省流量默认为false，测试时您可以传入true来熟悉返回内容
     *
     * 2. 国内
     *      AppKey： a2b210d8e8224722aed43a2c93027a73
     *      接口地址：http://api.avatardata.cn/GuoNeiNews/Query
     *      返回格式：JSON/XML
     *      请求方式：GET/POST
     *      请求示例： http://api.avatardata.cn/GuoNeiNews/Query?key=a2b210d8e8224722aed43a2c93027a73&page=1&rows=10
     *      	key	String	是	应用APPKEY
     *  	    page	Int	否	请求页数，默认page=1
     *  	    rows	Int	否	返回记录条数，默认rows=20,最大50
     *  	    dtype	String	否	返回结果格式：可选JSON/XML，默认为JSON
     *  	    format	Boolean	否	当返回结果格式为JSON时，是否对其进行格式化，为了节省流量默认为false，测试时您可以传入true来熟悉返回内容
     *
     *  3.国际
     *      AppKey： 9f77b24466b44a49b52989c72f557024
     *      接口地址：http://api.avatardata.cn/WorldNews/Query
     *      返回格式：JSON/XML
     *      请求方式：GET/POST
     *      请求示例： http://api.avatardata.cn/WorldNews/Query?key=9f77b24466b44a49b52989c72f557024&page=1&rows=10
     *      	key	String	是	应用APPKEY
     *  	    rows	Int	是	返回记录条数，默认rows=20,最大50
     *  	    page	Int	是	请求页数，默认page=1
     *  	    dtype	String	否	返回结果格式：可选JSON/XML，默认为JSON
     *  	    format	Boolean	否	当返回结果格式为JSON时，是否对其进行格式化，为了节省流量默认为false，测试时您可以传入true来熟悉返回内容
     */
    @GET("Query")
    Call<ResponseBody> requestGuoNei(@Query("key") String key, @Query("page") int page, @Query("rows") int rows, @Query("dtype")String dtype, @Query("format") boolean format);
}
