package com.example.a49944.myapp.net.hhnet;

/**
 * Author： Hndroid
 * Email : 2282250500@qq.com
 * Date： 18-4-17
 * Description： 用户管理类
 */
public class UserManagement {
    /**
     * 全局登录状态
     */
    private static boolean IS_LOGIN = false;
    //全局 Cookie
    private static String cookie;

    public static void setCookie(String cookie) {
        UserManagement.cookie = cookie;
    }

    public static String getCookie() {
        return cookie;
    }

    public static void setIsLogin(boolean isLogin) {
        IS_LOGIN = isLogin;
    }

    public static boolean isIsLogin() {
        return IS_LOGIN;
    }
}
