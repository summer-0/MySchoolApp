package com.example.a49944.myapp.net.grade;

/**
 * Author： Hndroid
 * Email : 2282250500@qq.com
 * Date： 18-5-4
 * Description：用于 EventBus 的事件类（返回年份）
 */
public class InquireGradeYearEvent {

    private static String date; //成绩

    public static void setDate(String date) {
        InquireGradeYearEvent.date = date;
    }

    public static String getDate() {
        return date;
    }

}
