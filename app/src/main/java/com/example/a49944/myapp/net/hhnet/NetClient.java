package com.example.a49944.myapp.net.hhnet;

import com.example.a49944.myapp.constant.AppConstant;
import okhttp3.*;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * Author： Hndroid
 * Email : 2282250500@qq.com
 * Date： 18-4-13
 * Description： 利用 retrofit 对网络请求进行封装
 */
public class NetClient {

    private NetClient(){}

    /**
     * 封装好的向外界提供发起网络请求接口
     *
     * @return
     */
    public static ApiService getApiService() {
        return ApiServiceHolder.API_SERVICE;
    }

    /**
     * 注意使用（当响应体的格式非 Gson 时使用）
     * @return
     */
    public static ApiService getApiServiceForScalas() {
        return ApiServiceHolderForScalars.API_SERVICE;
    }


    //cookie存储
    private static final ConcurrentHashMap<String, List<Cookie>> COOKIE_STORE = new ConcurrentHashMap<>();

    /**
     * 构建OkHttp
     */
    private static final class OKHttpHolder {
        private static final int TIME_OUT = 60;
        private static final OkHttpClient.Builder BUILDER = new OkHttpClient.Builder();
        // TODO: 18-4-16 添加拦截的规则（需进一步封装）< 可以利用单列模式从外界获取规则 >
        private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();

        private static OkHttpClient.Builder addInterceptor() {
            if (INTERCEPTORS != null && !INTERCEPTORS.isEmpty()) {
                for (Interceptor interceptor : INTERCEPTORS) {
                    BUILDER.addInterceptor(interceptor);
                }
            }
            return BUILDER;
        }

        private static final OkHttpClient OK_HTTP_CLIENT = addInterceptor()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .cookieJar(new MCookieJar())
                .build();
    }

    /**
     * 构建全局Retrofit客户端
     */
    private static final class RetrofitHolder {

        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(AppConstant.BASE_URL)
                .client(OKHttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * 构建全局retofit客户端（A converter for strings and both primitives and their boxed types to text/plain bodies.）
     */
    private static final class RetofitHoldlerForScalars {
        private static final Retrofit RETROFIT_CLIENT_SCALAS = new Retrofit.Builder()
                .baseUrl(AppConstant.BASE_URL)
                .client(OKHttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    /**
     * 单列模式构建 Service 接口
     */
    private static final class ApiServiceHolder {
        private static final ApiService API_SERVICE =
                RetrofitHolder.RETROFIT_CLIENT.create(ApiService.class);
    }

    /**
     * 单列模式构建 Service 接口(A converter for strings and both primitives and their boxed types to text/plain bodies.)
     */
    private static final class ApiServiceHolderForScalars {
        private static final ApiService API_SERVICE =
                RetofitHoldlerForScalars.RETROFIT_CLIENT_SCALAS.create(ApiService.class);
    }

    /**
     * 做 cookie 传递，保存等操作
     */
    private static final class MCookieJar implements CookieJar {

        @Override
        public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
            if (url.host().contentEquals("202.192.240.29")){
                UserManagement.setCookie(cookies.get(0).value());
            }
            //可以做保存 cookies 的操作
            COOKIE_STORE.put(url.host(), cookies);
        }

        @Override
        public List<Cookie> loadForRequest(HttpUrl url) {
            //加载新的 Cookie
            List<Cookie> cookies = COOKIE_STORE.get(url.host());
            /*if (url.host().contentEquals("202.192.240.29") && cookies != null){
                MLogger.d(cookies.get(0).value());
                CookieMessage message = new CookieMessage();
                message.setCookie(cookies.get(0).value());
                EventBus.getDefault().post(message);
            }*/
            return cookies != null ? cookies : new ArrayList<Cookie>();
        }
    }

}
