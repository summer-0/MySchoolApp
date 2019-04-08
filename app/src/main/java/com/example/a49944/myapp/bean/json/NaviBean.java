package com.example.a49944.myapp.bean.json;

import java.io.Serializable;
import java.util.List;
/*
data下
 */
public class NaviBean implements Serializable {
    private static final long serialVersionUID = -8779772898096650813L;
    private String name; //常用网站
    private int cid; // 272
    private List<NaviArticle> articles;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public List<NaviArticle> getArticles() {
        return articles;
    }

    public void setArticles(List<NaviArticle> articles) {
        this.articles = articles;
    }
}
/*
articles: [
          {
            apkLink: "",
            author: "小编",
            chapterId: 272,
            chapterName: "常用网站",
            collect: false,
            courseId: 13,
            desc: "",
            envelopePic: "",
            fresh: false,
            id: 1848,
            link: "https://developers.google.cn/",
            niceDate: "2018-01-07",
            origin: "",
            projectLink: "",
            publishTime: 1515322795000,
            superChapterId: 0,
            superChapterName: "",
            tags: [ ],
            title: "Google开发者",
            type: 0,
            userId: -1,
            visible: 0,
            zan: 0
        },
        {
            apkLink: "",
            author: "小编",
            chapterId: 272,
            chapterName: "常用网站",
            collect: false,
            courseId: 13,
            desc: "",
            envelopePic: "",
            fresh: false,
            id: 2406,
            link: "http://jcenter.bintray.com/",
            niceDate: "2018-02-25",
            origin: "",
            projectLink: "",
            publishTime: 1519537722000,
            superChapterId: 0,
            superChapterName: "",
            tags: [ ],
            title: "jcenter仓库",
            type: 0,
            userId: -1,
            visible: 1,
            zan: 0
        }
        ]*/
