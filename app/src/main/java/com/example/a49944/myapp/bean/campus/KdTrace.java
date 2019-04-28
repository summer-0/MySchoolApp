package com.example.a49944.myapp.bean.campus;

import java.io.Serializable;
import java.util.List;

/**
 * Created by summer_h on 2019/4/25 22:44
 */
public class KdTrace implements Serializable {

    /**
     * LogisticCode : 3747520216455
     * ShipperCode : YD
     * Traces : [{"AcceptStation":"【泉州市】  福建主城区公司泉州美泰淘宝服务部 已揽收","AcceptTime":"2019-04-23 12:10:18"},{"AcceptStation":"【泉州市】 已到达  福建晋江分拨中心","AcceptTime":"2019-04-23 22:44:35"},{"AcceptStation":"【泉州市】 已离开  福建晋江分拨中心 发往 广东广州网点包","AcceptTime":"2019-04-23 22:47:37"},{"AcceptStation":"【泉州市】 已离开  福建晋江分拨中心 发往 广东广州分拨中心","AcceptTime":"2019-04-23 22:58:35"},{"AcceptStation":"【广州市】 已到达  广东广州分拨中心","AcceptTime":"2019-04-24 14:12:09"},{"AcceptStation":"【广州市】 已到达  广东广州分拨中心","AcceptTime":"2019-04-24 14:25:12"},{"AcceptStation":"【广州市】 已离开  广东广州分拨中心 发往 广东主城区公司广州天河区东圃前进服务部","AcceptTime":"2019-04-24 14:26:18"},{"AcceptStation":"【广州市】 已离开  广东主城区公司广州天河区东圃前进服务部 发往  广东主城公司天河区东圃前进服务部东景分部(020-23399682|82310434|87024678)","AcceptTime":"2019-04-25 04:47:08"},{"AcceptStation":"【广州市】  广东主城区公司广州天河区东圃前进服务部 派件员 余裕(18102694110)正在为您派送","AcceptTime":"2019-04-25 11:01:58"},{"AcceptStation":"【广州市】 已签收 : 由广州莲溪南路8巷2号店 代签收，如有问题联系余裕(18102694110)","AcceptTime":"2019-04-25 12:50:11"}]
     * State : 3
     * EBusinessID : 1470525
     * Success : true
     */

    private String LogisticCode;
    private String ShipperCode;
    private String State;
    private String EBusinessID;
    private boolean Success;
    private List<TracesBean> Traces;

    public String getLogisticCode() {
        return LogisticCode;
    }

    public void setLogisticCode(String LogisticCode) {
        this.LogisticCode = LogisticCode;
    }

    public String getShipperCode() {
        return ShipperCode;
    }

    public void setShipperCode(String ShipperCode) {
        this.ShipperCode = ShipperCode;
    }

    public String getState() {
        return State;
    }

    public void setState(String State) {
        this.State = State;
    }

    public String getEBusinessID() {
        return EBusinessID;
    }

    public void setEBusinessID(String EBusinessID) {
        this.EBusinessID = EBusinessID;
    }

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean Success) {
        this.Success = Success;
    }

    public List<TracesBean> getTraces() {
        return Traces;
    }

    public void setTraces(List<TracesBean> Traces) {
        this.Traces = Traces;
    }

    public static class TracesBean {
        /**
         * AcceptStation : 【泉州市】  福建主城区公司泉州美泰淘宝服务部 已揽收
         * AcceptTime : 2019-04-23 12:10:18
         */

        private String AcceptStation;
        private String AcceptTime;

        public String getAcceptStation() {
            return AcceptStation;
        }

        public void setAcceptStation(String AcceptStation) {
            this.AcceptStation = AcceptStation;
        }

        public String getAcceptTime() {
            return AcceptTime;
        }

        public void setAcceptTime(String AcceptTime) {
            this.AcceptTime = AcceptTime;
        }
    }
}
