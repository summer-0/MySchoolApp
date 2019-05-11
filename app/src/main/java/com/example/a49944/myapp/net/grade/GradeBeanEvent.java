package com.example.a49944.myapp.net.grade;

import com.example.a49944.myapp.net.hhnet.bean.GradeBean;

/**
 * Author： Hndroid
 * Email : 2282250500@qq.com
 * Date： 18-5-5
 * Description：
 */
public class GradeBeanEvent {

    private GradeBean.RowsBean mRowsBean;

    public void setGradeBean(GradeBean.RowsBean gradeBean) {
        mRowsBean = gradeBean;
    }

    public GradeBean.RowsBean getGradeBean() {
        return mRowsBean;
    }
}
