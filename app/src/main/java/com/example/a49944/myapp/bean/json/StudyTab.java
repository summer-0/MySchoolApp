package com.example.a49944.myapp.bean.json;

import java.io.Serializable;
import java.util.List;

public class StudyTab  implements Serializable {
    private static final long serialVersionUID = -6941795244558100758L;
    private int errorCode; //0
    private String errorMsg; //
    private List<NaviBean> data;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public List<NaviBean> getData() {
        return data;
    }

    public void setData(List<NaviBean> data) {
        this.data = data;
    }
}
