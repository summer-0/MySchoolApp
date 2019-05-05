package com.example.a49944.myapp.bean.mine;

import io.realm.RealmObject;

import java.io.Serializable;

/**
 * Created by summer_h
 * Time: 2019/5/5 10:50
 * Des:
 */
public class HistoryData extends RealmObject implements Serializable , Cloneable{
    private static final long serialVersionUID = 5476113151472589265L;
    /**
     * uniquekey : b62d95b13f0d8070d42fd26dea2cf566
     * title : 海底电缆+氢能源汽车+低价=德威新材下周龙头
     * date : 2019-04-20 12:14
     * category : 财经
     * author_name : 游资传奇
     * url : http://mini.eastday.com/mobile/190420121454713.html
     * thumbnail_pic_s : http://09imgmini.eastday.com/mobile/20190420/20190420121454_82e0c9c3b891233bae25b01f0633c9fb_2_mwpm_03200403.jpg
     * thumbnail_pic_s02 : http://09imgmini.eastday.com/mobile/20190420/20190420121454_82e0c9c3b891233bae25b01f0633c9fb_1_mwpm_03200403.jpg
     * thumbnail_pic_s03 : http://09imgmini.eastday.com/mobile/20190420/20190420121454_82e0c9c3b891233bae25b01f0633c9fb_3_mwpm_03200403.jpg
     */

    private String uniquekey;
    private String title;
    private String date;
    private String category;
    private String author_name;
    private String url;
    private String thumbnail_pic_s;
    private String thumbnail_pic_s02;
    private String thumbnail_pic_s03;

    public String getUniquekey() {
        return uniquekey;
    }

    public void setUniquekey(String uniquekey) {
        this.uniquekey = uniquekey;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnail_pic_s() {
        return thumbnail_pic_s;
    }

    public void setThumbnail_pic_s(String thumbnail_pic_s) {
        this.thumbnail_pic_s = thumbnail_pic_s;
    }

    public String getThumbnail_pic_s02() {
        return thumbnail_pic_s02;
    }

    public void setThumbnail_pic_s02(String thumbnail_pic_s02) {
        this.thumbnail_pic_s02 = thumbnail_pic_s02;
    }

    public String getThumbnail_pic_s03() {
        return thumbnail_pic_s03;
    }

    public void setThumbnail_pic_s03(String thumbnail_pic_s03) {
        this.thumbnail_pic_s03 = thumbnail_pic_s03;
    }
}
