package com.example.a49944.myapp.net.hhnet;

import com.example.a49944.myapp.net.hhnet.bean.GradeBean;
import com.example.a49944.myapp.net.hhnet.bean.LoginBean;
import com.example.a49944.myapp.net.hhnet.bean.ScheduleBean;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Author： Hndroid
 * Email : 2282250500@qq.com
 * Date： 18-4-13
 * Description： 构造网络 API 的接口
 */
public interface ApiService {

    //初始化校园网站方便获取 cookie
    @GET("/")
    Call<String> initCookie();

    /**
     * 登录
     *
     * @param stuNumber
     * @param stuPassword
     * @param verifyCode
     * @return
     */
    @FormUrlEncoded
    @POST("new/login")
    Call<LoginBean> login(@Field("account") String stuNumber, @Field("pwd") String stuPassword, @Field("verifycode") String verifyCode);

    /**
     * 查询登录的用户信息
     *
     * @return
     */
    @GET("login!welcome.action")
    Call<String> inquireLoginUser();

    /**
     * 查询课表
     *
     * @return
     */
    @FormUrlEncoded
    @POST("xsgrkbcx!getDataList.action?order=asc&page=1&rows=500&sort=kxh")
    Call<ScheduleBean> inquireSchedule(@Field("xnxqdm") String yearMonth, @Field("zc") String week);

    /**
     * http://202.192.240.29/xsgrkbcx!getKbRq.action?xnxqdm=201702&zc=5
     *   @POST("xsgrkbcx!getKbRq.action")
     * @param yearMonth
     * @param week
     * @return
     */
    @FormUrlEncoded
    @POST("xsgrkbcx!getDataList.action?order=asc&page=1&rows=500&sort=kxh")
    Call<ResponseBody> inquireScheduleWeek(@Field("xnxqdm") String yearMonth, @Field("zc") String week);


    /**
     * 查询课程成绩
     * @param dateYear
     * @return
     */
    @FormUrlEncoded
    @POST("xskccjxx!getDataList.action?order=asc&page=1&rows=40&sort=xnxqdm")
    Call<GradeBean> inquireGrade(@Field("xnxqdm") String dateYear);
}
