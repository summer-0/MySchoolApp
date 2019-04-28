package com.example.a49944.myapp.sdk;

import com.example.a49944.myapp.bean.discover.JuHeBean;
import com.example.a49944.myapp.bean.study.WanAndroidBean;
import com.example.a49944.myapp.constant.ConfigConstant;

import java.util.List;

/**
 * Created by unengchan on 2018/5/4
 * 配置管理器
 */
public class ConfigManager {

    // 临时进出的crc校验码
    private String crcTempInoutRule;
    public void setCrcTempInoutRule(String crcTempInoutRule) {
        this.crcTempInoutRule = crcTempInoutRule;
        SPUtils.putValue(ConfigConstant.CRC_TEMP_INOUT_RULE,crcTempInoutRule);
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
    public void setLogin(Boolean isLogin){
      this.isLogin = isLogin;
      SPUtils.putValue(ConfigConstant.HOME_LOGIN, isLogin);
    }

    /**
     * 保存聚合数据
     */
    private List<JuHeBean.ResultBean.DataBean> juheData;
    public void setDisTabList(List<JuHeBean.ResultBean.DataBean> topData, String key){
        this.juheData = topData;
        SPUtils.setDataList(key, topData);
    }
    public List<JuHeBean.ResultBean.DataBean> getDisTabList(String key){
        juheData = SPUtils.getDataList(key, JuHeBean.ResultBean.DataBean.class);
        return juheData;
    }

    /**
     * 保存WanAndroid数据
     */
    private List<WanAndroidBean.DataBean> mListBean;
    public void setWanAndroidList(List<WanAndroidBean.DataBean> listBean, String key){
        this.mListBean = listBean;
        SPUtils.setDataList(key, listBean);
    }
    public List<WanAndroidBean.DataBean> getWanAndroidList(String key){
        if (mListBean == null){
            mListBean = SPUtils.getDataList(key, WanAndroidBean.DataBean.class);
        }

        return mListBean;
    }



    private List<WanAndroidBean.DataBean.ArticlesBean> studyData;
    public void setStudyTabList(List<WanAndroidBean.DataBean.ArticlesBean> studyData, String key){
        this.studyData = studyData;
        SPUtils.setDataList(key, studyData);
    }
    public List<WanAndroidBean.DataBean.ArticlesBean> getStudyTabList(String key){
        studyData = SPUtils.getDataList(key, WanAndroidBean.DataBean.ArticlesBean.class);
        return studyData;
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
