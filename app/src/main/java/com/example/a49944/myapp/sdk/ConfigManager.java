package com.example.a49944.myapp.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.a49944.myapp.bean.discover.JuHeBean;
import com.example.a49944.myapp.bean.study.WanAndroidBean;
import com.example.a49944.myapp.constant.ConfigConstant;

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
