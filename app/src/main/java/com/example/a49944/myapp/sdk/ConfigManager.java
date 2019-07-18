package com.example.a49944.myapp.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.a49944.myapp.bean.discover.JuHeBean;
import com.example.a49944.myapp.bean.study.WanAndroidBean;
import com.example.a49944.myapp.constant.ConfigConstant;
import com.example.a49944.myapp.net.hhnet.Configuration;
import com.example.a49944.myapp.net.hhnet.bean.ScheduleBean;

import java.util.List;

/**
 *
 */
public class ConfigManager {

    // 临时进出的crc校验码
    private String crcTempInoutRule;

    public void setCrcTempInoutRule(String crcTempInoutRule) {
        this.crcTempInoutRule = crcTempInoutRule;
        SPUtils.putValue(ConfigConstant.CRC_TEMP_INOUT_RULE, crcTempInoutRule);
    }

    public String getCrcTempInoutRule() {
        if (crcTempInoutRule == null) {
            crcTempInoutRule = (String) SPUtils.getValue(ConfigConstant.CRC_TEMP_INOUT_RULE, "");
        }
        return crcTempInoutRule;
    }

    /**
     * 保存登录信息
     */
    private Boolean isLogin;

    public void setLogin(Boolean isLogin) {
        this.isLogin = isLogin;
        SPUtils.putValue(ConfigConstant.HOME_LOGIN, isLogin);
    }

    /**
     * 保存聚合数据
     */
    private List<JuHeBean.ResultBean.DataBean> juheData;

    public void setDisTabList(List<JuHeBean.ResultBean.DataBean> topData, String key) {
        this.juheData = topData;
        SPUtils.setDataList(key, topData);
    }

    public List<JuHeBean.ResultBean.DataBean> getDisTabList(String key) {
        juheData = SPUtils.getDataList(key, JuHeBean.ResultBean.DataBean.class);
        return juheData;
    }

    /**
     * 保存“我的”历史观看数据
     */
    private List<JuHeBean.ResultBean.DataBean> historyData;
    public void setMeHistoryList(List<JuHeBean.ResultBean.DataBean> historyData, String key){
        this.historyData = historyData;
        SPUtils.setDataList(key, historyData);
    }
    public List<JuHeBean.ResultBean.DataBean> getHistoryData(String key){
        historyData = SPUtils.getDataList(key, JuHeBean.ResultBean.DataBean.class);
        return historyData;
    }


    /**
     * 保存WanAndroid数据
     */
    private List<WanAndroidBean.DataBean> mListBean;

    public void setWanAndroidList(List<WanAndroidBean.DataBean> listBean, String key) {
        this.mListBean = listBean;
        SPUtils.setDataList(key, listBean);
    }

    public List<WanAndroidBean.DataBean> getWanAndroidList(String key) {
        if (mListBean == null) {
            mListBean = SPUtils.getDataList(key, WanAndroidBean.DataBean.class);
        }

        return mListBean;
    }

    private List<WanAndroidBean.DataBean.ArticlesBean> studyData;

    public void setStudyTabList(List<WanAndroidBean.DataBean.ArticlesBean> studyData, String key) {
        this.studyData = studyData;
        SPUtils.setDataList(key, studyData);
    }

    public List<WanAndroidBean.DataBean.ArticlesBean> getStudyTabList(String key) {
        studyData = SPUtils.getDataList(key, WanAndroidBean.DataBean.ArticlesBean.class);
        return studyData;
    }

    /**
     * 保存用户登录的后得到的token值
     */
    private String mLoginToken;

    public void setLoginToken(String token) {
        mLoginToken = token;
        SPUtils.putValue(ConfigConstant.LOGIN_TOKEN, token);
    }

    public String getLoginToken() {
        if (mLoginToken == null) {
            mLoginToken = (String) SPUtils.getValue(ConfigConstant.LOGIN_TOKEN, "");
        }
        return mLoginToken;
    }
    private String mPhotoPath;
    public void setPhotoPath(String path){
        mPhotoPath = path;
        SPUtils.putValue(ConfigConstant.PHOTO_PATH, path);
    }
    public String getPhotoPath(){
        if (mPhotoPath == null){
            mPhotoPath = (String) SPUtils.getValue(ConfigConstant.PHOTO_PATH, "");
        }
        return mPhotoPath;
    }

    /**
     * 保存课表选择的学年
     */
    private String mScheduleTerm;
    public void setScheduleTerm(String term){
        mScheduleTerm = term;
        SPUtils.putValue(ConfigConstant.SCHEDULE_LAST_TERM, term);
    }
    public String getScheduleTerm(){
        if (mScheduleTerm == null){
            mScheduleTerm = (String) SPUtils.getValue(ConfigConstant.SCHEDULE_LAST_TERM, "");
        }
        return mScheduleTerm;
    }

    /**
     * 保存考试安排选择学期
     */
    private String mExamTerm;
    public void setExamTerm(String term){
        mExamTerm = term;
        SPUtils.putValue(ConfigConstant.EXAM_LAST_TERM, term);
    }
    public String getExamTerm(){
        if (mExamTerm == null){
            mExamTerm = (String) SPUtils.getValue(ConfigConstant.EXAM_LAST_TERM, "");
        }
        return mExamTerm;
    }

    /**
     * 保存课表选择的周数
     */
    private String mScheduleWeek;
    public void setScheduleWeek(String week){
        mScheduleWeek = week;
        SPUtils.putValue(ConfigConstant.SCHEDULE_LAST_WEEK, week);
    }
    public String getScheduleWeek(){
        if (mScheduleWeek == null){
            mScheduleWeek = (String) SPUtils.getValue(ConfigConstant.SCHEDULE_LAST_WEEK, "");
        }
        return mScheduleWeek;
    }

    /**
     * 保存请求到课表的数据集合
     */
    private List<ScheduleBean.RowsBean> mScheduleList;
    public void setScheduleList(List<ScheduleBean.RowsBean> scheduleList,String key){
        mScheduleList = scheduleList;
        SPUtils.setDataList(key, scheduleList);
    }
    public List<ScheduleBean.RowsBean> getScheduleList(String key){
        mScheduleList = SPUtils.getDataList(key, ScheduleBean.RowsBean.class);
        return mScheduleList;
    }



    /**
     * 保存登录状态
     *
     * @param stuNumber
     * @param stuPassword
     * @param isSelect
     */
    public void setSaveLoginState(String stuNumber, String stuPassword, boolean isSelect) {
        SharedPreferences sp = AppUtils.getAppContext().getSharedPreferences(ConfigConstant.USER_INFO, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(ConfigConstant.USER_STUNUMBER, stuNumber);
        edit.putString(ConfigConstant.USER_STUPASSWORD, stuPassword);
        edit.putBoolean(ConfigConstant.USER_ISSELECT, isSelect);
        edit.commit();
    }

    /**
     * 得到保存用户登录状态的 sp
     * @return
     */
    public SharedPreferences getUserInfoSP() {
        SharedPreferences sp = AppUtils.getAppContext().getSharedPreferences(ConfigConstant.USER_INFO, Context.MODE_PRIVATE);
        return sp;
    }

    /**
     * 保存登录后的学号
     */
    private String mStrNumber;
    public void setStrNumber(String strNumber){
        this.mStrNumber = strNumber;
        SPUtils.putValue(ConfigConstant.STR_STUNUMBER, strNumber);
    }
    public String getStrNumber(){
        if (mStrNumber == null){
            mStrNumber = (String) SPUtils.getValue(ConfigConstant.STR_STUNUMBER, "");
        }
        return mStrNumber;
    }



    // 静态内部类的单例模式
    private ConfigManager() {
    }

    private static class ConfigManagerHolder {
        private static final ConfigManager INSTANCE = new ConfigManager();
    }

    public static ConfigManager getInstance() {
        return ConfigManagerHolder.INSTANCE;
    }
}
