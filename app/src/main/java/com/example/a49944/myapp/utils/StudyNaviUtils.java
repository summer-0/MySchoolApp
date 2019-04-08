package com.example.a49944.myapp.utils;

import com.example.a49944.myapp.bean.json.NaviArticle;
import com.example.a49944.myapp.bean.json.NaviBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudyNaviUtils {
    private static final int CID_01 = 272; //常用网站
    private static final int CID_02 = 280; //开发社区
    private static final int CID_03 = 276; //在线学习
    private static final int CID_04 = 277; //开发平台
    private static final int CID_05 = 274; //个人博客
    private static final int CID_06 = 275; //常用工具
    private static final int CID_07 = 281; //公司博客
    private static final int CID_08 = 393; //查看源码
    private static final int CID_09 = 278; //互联网资讯(278) + 应用加固(282)
    private static final int CID_10 = 279; //求职招聘
    private static final int CID_11 = 290; // 后端云(290) Bug管理(289) IM即时通讯(288) 直播SDK(287) 地图平台(286) 推送平台(284)
    private static final int CID_12 = 299; //创意&素材
    private static final int CID_13 = 300; //互联网统计
    private static final int CID_14 = 301; //快速开发(301) 应用发布(359) 反馈平台(365) 在线文档(366) 短视频SDK(369) Flutter(430) WebView内核(291)

    private static final String STR_CID_01 = "Google开发者 Github stackoverflow 掘金 CSDN 简书 开发技术周报 开发者头条 segmentfault androiddevtools Google中文Blog 干货集中营 CodeKK 小专栏 国内大牛 国外大牛 Android源码 MaterialDesign中文版 leetcode googlemvn仓库 jcenter仓库 maven仓库";
    private static final String STR_CID_02 = "掘金 开源中国 ApkBus 中国谷歌开发者社区 CSDN v2ex 51ctoAndroid论坛";
    private static final String STR_CID_03 = "慕课网 网易云课程 极客学院 腾讯课堂 麦子学院 人工智能 牛客网 老罗视频教程 mars视频 gitchat免费区";
    private static final String STR_CID_04 = "DevStore bmob后端云 fir.im 融云 环信 讯飞开放平台 Face++ 七牛云 蒲公英 bugly 百度云推送 聚合数据 小米推送 极光推送 涂图 腾讯信鸽 UMeng";
    private static final String STR_CID_05 = "罗升阳 邓凡平 郭霖 鸿洋 AigeStudio 亓斌 徐医生 阮一峰 MrSimp1e 兰亭风雨 胡凯 技术小黑屋 任玉刚 张涛 Weishu gityuan GcsSloop 尼古拉斯.赵四 hencoder 叫我旺仔 一口仨馍 _StriveG 博客 Carson_Ho 亦枫 prototypez Gracker_Gao的个人博客 稀有猿诉 Eateeer的笔记屋 AndroidBlog周刊";
    private static final String STR_CID_06 = "JSON格式化 md5&sha base64 二维码 取色器 进制转化 iconfont tinypng 在线翻译 pdf派文档互转 视频转gif,gif优化等 图片背景透明";
    private static final String STR_CID_07 = "美团点评 悦跑圈技术团队 网易考拉移动端团队 有赞技术团队";
    private static final String STR_CID_08 = "androidxref androidos源码站";
    private static final String STR_CID_09 = "ReadHub 创业邦 36kr 品玩 少数派 泡面小镇 360加固 网易云易盾 爱加密";
    private static final String STR_CID_10 = "智联 拉钩 100offer 内推网 Boss直聘";
    private static final String STR_CID_11 = "bmob后端云 LeanCloud 腾讯Bugly 环信 百川云旺 融云 又拍云UPLive 七牛直播云 腾讯直播LVB 百度音视频LSS 高德地图 百度地图 腾讯地图 小米推送 极光推送 华为推送 百度云推送";
    private static final String STR_CID_12 = "暴走漫画制作器 素材设计 无版权素材站 iconfont iconstore MaterialDesign设计模板与素材 awesome-design lottie素材库";
    private static final String STR_CID_13 = "猎豹大数据 百度移动统计";
    private static final String STR_CID_14 = "QMUIAndroid 通用IM_UI组件 360开发者 一站式解决用户反馈问题 腾讯文档 阿里文档语雀 又拍云短视频 Flutter开发者 腾讯x5";



    private List<String> mLinks = new ArrayList<>();
    private List<String> mTitles = new ArrayList<>();
    public static List<String> getTitles(int cid, String type){
        List<String>  list = new ArrayList<>();
        switch (cid){
            case CID_01:
                String[] s1 = STR_CID_01.split(" ");
                list = Arrays.asList(s1); //常用网站
                break;
            case CID_02:
                String[] s2 = STR_CID_02.split(" ");
                list = Arrays.asList(s2); //开发社区
                break;
            case CID_03:
                String[] s3 = STR_CID_03.split(" ");
                list = Arrays.asList(s3); //在线学习
                break;
            case CID_04:
                String[] s4 = STR_CID_04.split(" ");
                list = Arrays.asList(s4); //开发平台
                break;
            case CID_05:
                String[] s5 = STR_CID_05.split(" ");
                list = Arrays.asList(s5); //个人博客
                break;
            case CID_06:
                String[] s6 = STR_CID_06.split(" ");
                list = Arrays.asList(s6); //常用工具
                break;
            case CID_07:
                String[] s7 = STR_CID_07.split(" ");
                list = Arrays.asList(s7); //公司博客
                break;
            case CID_08:
                String[] s8 = STR_CID_08.split(" ");
                list = Arrays.asList(s8); //查看源码
                break;
            case CID_09:
                String[] s9 = STR_CID_09.split(" ");
                list = Arrays.asList(s9); //互联网资讯 + 应用加固
                break;
            case CID_10:
                String[] s10 = STR_CID_10.split(" ");
                list = Arrays.asList(s10); //求职招聘
            case CID_11:
                String[] s11 = STR_CID_11.split(" ");
                list = Arrays.asList(s11); //后端云 Bug管理 IM即时通讯 直播SDK 地图平台 推送平台
                break;
            case CID_12:
                String[] s12 = STR_CID_12.split(" ");
                list = Arrays.asList(s12); //创意素材
                break;
            case CID_13:
                String[] s13 = STR_CID_13.split(" ");
                list = Arrays.asList(s13); //互联网统计
                break;
            case CID_14:
                String[] s14 = STR_CID_14.split(" ");
                list = Arrays.asList(s14); //快速开发 应用发布 反馈平台 在线文档 短视频SDK Flutter WebView内核
                break;
        }
        return list;
    }
}
